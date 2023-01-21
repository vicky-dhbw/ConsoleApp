package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws UnknownHostException {
        showManual();

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

    public static void showManual(){
        System.out.println();
        System.out.println("The following program can load, sort, encrypt data and offer the user to write script that the user can execute :)");
        System.out.println("Following are useful terminal commands: ");
        System.out.println("load data [filename]");
        System.out.println("sort data using algorithm [quick/merge/lambda]");
        System.out.println("encrypt/decrypt [filename]");
        System.out.println("start/execute macro [filename]");
        System.out.println("ENJOY YOUR GIN :)");
        System.out.println("-------------------------------------");
        System.out.println();

    }


}