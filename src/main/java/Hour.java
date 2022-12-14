public class Hour {
    private String date;
    private float temp;
    private float precipitation;

    public Hour(String date, float temp, float precipitation) {
        this.date = date;
        this.temp = temp;
        this.precipitation = precipitation;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(float precipitation) {
        this.precipitation = precipitation;
    }

    @Override
    public String toString() {
        return date+", "+temp+", "+precipitation;
    }
}
