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
        Arrays.sort(values);
    }
    public class Port implements ILambdaSort{

        @Override
        public void sort(String[] values) {
            innerSort(values);
        }
    }
}
