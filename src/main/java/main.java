import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("You have "+args.length+" arguments");


        APIManager apiManager = new APIManager("5e25fd264a5455963a0f0ee4f0a6d43e",33.44, 94.04, "daily");
        String response = apiManager.getResponse();
        JSONObject mainObject = apiManager.getJSONFromString(response);

        JSONArray testArray = (JSONArray) mainObject.get("daily");

            for(Object o: testArray){
                JSONObject testObj = (JSONObject) o;
                System.out.println(testObj.get("temp"));
            }

    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
