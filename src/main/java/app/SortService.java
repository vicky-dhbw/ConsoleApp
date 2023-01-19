package app;

import java.util.ArrayList;

public class SortService {

    private String algorithm;

    public void executeSortService(ArrayList<String> manufacturers,String command){
        String[] subCommands=command.split(" ");
        algorithm=subCommands[4];
        ISorter sorter = getSorterClass();
        if(sorter!=null){
            String[] values=manufacturers.stream().toList().toArray(new String[0]);
            sorter.sort(values);
        }
    }
    public ISorter getSorterClass(){
        switch (algorithm){
            case "lambda"-> {
                return new LambdaSort();
            }
            case "merge"->{
                return new MergeSort();
            }
            case "quick"->{
                return new QuickSort();
            }
            default -> {
                return null;
            }
        }
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    public String getAlgorithm(){
        return algorithm;
    }
}
