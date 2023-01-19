package app;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class MergeSort implements ISorter{
    @Override
    public void sort(String[] values) {
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToMergeSortJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,MergeSort.class.getClassLoader());

            Class<?> mergeSortClass=Class.forName("MergeSort",true,urlClassLoader);
            Object mergeSortClassInstance=mergeSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=mergeSortClass.getDeclaredField("port").get(mergeSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("mergeSort", String[].class,int.class,int.class);
            Object[] arguments = new Object[3];  //values of parameters for constructor above
            arguments[0] = values;
            arguments[1] = 0;
            arguments[2]=values.length-1;
            load.invoke(mergeSortPort,arguments);

            for(String manufacturer:values){
                System.out.println(manufacturer);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
