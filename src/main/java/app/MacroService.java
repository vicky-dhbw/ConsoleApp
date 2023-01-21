package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MacroService {


    public void executeMacroService(String command) throws IOException{
        String macroService = command.split(" ")[0];

        if(macroService.equals("start")){
            startMacroService(command);
        } else if (macroService.equals("execute")) {
            executeMacroScript(command);
        }

    }

    public void startMacroService(String command) throws IOException{

        String filename=command.split(" ")[2];
        File macroFile=new File(Configuration.INSTANCE.pathToDataDir+System.getProperty("file.separator")+filename);

        if(!FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir))){
            if(macroFile.createNewFile()){
                System.out.println("creating new fine named "+filename+" ......");
            }
        }
        System.out.println("-- to finish writing script type finish macro [filename] --");
        System.out.println("write your script below this line :)");
        BufferedWriter writer=new BufferedWriter(new FileWriter(macroFile.getPath(),true));
        String input="";
        Scanner scanner=new Scanner(System.in);

        do{
            input=scanner.nextLine();
            writer.write(input);
            writer.newLine();
        }while (!input.contains("finish macro "+filename));

        writer.close();
    }

    public void executeMacroScript(String command){

        String filename=command.split(" ")[2];
        if(FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir))){
            try{
                BufferedReader reader=new BufferedReader(new FileReader(Configuration.INSTANCE.pathToDataDir+System.getProperty("file.separator")+filename));
                String line;

                while((line=reader.readLine())!=null){
                    CommandExecutor commandExecutor=new CommandExecutor(line);
                    commandExecutor.executeCommand();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
