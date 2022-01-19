package ua.com.alevel.names;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NamesController {
    public static void run() {
        BufferedReader reader;
        StringBuilder stringBuilder = new StringBuilder();
        {
            try {
                reader = new BufferedReader(new FileReader(
                        "module_2/src/main/java/ua/com/alevel/input/inputNames.txt"));
                while (reader.ready()) {
                    stringBuilder.append(reader.readLine()).append("\n");
                }
                reader.close();
                String[] sequences = stringBuilder.toString().split("\\s+|[\n]+");
                List<String> list = Arrays.stream(sequences).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() == 1)
                        .map(Map.Entry::getKey).collect(Collectors.toList());
                if (list.size() > 0) {
                    String firstUnique = list.get(list.size() - 1);
                    System.out.println("\t...first unique name: " + firstUnique);
                } else {
                    System.out.println("\t...0 unique names found");
                }

            } catch (FileNotFoundException fe) {
                System.out.println("[ERROR] Input file for names not found");
            } catch (IOException ioe) {
                System.out.println("[ERROR] Error occurred. Info: " + ioe.getMessage());
            }
        }
    }
}