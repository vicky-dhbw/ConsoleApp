package app;
import common.org.Gin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CommandExecutor {
    private CommandValidator commandValidator;
    private String command;

    private static List<Gin> manufacturers=new ArrayList<>();
    public CommandExecutor(String command){
        this.command=command;
        commandValidator=new CommandValidator();
    }

    public CommandValidator getCommandValidator() {
        return commandValidator;
    }

    public void setCommandValidator(CommandValidator commandValidator) {
        this.commandValidator = commandValidator;
    }

    public void executeCommand() throws IOException{
        Object service = commandValidator.getServiceObjectFromCommand(command);
        if (service == null) {
            System.out.println("command not found: "+command);
        }
        else{
            if(service.getClass()==LoadService.class){
                LoadService loadService=new LoadService();
                manufacturers= loadService.executeLoadService(command);
                if(manufacturers!=null){
                    System.out.println("data successfully loaded... :)");
                }
            } else if (service.getClass()==SortService.class) {
                if(manufacturers.size()!=0){
                    SortService sortService=new SortService();
                    sortService.executeSortService(manufacturers,command);
                }else {
                    System.out.println("load data first..");
                }

            } else if (service.getClass()== MacroService.class) {
                MacroService macroService=new MacroService();
                macroService.executeMacroService(command);

            } else if (service.getClass()==EncryptionDecryptionService.class) {
                EncryptionDecryptionService encryptionDecryptionService=new EncryptionDecryptionService();
                encryptionDecryptionService.executeCryptographicProcess(command);
            }
        }

    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
