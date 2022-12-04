import java.nio.file.Files;
import java.nio.file.Paths;

public class ErrorManager {

    public static boolean haveErrors(String[] args){
        if(args.length > 4){
            Main.printError("Too many arguments");
            return true;
        }
        else if(args[0].length() < 4){
            Main.printError("Not enough arguments");
            return true;
        }
        else if(args[0].length() != 32){
            Main.printError("Incorrect API Key length");
            return true;
        }
        else if(Files.notExists(Paths.get(args[1]))){
            Main.printError("Invalid path to config file");
            return true;
        }
        else if(args[2].equalsIgnoreCase("daily") || args[2].equalsIgnoreCase("hourly") ){
            Main.printError("Incorrect forecast type value");
            return true;
        }
        else if(!ConfigReader.containsCity(args[1], args[3])){
            Main.printError("Cannot find that city in config file");
            return true;
        }
        else return false;
    }


}
