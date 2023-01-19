package app;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class JSON_Loader implements IFileLoader,ISearchFile{
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<String> loadFile(String filename) {
        if(searchFile(filename)){
            try{
                URL[] urls={new File(Configuration.INSTANCE.fullPathToJSONLoaderJavaArchive).toURI().toURL()};
                URLClassLoader urlClassLoader=new URLClassLoader(urls,JSON_Loader.class.getClassLoader());

                Class<?> jsonLoaderClass=Class.forName("JSONLoader",true,urlClassLoader);
                Object jsonLoaderInstance=jsonLoaderClass.getMethod("getInstance").invoke(null);
                Object jsonLoaderPort=jsonLoaderClass.getDeclaredField("port").get(jsonLoaderInstance);

                Method load=jsonLoaderPort.getClass().getMethod("load", String.class);
                System.out.println("data successfully loaded :)");

                return (ArrayList<String>) load.invoke(jsonLoaderPort,Configuration.INSTANCE.pathToJSONFile);

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
