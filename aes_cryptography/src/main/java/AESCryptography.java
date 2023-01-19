import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESCryptography {

    public static final AESCryptography aesCryptography=new AESCryptography();
    public Port port;
    public AESCryptography(){
        port=new Port();
    }
    public static AESCryptography getInstance(){
        return aesCryptography;
    }


    public void innerEncrypt(String key, File inputFile, File outputFile) throws IOException {
        if(FileSearcher.searchFile(inputFile.getName(), new File(inputFile.getParent()))||FileSearcher.searchFile(outputFile.getName(),new File(outputFile.getParent()))){
            doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
        }
        else {
            inputFile.createNewFile();
            outputFile.createNewFile();
            doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
        }

    }

    public void innerDecrypt(String key, File inputFile, File outputFile) throws IOException {
        if(FileSearcher.searchFile(inputFile.getName(), new File(inputFile.getParent()))||FileSearcher.searchFile(outputFile.getName(),new File(outputFile.getParent()))){
            doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
        }
        else {
            inputFile.createNewFile();
            outputFile.createNewFile();
            doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
        }

    }
    private void doCrypto(int cipherMode, String key, File inputFile, File outputFile) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                 | InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public class Port implements IAESCryptography{

        @Override
        public void decrypt(String key, File inputFile, File outputFile) throws IOException {
            innerDecrypt(key,inputFile,outputFile);
        }

        @Override
        public void encrypt(String key, File inputFile, File outputFile) throws IOException {
            innerEncrypt(key,inputFile,outputFile);
        }
    }
}
