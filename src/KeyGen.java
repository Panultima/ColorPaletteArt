import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KeyGen {

    public static byte[] getKeyFromFile16(String dir)
    {
        byte[] key = new byte[16];

        try {
            Path path = Paths.get(dir);
            byte[] data = Files.readAllBytes(path);
            key[0] = data[3];
            key[1] = data[21];
            key[2] = data[7];
            key[3] = data[32];
            key[4] = data[41];
            key[5] = data[167];
            key[6] = data[1022];
            key[7] = data[0];
            key[8] = data[2000];
            key[9] = data[567];
            key[10] = data[3001];
            key[11] = data[9];
            key[12] = data[1500];
            key[13] = data[1501];
            key[14] = data[2121];
            key[15] = data[2222];
        } catch(Exception e) {}

        return key;
    }
}
