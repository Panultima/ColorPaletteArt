import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {

        if(args.length < 10) {

            if(args[0].equals("rd")) {

                /** ALL PARAMETERS REQUIRE FILE EXTENSION
                 * args[2] = file to embed in an image
                 * args[3] = the key image
                 * args[4] = name of the output image file
                 */
                if (args[1].equals("e")) {
                    Embed.PNGSimpleAES16(args[2], args[3], args[4]);
                }

                /**
                 * args[2] = image with embedded data
                 * args[3] = the key image
                 * args[4] = output file name (make sure it has correct extension)
                 */
                if (args[1].equals("r")) {
                    Retrieve.PNGSimpleAES16(args[2], args[3], args[4]);
                }
            }

            if(args[0].equals("lsb")) {

                /**
                 * args[2] = file to embed in an image
                 * args[3] = the key image
                 * args[4] = image to embed the file in
                 * args[5] = name of the output image file with the embedded data
                 */
                if (args[1].equals("e")) {
                    Embed.PNGAES16(args[2], args[3], args[4],args[5]);
                }

                /**
                 * args[2] = image with embedded data
                 * args[3] = the key image
                 * args[4] = output file name (make sure it has correct extension)
                 */
                if (args[1].equals("r")) {
                    Retrieve.PNGAES16(args[2], args[3], args[4]);
                }
            }

        }

    }
}
