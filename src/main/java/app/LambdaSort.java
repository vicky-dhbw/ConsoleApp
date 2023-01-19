package app;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LambdaSort implements ISorter{
    @Override
    public void sort(String[] values) {
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToLambdaSortJavaArchive).toURI().toURL()};
            System.out.println(Configuration.INSTANCE.fullPathToLambdaSortJavaArchive);
            URLClassLoader urlClassLoader=new URLClassLoader(urls,LambdaSort.class.getClassLoader());

            Class<?> lambdaSortClass=Class.forName("Lambda_Sort",true,urlClassLoader);
            Object lambdaSortClassInstance=lambdaSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=lambdaSortClass.getDeclaredField("port").get(lambdaSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("sort", String[].class);
            load.invoke(mergeSortPort, (Object) values);

            for(String manufacturer:values){
                System.out.println(manufacturer);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
