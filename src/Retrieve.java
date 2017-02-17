import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by nathaniel on 1/22/17.
 */
public class Retrieve {

    public static void PNGSimpleAES16(String imagepath, String keyimagepath, String outputpath)
    {
        try {
            byte[] recovered = PNGSimple.recoverFromImage(imagepath);

            byte[] decrypted = Encrypt.AESDecrypt(recovered, KeyGen.getKeyFromFile16(keyimagepath));

            File someFile = new File(outputpath);
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(decrypted);
            fos.flush();
            fos.close();
        } catch(Exception e) {}
    }

    public static void PNGAES16(String imagepath, String keyimagepath, String outputpath)
    {
        try {
            byte[] recovered = PNG.recoverFromImage(imagepath);

            byte[] decrypted = Encrypt.AESDecrypt(recovered, KeyGen.getKeyFromFile16(keyimagepath));

            File someFile = new File(outputpath);
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(decrypted);
            fos.flush();
            fos.close();
        } catch(Exception e) {}
    }

    public static void JPGAES16(String imagepath, String keyimagepath, String outputpath)
    {
        try {
            byte[] recovered = JPG.recoverFromImage(imagepath);

            byte[] decrypted = Encrypt.AESDecrypt(recovered, KeyGen.getKeyFromFile16(keyimagepath));

            File someFile = new File(outputpath);
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(decrypted);
            fos.flush();
            fos.close();
        } catch(Exception e) {}
    }
}
