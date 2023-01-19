import java.util.Arrays;
import java.util.List;

public class Lambda_Sort {
    private static final Lambda_Sort lambdaSortInstance=new Lambda_Sort();
    public Port port;
    public Lambda_Sort(){
        port= new Port();
    }

    public static Lambda_Sort getInstance(){
        return lambdaSortInstance;
    }

    public void innerSort(String[] values){
        List<String> arrayList= Arrays.asList(values);
        arrayList.sort(String::compareTo);
        for(int i=0;i<values.length;i++){
            values[i]= arrayList.get(i);
        }
    }
    public class Port implements ILambdaSort{

        @Override
        public void sort(String[] values) {
            innerSort(values);
        }
    }
}
