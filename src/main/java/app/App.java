package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws UnknownHostException {

        ArrayList<String> manufacturers=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        String command = "";
        String hostName= InetAddress.getLocalHost().getHostName();

        do{
            try{
                System.out.print(System.getProperty("user.name")+"@"+hostName+"~ %  ");
                command=scanner.nextLine();

                if (command.isEmpty()) {
                    command="";
                }
                if(command.equals("quit")){
                    break;
                }

                CommandExecutor commandExecutor=new CommandExecutor(command);
                commandExecutor.executeCommand();

            }catch (Exception e){
                e.printStackTrace();
            }

        }while(!command.equals("quit"));

    }


}