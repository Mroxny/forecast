import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {

        // Major error checking
        if(ErrorManager.haveErrors(args)) return;

        // Read the configuration file and get a specific city
        ConfigReader cReader = new ConfigReader(args[1]);
        City city = cReader.getCityByName(cReader.getCities(),args[3]);
        if(city == null){
            printError("Cannot find that city in config file");
            return;
        }

        // Get response from the API as JSON
        APIManager apiManager = new APIManager(args[0], city.getLat(), city.getLon(), args[2]);
        String response = apiManager.getResponse();
        if(response == null) return;

        JSONObject mainObject = APIManager.getJSONFromString(response);

        if(mainObject.containsKey("cod")){
            printError("API response code "+ mainObject.get("cod"));
            return;
        }

        // Handle and print data from JSON
        ForecastParser fp = new ForecastParser(mainObject, args[2]);
        fp.printForecast();


    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
