package app;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class XML_Loader implements IFileLoader,ISearchFile{
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<String> loadFile(String filename) {
        if(searchFile(filename)){
            try{
                URL[] urls={new File(Configuration.INSTANCE.fullPathToXMLLoaderJavaArchive).toURI().toURL()};
                URLClassLoader urlClassLoader=new URLClassLoader(urls,XML_Loader.class.getClassLoader());

                Class<?> xmlLoaderClass=Class.forName("XMLLoader",true,urlClassLoader);
                Object xmlLoaderInstance=xmlLoaderClass.getMethod("getInstance").invoke(null);
                Object xmlLoaderPort=xmlLoaderClass.getDeclaredField("port").get(xmlLoaderInstance);

                Method load=xmlLoaderPort.getClass().getMethod("load", String.class);
                System.out.println("data successfully loaded :)");

                return (ArrayList<String>) load.invoke(xmlLoaderPort,Configuration.INSTANCE.pathToXMLFile);

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
