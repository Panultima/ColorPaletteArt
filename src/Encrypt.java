import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by nathaniel on 1/21/17.
 */
public class Encrypt {

    /**
     *
     * @param databytes
     * @param key
     * @return
     */
    public static byte[] AESEncrypt(byte[] databytes, byte[] key)
    {
        try {

            SecretKey SecKey = new SecretKeySpec(key, 0, key.length, "AES");

            //Get IV
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher AesCipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            AesCipher.init(Cipher.ENCRYPT_MODE, SecKey, ivParameterSpec);

            byte[] encrypted = AesCipher.doFinal(databytes);

            //append iv to encrypted text
            byte[] c = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, c, 0, iv.length);
            System.arraycopy(encrypted, 0, c, iv.length, encrypted.length);

            return c;

        } catch(Exception e) {System.out.println(e.getMessage());}

        return null;
    }

    /**
     * Decrypt encrypted bytes
     * @param databytes
     * @param key
     * @return
     */
    public static byte[] AESDecrypt(byte[] databytes, byte[] key)
    {
        try {
            //strip iv from data
            //append iv to encrypted text
            byte[] iv = new byte[16];
            byte[] data = new byte[databytes.length - 16];
            System.arraycopy(databytes, 0, iv, 0, 16);
            System.arraycopy(databytes, 16, data,0, databytes.length-16);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            SecretKey SecKey = new SecretKeySpec(key, 0, key.length, "AES");

            Cipher AesCipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            AesCipher.init(Cipher.DECRYPT_MODE, SecKey,ivParameterSpec);

            return AesCipher.doFinal(data);

        } catch(Exception e) {System.out.println(e.getMessage());}

        return null;
    }
}
