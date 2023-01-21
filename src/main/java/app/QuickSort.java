package app;

import common.org.Gin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class QuickSort implements ISorter{
    String[] sortedManufacturers;
    @Override
    public String[] sort(List<Gin> gins) {
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToQuickSortJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,QuickSort.class.getClassLoader());

            Class<?> quickSortClass=Class.forName("QuickSort",true,urlClassLoader);
            Object quickSortClassInstance=quickSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=quickSortClass.getDeclaredField("port").get(quickSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("sort", String[].class);

            sortedManufacturers=new String[gins.size()];

            for(int i=0;i<sortedManufacturers.length;i++){
                sortedManufacturers[i]=gins.get(i).getManufacturer();
            }

            load.invoke(mergeSortPort, (Object) sortedManufacturers);



        }catch (Exception e){
            e.printStackTrace();
        }

        return sortedManufacturers;
    }
}
