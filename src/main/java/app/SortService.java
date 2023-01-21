package app;

import common.org.Gin;

import java.util.ArrayList;
import java.util.List;

public class SortService {

    private String algorithm;

    public void executeSortService(List<Gin> gins, String command){
        String[] subCommands=command.split(" ");
        algorithm=subCommands[4];
        ISorter sorter = getSorterClass();
        if(sorter!=null){
            sorter.sort(gins);
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
