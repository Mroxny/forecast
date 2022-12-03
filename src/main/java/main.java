import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static <T> void main(String[] args) {
        System.out.println("You have "+args.length+" arguments");


        APIManager apiManager = new APIManager("5e25fd264a5455963a0f0ee4f0a6d43e",33.44, 94.04, "daily");
        String response = apiManager.getResponse();
        JSONObject mainObject = apiManager.getJSONFromString(response);



        ForecastParser fp = new ForecastParser(mainObject, "daily");

        fp.getForecastDaily();


    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
