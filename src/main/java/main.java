import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static <T> void main(String[] args) {

//        if(ErrorManager.haveErrors(args)) return;

        ConfigReader cReader = new ConfigReader("config.txt");
        City city = cReader.getCityByName(cReader.getCities(),"Warsaw");
        if(city == null){
            printError("Cannot find that city in config file");
            return;
        }

        APIManager apiManager = new APIManager("5e25fd264a5455963a0f0ee4f0a6d43e", city.getLat(), city.getLon(), "hourly");
        String response = apiManager.getResponse();
        JSONObject mainObject = APIManager.getJSONFromString(response);



        ForecastParser fp = new ForecastParser(mainObject, "hourly");
        fp.printForecast();


    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
