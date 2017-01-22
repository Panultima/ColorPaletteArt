import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("readingplan.pdf");
            byte[] data = Files.readAllBytes(path);

            byte[] enc = Encrypt.AESEncrypt(data,KeyGen.getKeyFromFile16("keyimage.jpg"));

            PNGSimple.writeToImage(enc,"image.png",true);
            byte[] recovered = PNGSimple.recoverFromImage("image.png");

            byte[] decrypted = Encrypt.AESDecrypt(recovered,KeyGen.getKeyFromFile16("keyimage.jpg"));


            File someFile = new File("recovered.pdf");
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(decrypted);
            fos.flush();
            fos.close();

        } catch(Exception e) { System.out.println(e.getMessage());}
    }
}
