import org.json.simple.JSONObject;

public class ForecastParser {

    public enum ForecastType{
        DAILY,
        HOURLY
    }
    
    private JSONObject jObject;
    private ForecastType fType;


    public ForecastParser(JSONObject jObject, ForecastType fType) {
        this.jObject = jObject;
        this.fType = fType;
    }

    public JSONObject getObject() {
        return jObject;
    }

    public void setObject(JSONObject jObject) {
        this.jObject = jObject;
    }

    public ForecastType getType() {
        return fType;
    }

    public void setType(ForecastType fType) {
        this.fType = fType;
    }

}
