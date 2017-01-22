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
            Path path = Paths.get("readingplan.pdf");
            byte[] data = Files.readAllBytes(path);

            byte[] enc = Encrypt.AESEncrypt(data, KeyGen.getKeyFromFile16("keyimage.jpg"));

            PNGSimple.writeToImage(enc, "image.png", true);
        } catch(Exception e) {}
    }
}
