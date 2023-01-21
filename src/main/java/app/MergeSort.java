package app;

import common.org.Gin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class MergeSort implements ISorter{
    @Override
    public void sort(List<Gin> gins) {
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToMergeSortJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,MergeSort.class.getClassLoader());

            Class<?> mergeSortClass=Class.forName("MergeSort",true,urlClassLoader);
            Object mergeSortClassInstance=mergeSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=mergeSortClass.getDeclaredField("port").get(mergeSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("mergeSort", String[].class,int.class,int.class);
            Object[] arguments = new Object[3];  //values of parameters for constructor above
            arguments[0] = gins;
            arguments[1] = 0;
            arguments[2]=gins.size()-1;
            load.invoke(mergeSortPort,arguments);



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
