public class Day{
    private String date;
    private float minTemp;
    private float maxTemp;
    private float avgTemp;
    private float probOfPrec;

    public Day(String date, float minTemp, float maxTemp, float avgTemp, float probOfPrec) {
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.avgTemp = avgTemp;
        this.probOfPrec = probOfPrec;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(float avgTemp) {
        this.avgTemp = avgTemp;
    }

    public float getProbOfPrec() {
        return probOfPrec;
    }

    public void setProbOfPrec(float probOfPerc) {
        this.probOfPrec = probOfPerc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date+", "+minTemp+", "+maxTemp+", "+avgTemp+", "+probOfPrec;
    }
}
