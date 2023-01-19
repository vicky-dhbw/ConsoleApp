package app;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class CSV_Loader implements IFileLoader,ISearchFile{

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<String> loadFile(String filename) {
        if(searchFile(filename)){
            try{
                URL[] urls={new File(Configuration.INSTANCE.fullPathToCSVLoaderJavaArchive).toURI().toURL()};
                URLClassLoader urlClassLoader=new URLClassLoader(urls,CSV_Loader.class.getClassLoader());

                Class<?> csvLoaderClass=Class.forName("CSVLoader",true,urlClassLoader);
                Object csvLoaderInstance=csvLoaderClass.getMethod("getInstance").invoke(null);
                Object csvLoaderPort=csvLoaderClass.getDeclaredField("port").get(csvLoaderInstance);

                Method load=csvLoaderPort.getClass().getMethod("load", String.class);
                System.out.println("data successfully loaded :)");

                return (ArrayList<String>) load.invoke(csvLoaderPort,Configuration.INSTANCE.pathToCSVFile);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("data failed to load :(");
        return new ArrayList<String>();
    }

    @Override
    public boolean searchFile(String filename) {
        return FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir));
    }
}
