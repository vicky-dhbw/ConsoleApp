package app;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandValidator {
    private static final ArrayList<String> allowedCommands=new ArrayList<>(Arrays.asList(
            "load data ",
            "sort data using algorithm ",
            "encrypt ",
            "decrypt ",
            "start macro ",
            "execute macro "
    ));

    public Object getServiceObjectFromCommand(String command){

       ServiceClassGetter serviceClassGetter=new ServiceClassGetter();

        for(String allowedCommand:allowedCommands){
            if(allowedCommand.contains(command)|| command.contains(allowedCommand)){
                CommandValidatorGetter validatorClass=new CommandValidatorGetter(command);
                ICommandValidator commandValidator=validatorClass.getCommandValidator();

                if(commandValidator!=null){
                    if(commandValidator.validate(command)){
                        return serviceClassGetter.getServiceClass(command);
                    }
                }

            }

        }

        return null;
    }




}
