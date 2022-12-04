import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
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

    public void printForecast(){
        switch (fType){
            case TYPE_DAILY:
                for(Day d: getForecastDaily()) System.out.println(d);
                break;

            case TYPE_HOURLY:
                for(Hour h: getForecastHourly()) System.out.println(h);
                break;
            default:
                Main.printError("Unknown forecast type");
        }
    }

    public List<Day> getForecastDaily(){
        List<Day> list = new ArrayList<Day>();
        JSONArray jArray = (JSONArray) jObject.get("daily");

        for(Object o: jArray){
            JSONObject object = (JSONObject) o;
            JSONObject temp = (JSONObject) object.get("temp");

            String date = parseDate((Long) object.get("dt"), "dd.MM.yyyy");

            Number min = (Number) temp.get("min");
            Number max = (Number)temp.get("max");

            Number morn = (Number) temp.get("morn");
            Number day = (Number) temp.get("day");
            Number eve = (Number) temp.get("eve");
            Number night = (Number) temp.get("night");
            float avg = (morn.floatValue() + day.floatValue() + eve.floatValue() + night.floatValue()) / 4;

            Number pop = (Number) object.get("pop");
            pop= pop.floatValue() * 100;

            list.add(new Day(date, min.floatValue(), max.floatValue(), avg, pop.floatValue()));
        }
        return list;
    }

    public List<Hour> getForecastHourly(){
        List<Hour> list = new ArrayList<Hour>();
        JSONArray jArray = (JSONArray) jObject.get("hourly");

        for(Object o: jArray){
            JSONObject object = (JSONObject) o;
            String date = parseDate((Long) object.get("dt"), "dd.MM.yyyy HH:mm");

           Number temp = (Number) object.get("temp");
           Number pop = (Number) object.get("pop");
           pop= pop.floatValue() * 100;

           list.add(new Hour(date, temp.floatValue(), pop.floatValue()));
        }
        return list;
    }

    public static String parseDate(long timeStamp, String pattern){
        java.util.Date date = new java.util.Date((long)timeStamp*1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(date);
    }

}
