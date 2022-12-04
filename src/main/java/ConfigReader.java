import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigReader {

    private String path;

    public ConfigReader(String path) {
        this.path = path;
    }

    public List<City> getCities(){
        List<City> cities = new ArrayList<City>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            for(String s : lines){
                String[] values = s.split(",");
                if(values.length != 3){
                    Main.printError("Config file has a wrong format");
                    return cities;
                }
                cities.add(new City(values[0],Double.parseDouble(values[1]), Double.parseDouble(values[2])));
            }
            return cities;
        } catch (IOException e) {
            Main.printError("Cannot read config file");
            return null;
        }
    }

    public City getCityByName(List<City> list, String name){
        return list.stream().filter(o -> o.getName().equals(name)).findFirst().orElse(null);
    }

    public static boolean containsCity(String path, String cityName) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(cityName)) return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

}
