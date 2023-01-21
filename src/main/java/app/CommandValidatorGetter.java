package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandValidatorGetter {
    private final String command;

    public CommandValidatorGetter(String command){
        this.command=command;
    }

    private final List<ICommandValidator> commandValidators=new ArrayList<>(Arrays.asList(
            new MacroCommandValidator(),
            new SortCommandValidator(),
            new EncryptDecryptCommandValidator(),
            new LoadCommandValidator()
    ));

    private ICommandValidator commandValidator;
    public ICommandValidator getCommandValidator(){
        String[] subcommands=command.split(" ");
        for(String subcommand:subcommands){
            for(ICommandValidator loaderClass:commandValidators){
                if(loaderClass.getClass().getName().toLowerCase().contains(subcommand)){
                    return loaderClass;
                }
            }
        }
        return null;
    }
}
