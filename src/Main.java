import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("/home/nathaniel/IdeaProjects/ColorPaletteArt/src/art.txt");
            byte[] data = Files.readAllBytes(path);

            PNGSimple.writeToImage(data,"image.png");
            PNGSimple.recoverFromImage("image.png","recovered.txt");

        } catch(Exception e) { System.out.println(e.getMessage());}
    }
}
