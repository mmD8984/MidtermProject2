package backend;

import javax.crypto.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

// Thuật toán đối xứng
public class DES {
    SecretKey key;

    // 0- Tạo khóa
    public SecretKey genKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        return key = keyGenerator.generateKey();
    }
    // 0.0- load khóa
    public void loadKey(SecretKey key) {
        this.key = key;
    }

    // 1- Mã hóa text(đoạn văn bản)
    public byte[] encrypt(String text) throws Exception {
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        return cipher.doFinal(data);
    }

    // 2- Mã hóa file
    public boolean encryptFile(String srcf, String desf) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(srcf));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(desf));
        CipherInputStream en = new CipherInputStream(input, cipher);
        int i;
        byte[] buff = new byte[1024];
        while ((i = en.read(buff)) != -1) {
            output.write(buff, 0, i);
        }
        buff = cipher.doFinal();
        if (buff != null)
            output.write(buff);
        input.close();
        en.close();
        output.close();
        return true;
    }

    // 3- Giải mã text(đoạn văn bản)
    public String decrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
    }

    // 4- Giải mã file
    public boolean decryptFile(String desf, String de) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(desf));
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(de));
        CipherOutputStream decrypt = new CipherOutputStream(output, cipher);
        int i;
        byte[] buff = new byte[1024];
        while ((i = input.read(buff)) != -1) {
            decrypt.write(buff, 0, i);
        }
        buff = cipher.doFinal();
        if (buff != null)
            decrypt.write(buff);
        input.close();
        decrypt.close();
        output.close();
        return true;
    }
}
