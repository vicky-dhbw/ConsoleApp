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
    public String[] sort(List<Gin> gins) {
        String[] sortedManufacturers=new String[2];

        try{
            URL[] urls={new File(Configuration.INSTANCE.fullPathToLambdaSortJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader=new URLClassLoader(urls,LambdaSort.class.getClassLoader());

            Class<?> lambdaSortClass=Class.forName("Lambda_Sort",true,urlClassLoader);
            Object lambdaSortClassInstance=lambdaSortClass.getMethod("getInstance").invoke(null);
            Object mergeSortPort=lambdaSortClass.getDeclaredField("port").get(lambdaSortClassInstance);

            Method load=mergeSortPort.getClass().getMethod("sort", String[].class);
            Type[] genericParameterTypes = load.getGenericParameterTypes();

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
