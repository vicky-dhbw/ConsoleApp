package app;
import common.org.Gin;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class JSON_Loader implements IFileLoader,ISearchFile{
    @Override
    @SuppressWarnings("unchecked")
    public List<Gin> loadFile(String filename) {
        if(searchFile(filename)){
            List<Gin> ginList=new ArrayList<>();
            try{
                URL[] urls={new File(Configuration.INSTANCE.fullPathToJSONLoaderJavaArchive).toURI().toURL()};
                URLClassLoader urlClassLoader=new URLClassLoader(urls,JSON_Loader.class.getClassLoader());

                Class<?> jsonLoaderClass= forName("JSONLoader_",true,urlClassLoader);
                Object jsonLoaderInstance=jsonLoaderClass.getMethod("getInstance").invoke(null);
                Object jsonLoaderPort=jsonLoaderClass.getDeclaredField("port").get(jsonLoaderInstance);

                Method load=jsonLoaderPort.getClass().getMethod("load", String.class);
                ginList=(List<Gin>)load.invoke(jsonLoaderPort,Configuration.INSTANCE.pathToJSONFile);
            }catch (Exception e){
                e.printStackTrace();
            }

            return ginList;
        }
        System.out.println("data failed to load :(");
        return null;
    }

    @Override
    public boolean searchFile(String filename) {
        return FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir));
    }
}
