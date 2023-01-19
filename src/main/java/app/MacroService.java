package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MacroService {


    public void executeMacroService(String command) throws IOException, InterruptedException {
        String macroService = command.split(" ")[0];

        if(macroService.equals("start")){
            startMacroService(command);
        }

    }

    public void startMacroService(String command) throws IOException, InterruptedException {
        StringBuilder scriptCommands=new StringBuilder();
        File macroFile=new File(" ");
        String filename=command.split(" ")[2];
        if(!FileSearcher.searchFile(filename,new File(Configuration.INSTANCE.pathToDataDir))){
            macroFile=new File(Configuration.INSTANCE.pathToDataDir+System.getProperty("file.separator")+filename);
            macroFile.createNewFile();
            System.out.println("creating new script named "+filename);
        }

        BufferedWriter writer=new BufferedWriter(new FileWriter(macroFile.getPath(),true));
        System.out.println(macroFile.getPath());
        String input="";
        Scanner scanner=new Scanner(System.in);

        do{
            while(scanner.hasNext()){
                input=scanner.nextLine();
                scriptCommands.append(input);
            }

        }while (!input.contains("finish macro "+filename));

        writer.write(String.valueOf(scriptCommands));
        writer.close();
    }

}
