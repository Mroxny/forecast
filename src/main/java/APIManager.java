import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class APIManager {
    private static final String URL_PREFIX = "https://api.openweathermap.org/data/2.5/onecall?";

    private String key;
    private double lat;
    private double lon;
    private String units;
    private String[] type;
    private String[] typesToExclude = {"current","minutely","hourly","daily","alerts"};


    public APIManager(String key,double lat, double lon, String ... type) {
        this.key = key;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.units = "metric";
    }

    public String getLat() {
        return "lat="+lat;
    }

    public String getLon() {
        return "lon="+lon;
    }

    public String getKey() {
        return "appid="+key;
    }

    public String getUnits() {
        return "units="+units;
    }

    public String getExcluded(){
        List<String> list = new ArrayList<String>(Arrays.asList(typesToExclude));
        for(String t : type){
            list.remove(t);
        }
        typesToExclude = list.toArray(new String[0]);

        return "exclude="+String.join(",",typesToExclude);
    }

    public JSONObject getJSONFromString(String data){
        try{
            JSONParser parser = new JSONParser();
            Object parsedObject = parser.parse(data);


            return (JSONObject) parsedObject;
        }
        catch (ParseException e) {
            Main.printError("Invalid API response");
            return null;
        }
    }

    public String getResponse(){
        String apiRequest = URL_PREFIX+String.join("&",getLat(), getLon(), getExcluded(), getUnits(), getKey());
        try {
            URL url = new URL(apiRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if(responseCode != 200){
                Main.printError("HTTP response code "+responseCode);
                return null;
            }
            else{
                StringBuilder data = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.toString());

                while (scanner.hasNext()) {
                    data.append(scanner.nextLine());
                }
                scanner.close();

                return String.valueOf(data);
            }
        } catch (IOException e) {
           Main.printError("Can't connect to the URL");
           return null;
        }
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setType(String ... type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "APIManager{" +
                "key='" + key + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", units='" + units + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
