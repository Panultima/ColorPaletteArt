import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        Embed.PNGSimpleAES16("readingplan.pdf","keyimage.jpg","image.png");

        Retrieve.PNGSimpleAES16("image.png","keyimage.jpg","recovered.pdf");

    }
}
