import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        if(args.length == 4) {

            if(args[0].equals("e")) {
                Embed.PNGSimpleAES16(args[1], args[2], args[3]);
            }

            if(args[0].equals("r")) {
                Retrieve.PNGSimpleAES16(args[1], args[2], args[3]);
            }

        }

    }
}
