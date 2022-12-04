import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static <T> void main(String[] args) {


        APIManager apiManager = new APIManager("5e25fd264a5455963a0f0ee4f0a6d43e",33.44, 94.04, "hourly");
        String response = apiManager.getResponse();
        JSONObject mainObject = APIManager.getJSONFromString(response);



        ForecastParser fp = new ForecastParser(mainObject, "hourly");
        fp.printForecast();


    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
