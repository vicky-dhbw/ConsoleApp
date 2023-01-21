package app;

import common.org.Gin;

import java.util.*;

public class SortService {

    private String algorithm;

    public void executeSortService(List<Gin> gins, String command){
        String[] sortedManufacturers=new String[0];
        String[] subCommands=command.split(" ");
        algorithm=subCommands[4];
        ISorter sorter = getSorterClass();
        if(sorter!=null){
            sortedManufacturers=sorter.sort(gins);
        }

        Set<String> manufacturerSet=new LinkedHashSet<>(Arrays.asList(sortedManufacturers));

        for (String manufacturer : manufacturerSet) {
            System.out.println();
            System.out.println("MANUFACTURER :"+manufacturer);
            System.out.println("----------------------------");
            for(Gin gin:gins){
                if(gin.getManufacturer().equals(manufacturer)){
                    System.out.println(gin.getBottle()+" | "+gin.getPrice()+" | "+gin.getSize());
                }
            }
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
