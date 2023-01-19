package app;
import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;

public class LoadService{

    private IFileLoader fileLoader;
    private String filename;

    public ArrayList<String> executeLoadService(String command){
        filename=command.split(" ")[2];
        fileLoader=getFileExtensionClassLoader(filename);
        if(fileLoader==null){
            System.out.println("file extension not supported");
        }
        else {
            return fileLoader.loadFile(filename);
        }

        return null;
    }


    public IFileLoader getFileExtensionClassLoader(String filename){

        String fileExtension=FilenameUtils.getExtension(filename);

        switch (fileExtension){
            case "csv"-> {
                return new CSV_Loader();
            }
            case "json"->{
                return new JSON_Loader();
            }
            case "xml"-> {
                return new XML_Loader();
            }
            default -> {
                return null;
            }
        }
    }


    public String getFilename() {
        return filename;
    }
}