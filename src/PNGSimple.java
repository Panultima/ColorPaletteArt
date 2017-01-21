import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

/**
 * Created by nathaniel on 1/20/17.
 */
public class PNGSimple {

    static void writeToImage(byte[] data, String path, Boolean genRand)
    {
        try {
            SecureRandom rnd = new SecureRandom();
            int height = (int) Math.ceil(Math.sqrt((data.length/3)+1));
            int width = height;
            while(width*height>(data.length/3)+1)
            {
                width = width-1;
            }
            width=width+1;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            // Value to index the byte[];
            int bI = 0;
            int paddingLength = width*height*3 - data.length;
            byte[] padding = ByteBuffer.allocate(4).putInt(paddingLength).array();
            int min = data[0];
            int max = data[0];

            // Iterate through the pixel rows;
            for(int i = 0; i < height; i++) {
                // Iterate through the pixels in the row;
                for(int j = 0; j < width; j++) {
                    // Pull out 4 bytes and generate colour int;
                    // This entire statement depends on bytesPerPixel;
                    if(i==0 && j==0) {
                        int p1 = Byte.toUnsignedInt(padding[1]);
                        int p2 = Byte.toUnsignedInt(padding[2]);
                        int p3 = Byte.toUnsignedInt(padding[3]);
                        int rgb=new Color(p1,p2,p3).getRGB();
                        image.setRGB(j,i,rgb);
                    }
                    else {
                        int pixelVal1 = bI < data.length ? Byte.toUnsignedInt(data[bI]) : Byte.toUnsignedInt(data[rnd.nextInt(data.length)]);
                        if(pixelVal1<min) {min=pixelVal1;}
                        if(pixelVal1>max) {max=pixelVal1;}
                        int pixelVal2 = bI + 1 < data.length ? Byte.toUnsignedInt(data[bI + 1]) : Byte.toUnsignedInt(data[rnd.nextInt(data.length)]);
                        if(pixelVal2<min) {min=pixelVal2;}
                        if(pixelVal2>max) {max=pixelVal2;}
                        int pixelVal3 = bI + 2 < data.length ? Byte.toUnsignedInt(data[bI + 2]) : Byte.toUnsignedInt(data[rnd.nextInt(data.length)]);
                        if(pixelVal3<min) {min=pixelVal3;}
                        if(pixelVal3>max) {max=pixelVal3;}
                        int rgb = new Color(pixelVal1, pixelVal2, pixelVal3).getRGB();
                        image.setRGB(j, i, rgb);
                        if (bI + 2 < data.length) {
                            bI = bI + 3;
                        }
                    }
                }
            }

            if(genRand)
            {
                BufferedImage rand = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);

                for(int i = 0; i < height; i++) {
                    // Iterate through the pixels in the row;
                    for (int j = 0; j < width; j++) {
                        int p1 = rnd.nextInt(max-min)+min;
                        int p2 = rnd.nextInt(max-min)+min;
                        int p3 = rnd.nextInt(max-min)+min;
                        int rgb = new Color(p1, p2, p3).getRGB();
                        rand.setRGB(j, i, rgb);
                    }
                }

                File outputfile = new File("rand.png");
                ImageIO.write(rand, "png", outputfile);
            }

            File outputfile = new File(path);
            ImageIO.write(image, "png", outputfile);
        } catch(Exception e) {}
    }

    static byte[] recoverFromImage(String imagePath)
    {
        try {
            Path path = Paths.get(imagePath);
            byte[] data = Files.readAllBytes(path);

            BufferedImage originalImage = null;
            originalImage = ImageIO.read(new File("image.png"));

            byte[] recoveredBytes = new byte[originalImage.getHeight() * originalImage.getWidth() * 3];
            int bI = 0;
            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    int clr = originalImage.getRGB(x, y);
                    Color c = new Color(originalImage.getRGB(x, y));
                    recoveredBytes[bI] = (byte) c.getRed();
                    recoveredBytes[bI + 1] = (byte) c.getGreen();
                    recoveredBytes[bI + 2] = (byte) c.getBlue();
                    originalImage.setRGB(x, y, clr);
                    bI = bI + 3;
                }
            }

            int length = recoveredBytes.length;
            byte[] pad = {recoveredBytes[0], recoveredBytes[1],recoveredBytes[2]};
            int paddinglength = new BigInteger(pad).intValue();

            byte[] finalBytes = new byte[length-paddinglength];
            System.arraycopy(recoveredBytes, 3, finalBytes, 0, length-paddinglength);

            return finalBytes;
        }catch(Exception e) { System.out.println(e.getMessage());}

        return null;
    }
}
