package backend;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
// Chữ ký điện tử
public class DS {
    KeyPair keyPair;
    SecureRandom secureRandom;
    Signature signature;
    PublicKey publicKey;
    PrivateKey privateKey;

    public DS() {
    }

    public DS(String alg, String aloRandom, String prov) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(alg, prov);
        secureRandom = SecureRandom.getInstance(aloRandom, prov);
        generator.initialize(1024, secureRandom);
        keyPair = generator.genKeyPair();
        signature = Signature.getInstance(alg, prov);
    }

    // 0- tạo khóa
    public boolean genKey() {
        if (keyPair == null)
            return false;
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        return true;
    }

    // 1- ký tên với text(đoạn văn bản)
    public String sign(String mess) throws SignatureException, InvalidKeyException {
        byte[] data = mess.getBytes();
        signature.initSign(privateKey);
        signature.update(data);
        byte[] sign = signature.sign();
        return Base64.getEncoder().encodeToString(sign);
    }

    // 2- ký tên với file
    public String signFile(String src) throws InvalidKeyException, IOException, SignatureException {
        signature.initSign(privateKey);
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(src));
        byte[] buff=new byte[1024];
        int read;
        while((read=bis.read(buff))!=-1) {
            signature.update(buff,0,read);
        }
        bis.close();
        byte[] sign=signature.sign();
        return Base64.getEncoder().encodeToString(sign);
    }

    // 3- xác thực chữ ký text
    public boolean verify(String src, String sign) throws InvalidKeyException, SignatureException {
        signature.initVerify(publicKey);
        byte[] data=src.getBytes();
        byte[] signValue=Base64.getDecoder().decode(sign);
        signature.update(data);
        return signature.verify(signValue);
    }

    // 4- xác thực chữ ký với file
    public boolean verifyFile(String src, String sign) throws InvalidKeyException, SignatureException, IOException {
        signature.initVerify(publicKey);
        byte[] signValue=Base64.getDecoder().decode(sign);
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(src));
        byte[] buff=new byte[1024];
        int read;
        while((read=bis.read(buff))!=-1) {
            signature.update(buff,0,read);
        }
        bis.close();
        return signature.verify(signValue);
    }
}
