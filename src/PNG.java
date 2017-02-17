import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Grandpa on 2/17/2017.
 */
public class PNG {

    private static byte[] intToBytes(int num, int numBytes) {
        byte[] bytes = new byte[numBytes];
        for (int i = 0; i < numBytes; i += 1) {
            bytes[i] = (byte) ((num >> (8 * i)) & 0xFF);
        }
        return bytes;
    }

    private static int bytesToInt(byte[] bytes, int numBytes) {
        int num = 0;
        for (int i = 0; i < numBytes; i += 1) {
            num |= (bytes[i] & 0xFF) << (8 * i);
        }
        return num;
    }

    static void writeToImage(byte[] data, String path, String outpath)
    {
        BufferedImage im = null;
        File encryptedFile = new File(outpath);
        try {
            im = ImageIO.read(new File(path));
            WritableRaster raster = im.getRaster();
            DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

            byte[] writableBytes = buffer.getData();

            int header = data.length;
            byte[] lenBytes = intToBytes(header,4);
            int totalLen = 4 + data.length;
            byte[] bytesToHide = new byte[totalLen];

            System.arraycopy(lenBytes, 0, bytesToHide, 0, lenBytes.length);
            System.arraycopy(data, 0, bytesToHide, lenBytes.length,
                    data.length);

            if (bytesToHide.length * 8 > writableBytes.length) {
                System.out.println("Image too small to hide message");
            }

            int offset = 0;
            for (int i = 0; i < bytesToHide.length; i += 1) {
                byte b = bytesToHide[i];
                for (int j = 0; j < 8; j += 1) {
                    int bit = (b >> j) & 1;
                    writableBytes[offset] = (byte) ((writableBytes[offset] & 0xFE) | bit);
                    offset += 1;
                }
            }
            ImageIO.write(im, "png", encryptedFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static byte[] recoverFromImage(String imagePath)
    {
        BufferedImage im = null;
        byte[] hiddenBytes = null;
        try {
            im = ImageIO.read(new File(imagePath));
            WritableRaster raster = im.getRaster();
            DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
            byte[] data = buffer.getData();

            int len = 0;
            int offset = 0;
            byte[] lendata = new byte[4];

            for (int i = 0; i < 4; i += 1) {
                byte b = 0;
                for (int j = 0; j < 8; j += 1) {
                    b |= (data[offset] & 1) << j;
                    offset += 1;
                }
                lendata[i] = b;
            }
            len = bytesToInt(lendata,4);
            hiddenBytes = new byte[len];
            for (int i = 0; i < len; i += 1) {
                byte b = 0;
                for (int j = 0; j < 8; j += 1) {
                    b |= (data[offset] & 1) << j;
                    offset += 1;
                }
                hiddenBytes[i] = b;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return hiddenBytes;
    }
}
