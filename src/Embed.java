import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nathaniel on 1/22/17.
 */
public class Embed {

    public static void PNGSimpleAES16(String filepath, String keyfilepath, String imagepath)
    {
        try {
            Path path = Paths.get(filepath);
            byte[] data = Files.readAllBytes(path);

            byte[] enc = Encrypt.AESEncrypt(data, KeyGen.getKeyFromFile16(keyfilepath));

            PNGSimple.writeToImage(enc, imagepath, true);
        } catch(Exception e) {}
    }

    public static void PNGAES16(String filepath, String keyfilepath, String imagepath, String outputimagepath)
    {
        try {
            Path path = Paths.get(filepath);
            byte[] data = Files.readAllBytes(path);

            byte[] enc = Encrypt.AESEncrypt(data, KeyGen.getKeyFromFile16(keyfilepath));

            PNG.writeToImage(enc, imagepath, outputimagepath);
        } catch(Exception e) {}
    }
}
