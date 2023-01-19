package app;

public class EncryptDecryptCommandValidator implements ICommandValidator{
    @Override
    public boolean validate(String command) {
        String[] subCommands=command.split(" ");
        if(subCommands.length==2){
            return true;
        }
        System.out.println("try: encrypt/decrypt [filename]");
        return false;
    }
}
