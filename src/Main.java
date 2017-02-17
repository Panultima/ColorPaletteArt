import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        if(args.length < 10) {

            if(args[0].equals("ps")) {
                if (args[1].equals("e")) {
                    Embed.PNGSimpleAES16(args[2], args[3], args[4]);
                }

                if (args[1].equals("r")) {
                    Retrieve.PNGSimpleAES16(args[2], args[3], args[4]);
                }
            }

            if(args[0].equals("p")) {
                if (args[1].equals("e")) {
                    Embed.PNGAES16(args[2], args[3], args[4],args[5]);
                }

                if (args[1].equals("r")) {
                    Retrieve.PNGAES16(args[2], args[3], args[4]);
                }
            }

        }

    }
}
