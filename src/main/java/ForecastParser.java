import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForecastParser {

    private static final String TYPE_DAILY = "DAILY";
    private static final String TYPE_HOURLY = "HOURLY";
    private static final String TYPE_UNKNOWN = "UNKNOWN";



    private JSONObject jObject;
    private String fType;

    public ForecastParser(JSONObject jObject, String fType) {
        this.jObject = jObject;
        setType(fType);
    }

    public JSONObject getObject() {
        return jObject;
    }

    public void setObject(JSONObject jObject) {
        this.jObject = jObject;
    }

    public String getType() {
        return fType;
    }

    public void setType(String fType) {
        if(fType.equalsIgnoreCase(TYPE_DAILY)) this.fType = TYPE_DAILY;
        else if(fType.equalsIgnoreCase(TYPE_HOURLY)) this.fType = TYPE_HOURLY;
        else this.fType = TYPE_UNKNOWN;

    }

    public <T> List<T> getForecast(){
        switch (fType){
            case TYPE_DAILY:
                return (List<T>) getForecastDaily();

            case TYPE_HOURLY:
                return (List<T>) getForecastHourly();

            default:
                Main.printError("Unknown forecast type");
                return null;

        }
    }

    public List<Day> getForecastDaily(){
        List<Day> list = new ArrayList<Day>();
        JSONArray jArray = (JSONArray) jObject.get("daily");

        for(Object o: jArray){
            JSONObject object = (JSONObject) o;
            JSONObject temp = (JSONObject) object.get("temp");

            System.out.println(temp.get("min"));


        }


        return list;
    }

    public List<Hour> getForecastHourly(){
        List<Hour> list = new ArrayList<Hour>();

        return list;
    }

}
