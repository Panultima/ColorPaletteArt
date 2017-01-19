import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final int width = 32;
    private static final int height = 32;

    public static void main(String[] args) {

        try {

            Path path = Paths.get("/home/nathaniel/IdeaProjects/ColorPaletteArt/src/art.txt");
            byte[] data = Files.readAllBytes(path);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Value to index the byte[];
            int bI = 0;

            // Iterate through the pixel rows;
            for(int i = 0; i < height; i++) {
                // Iterate through the pixels in the row;
                for(int j = 0; j < width; j++) {
                    // Pull out 4 bytes and generate colour int;
                    // This entire statement depends on bytesPerPixel;
                    int pixelVal1 = Byte.toUnsignedInt(data[bI]);
                    int pixelVal2 = Byte.toUnsignedInt(data[bI++]);
                    int pixelVal3 = Byte.toUnsignedInt(data[bI+2]);
                    int rgb=new Color(pixelVal1,pixelVal2,pixelVal3).getRGB();
                    image.setRGB(i, j, rgb);
                    bI=bI+2;
                }
            }

            File outputfile = new File("image.jpg");
            ImageIO.write(image, "jpg", outputfile);

        } catch(Exception e) { System.out.println(e.getMessage());}
    }
}
