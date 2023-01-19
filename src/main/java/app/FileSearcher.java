package app;

import java.io.File;

public class FileSearcher {

    public static boolean searchFile(String filename, File directory){
        File[] list = directory.listFiles();
        if(list!=null){
            for(File file: list){
                if(filename.equalsIgnoreCase(file.getName())){
                    System.out.println("file found...");
                    return true;
                }
            }
        }
        System.out.println("no such file found..");
        return false;
    }
}
