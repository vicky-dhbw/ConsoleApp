package app;

import java.io.File;
import java.io.IOException;

public class EncryptionDecryptionService {
    AESCryptography aesCryptography=new AESCryptography();
    public void executeCryptographicProcess(String command) throws IOException {
        String[] subCommands=command.split(" ");
        String filename=subCommands[1];
        File inputFile=getFilename(filename);
        String key = "v*i*c*k*y*k*e*y*";
        if(subCommands[0].equals("encrypt")){
            if(inputFile!=null){
                aesCryptography.encrypt(key,inputFile, new File(inputFile.getParent() + Configuration.INSTANCE.fileSeparator
                        + filename + ".encrypted"));
            }
        } else if (subCommands[0].equals("decrypt")) {
            if(inputFile!=null) {
                aesCryptography.decrypt(key,inputFile, new File(inputFile.getParent() + Configuration.INSTANCE.fileSeparator
                        + filename + ".decrypted"));
            }
        }
        else {
            System.out.println("could not encrypt or decrypt :(");
        }


    }

    public File getFilename(String filename){
        if(FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir))){
            return new File(Configuration.INSTANCE.pathToDataDir
            +Configuration.INSTANCE.fileSeparator+filename);
        }
        return null;
    }



}
