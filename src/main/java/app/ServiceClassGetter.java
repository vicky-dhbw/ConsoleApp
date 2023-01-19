package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceClassGetter {

    private final List<Object> services=new ArrayList<>(Arrays.asList(
            new SortService(),
            new LoadService(),
            new MacroService(),
            new EncryptionDecryptionService()
    ));

    public Object getServiceClass(String command){
        String[] subcommands=command.split(" ");
        for(String subcommand:subcommands){
            for(Object service:services){
                if(service.getClass().getName().toLowerCase().contains(subcommand)){
                    return service;
                }
            }
        }
        return null;
    }
}
