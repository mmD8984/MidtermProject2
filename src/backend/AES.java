package backend;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
// thuật toán đối xứng
public class AES {
    SecretKey key;

    //    0- Tạo khóa
    public SecretKey genKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return key = keyGenerator.generateKey();
    }

    // 0.0 load khóa
    public void loadKey(SecretKey key) {
        this.key = key;
    }

    // 1- mã hóa text(đoạn văn bản)
    public byte[] encrypt(String text) throws Exception {
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        return cipher.doFinal(data);
    }

    public char[] encryptFile(String srcf, String desf) {
        // TODO Auto-generated method stub
        return null;
    }

    public String decrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
    }
    public char[] decryptFile(String desf, String de) {
        // TODO Auto-generated method stub
        return null;
    }

//    public static void main(String[] args) throws  Exception {
//        AES aes = new AES();
//        aes.genKey();
//        String text = "Phạm Anh Thư";
//        byte[] data=aes.encrypt(text);
//        System.out.println(Base64.getEncoder().encodeToString(data));
//        String out = aes.decrypt(data);
//        System.out.println(out);
//    }
}
