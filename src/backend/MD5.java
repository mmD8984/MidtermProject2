package backend;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

// thuật toán băm
public class MD5 {
    // 1- Băm với text(đoạn văn bản)
    public String hash(String data) throws Exception {
        //	đối sang nhi phân
        byte[] bs = data.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        //	Load vào MD
        byte[] digest = md.digest(bs);
        BigInteger re = new BigInteger(1, digest);
        return re.toString(16);
        System.out.println("anh thư");
    }
    // 2- băm với file
    public String hashFile(String src) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        File file = new File(src);
        if (!file.exists())
            return null;
        DigestInputStream di = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)), md);
        byte[] buff = new byte[1024];
        int read;
        do {
            read = di.read(buff);
        } while (read != -1);
        byte[] digest = di.getMessageDigest().digest();
        BigInteger re = new BigInteger(1, digest);
        return re.toString(16);
    }
}
