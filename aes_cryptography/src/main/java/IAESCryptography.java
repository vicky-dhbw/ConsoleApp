import java.io.File;
import java.io.IOException;

public interface IAESCryptography {
    void decrypt(String key, File inputFile, File outputFile) throws IOException;
    void encrypt(String key, File inputFile, File outputFile) throws IOException;
}
