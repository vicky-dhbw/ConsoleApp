package app;
import common.org.Gin;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class LambdaSort implements ISorter{
    @Override
    public void sort(List<Gin> values) {
        List<Gin> gins=new ArrayList<>();
        gins=values;
        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToLambdaSortJavaArchive).toURI().toURL()};
            System.out.println(Configuration.INSTANCE.fullPathToLambdaSortJavaArchive);
            URLClassLoader urlClassLoader=new URLClassLoader(urls,LambdaSort.class.getClassLoader());

            Class<?> lambdaSortClass=Class.forName("Lambda_Sort",true,urlClassLoader);
            Object lambdaSortClassInstance=lambdaSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=lambdaSortClass.getDeclaredField("port").get(lambdaSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("sort", List.class);
            Type[] genericParameterTypes = load.getGenericParameterTypes();

            load.invoke(mergeSortPort, values);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
