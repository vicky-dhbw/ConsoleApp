package app;

public class LoadCommandValidator implements ICommandValidator{
    @Override
    public boolean validate(String command) {
        String[] subCommands=command.split(" ");
        if(subCommands.length==3){
            return true;
        }
        System.out.println("try: load data [filename]");
        return false;
    }
}
