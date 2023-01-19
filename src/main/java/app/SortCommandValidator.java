package app;

import java.util.ArrayList;
import java.util.Arrays;

public class SortCommandValidator implements ICommandValidator{

    public String algorithm;
    @Override
    public boolean validate(String command) {
        String[] subCommands=command.split(" ");
        if(subCommands.length==5){
            if(checkAlgorithmAvailability(subCommands[4])){
                algorithm=subCommands[4];
            }
            return true;
        }
        System.out.println("try: sort data using algorithm [algorithm name]");
        return false;
    }

    public boolean checkAlgorithmAvailability(String algorithm){
        ArrayList<String> algorithms=new ArrayList<>(Arrays.asList(
                "merge",
                "lambda",
                "quick"
        ));

        if(algorithms.contains(algorithm)){
            return true;
        }
        System.out.println("sorting algorithm "+algorithm+ " not available..");
        return false;
    }

}
