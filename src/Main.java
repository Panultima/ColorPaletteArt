import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class Main {

    public static void main(String[] args) {

        try {

            byte[] key = new byte[16];
            new Random().nextBytes(key);

            Path path = Paths.get("plain.txt");
            byte[] data = Files.readAllBytes(path);

            byte[] enc = Encrypt.AESEncrypt(data,key);

            PNGSimple.writeToImage(enc,"image.png",true);
            byte[] recovered = PNGSimple.recoverFromImage("image.png");

            byte[] decrypted = Encrypt.AESDecrypt(recovered,key);

            FileOutputStream fos = new FileOutputStream("recovered.txt");
            fos.write(decrypted);
            fos.close();

        } catch(Exception e) { System.out.println(e.getMessage());}
    }
}
