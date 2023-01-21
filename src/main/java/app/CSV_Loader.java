package app;

import common.org.Gin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class CSV_Loader implements IFileLoader,ISearchFile{

    @Override
    @SuppressWarnings("unchecked")
    public List<Gin> loadFile(String filename) {
        if(searchFile(filename)){
            try{
                URL[] urls={new File(Configuration.INSTANCE.fullPathToCSVLoaderJavaArchive).toURI().toURL()};
                URLClassLoader urlClassLoader=new URLClassLoader(urls,CSV_Loader.class.getClassLoader());

                Class<?> csvLoaderClass=Class.forName("CSVLoader",true,urlClassLoader);
                Object csvLoaderInstance=csvLoaderClass.getMethod("getInstance").invoke(null);
                Object csvLoaderPort=csvLoaderClass.getDeclaredField("port").get(csvLoaderInstance);

                Method load=csvLoaderPort.getClass().getMethod("load", String.class);

                return (List<Gin>) load.invoke(csvLoaderPort,Configuration.INSTANCE.pathToCSVFile);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("data failed to load :(");
        return null;
    }

    @Override
    public boolean searchFile(String filename) {
        return FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir));
    }
}
