import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConfigReader {
    public static boolean containsCity(String path, String cityName) {
        try {
            File file = new File("Student.txt");
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
