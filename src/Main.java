import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class Main {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("plain.txt");
            byte[] data = Files.readAllBytes(path);

            byte[] enc = Encrypt.AESEncrypt(data,KeyGen.getKeyFromFile("keyimage.jpg"));

            PNGSimple.writeToImage(enc,"image.png",true);
            byte[] recovered = PNGSimple.recoverFromImage("image.png");

            byte[] decrypted = Encrypt.AESDecrypt(recovered,KeyGen.getKeyFromFile("keyimage.jpg"));

            FileOutputStream fos = new FileOutputStream("recovered.txt");
            fos.write(decrypted);
            fos.close();

        } catch(Exception e) { System.out.println(e.getMessage());}
    }
}
