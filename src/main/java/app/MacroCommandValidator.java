package app;

public class MacroCommandValidator implements ICommandValidator{
    @Override
    public boolean validate(String command) {
        String[] subCommands=command.split(" ");
        if(subCommands.length==3){
            return true;
        }
        System.out.println("try: start/finish/execute macro [filename.ext]");
        return false;
    }
}
