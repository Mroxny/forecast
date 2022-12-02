import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("You have "+args.length+" arguments");

        // test API URL: https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=hourly,daily&appid=5e25fd264a5455963a0f0ee4f0a6d43e

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=52.2297&lon=21.0122&exclude=current,minutely,hourly,alerts&units=metric&appid=5e25fd264a5455963a0f0ee4f0a6d43e");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode != 200) printError("HTTP response code "+responseCode);
            else{
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.toString());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parser = new JSONParser();
                Object parsedObject = parser.parse(String.valueOf(informationString));
                JSONObject jsonObject = (JSONObject) parsedObject;

                System.out.println(jsonObject.get("timezone"));

                JSONArray testArray = (JSONArray) jsonObject.get("daily");

                for(Object o: testArray){
                    JSONObject testObj = (JSONObject) o;
                    System.out.println(testObj.get("temp"));
                }

            }
        } catch (IOException e) {
            printError("Can't connect to the URL");
        } catch (ParseException e) {
            printError("Cannot parse JSON info");

        }

    }

    public static void printError(String msg){
        System.out.println("[ERROR]: "+msg);
    }
    
}
