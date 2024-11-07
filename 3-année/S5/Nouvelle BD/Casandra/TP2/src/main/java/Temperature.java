public class Temperature {
    String sensor, date, timestamp;
    double value;

    public Temperature(String data) {
        String[] tab = data.split(",");
        sensor = tab[0];
        date = tab[2].split(" ")[0];
        timestamp = tab[2];
        value = Double.parseDouble(tab[1]);
    }
    public String getSensor() {
        return sensor;
    }

    public String getDate() {
        return date;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getValue(){
        return value;
    }
}
