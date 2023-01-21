package app;

import common.org.Gin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class QuickSort implements ISorter{
    @Override
    public void sort(List<Gin> values) {
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToQuickSortJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,QuickSort.class.getClassLoader());

            Class<?> quickSortClass=Class.forName("QuickSort",true,urlClassLoader);
            Object quickSortClassInstance=quickSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=quickSortClass.getDeclaredField("port").get(quickSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("sort", String[].class);
            load.invoke(mergeSortPort,values);



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
