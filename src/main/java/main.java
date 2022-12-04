import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static <T> void main(String[] args) {

        if(ErrorManager.haveErrors(args)) return;

        ConfigReader cReader = new ConfigReader(args[1]);
        City city = cReader.getCityByName(cReader.getCities(),args[3]);
        if(city == null){
            printError("Cannot find that city in config file");
            return;
        }

        System.out.println(args);

        APIManager apiManager = new APIManager(args[0], city.getLat(), city.getLon(), args[2]);
        String response = apiManager.getResponse();
        JSONObject mainObject = APIManager.getJSONFromString(response);

        if(mainObject.containsKey("cod")){
            printError("Invalid API response");
            return;
        }
        
        ForecastParser fp = new ForecastParser(mainObject, "hourly");
        fp.printForecast();


    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
