import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) {

        try {

            Path path = Paths.get("/home/nathaniel/IdeaProjects/ColorPaletteArt/src/art.txt");
            byte[] data = Files.readAllBytes(path);

            int height = (int) Math.ceil(Math.sqrt(data.length/3));
            int width = height;
            while(width*height>data.length/3)
            {
                width = width-1;
            }
            width=width+1;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Value to index the byte[];
            int bI = 0;

            // Iterate through the pixel rows;
            for(int i = 0; i < height; i++) {
                // Iterate through the pixels in the row;
                for(int j = 0; j < width; j++) {
                    // Pull out 4 bytes and generate colour int;
                    // This entire statement depends on bytesPerPixel;
                    int pixelVal1 = bI < data.length ? Byte.toUnsignedInt(data[bI]) : 0;
                    int pixelVal2 = bI+1 < data.length ? Byte.toUnsignedInt(data[bI+1]) : 0;
                    int pixelVal3 = bI+2 < data.length ? Byte.toUnsignedInt(data[bI+2]) : 0;
                    int rgb=new Color(pixelVal1,pixelVal2,pixelVal3).getRGB();
                    image.setRGB(j,i, rgb);
                    bI=bI+3;
                }
            }

            File outputfile = new File("image.png");
            ImageIO.write(image, "png", outputfile);

            BufferedImage originalImage = null;
            originalImage = ImageIO.read(new File("image.png"));

            byte[] recoveredBytes = new byte[originalImage.getHeight()*originalImage.getWidth()*3];
            bI=0;
            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    int  clr   = originalImage.getRGB(x,y);
                    Color c = new Color(originalImage.getRGB(x,y));
                    recoveredBytes[bI] = (byte) c.getRed();
                    recoveredBytes[bI+1] = (byte) c.getGreen();
                    recoveredBytes[bI+2] =  (byte) c.getBlue();
                    originalImage.setRGB(x,y, clr);
                    bI=bI+3;
                }
            }

            FileOutputStream fos = new FileOutputStream("recovered.txt");
            fos.write(recoveredBytes);
            fos.close();

        } catch(Exception e) { System.out.println(e.getMessage());}
    }
}
