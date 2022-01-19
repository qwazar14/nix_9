package ua.com.alevel.dates;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class DatesController {
    public static void run() {
        BufferedReader reader;
        StringBuilder stringBuilder = new StringBuilder();

        {
            try {
                reader = new BufferedReader(new FileReader(
                        "module_2/src/main/java/ua/com/alevel/input/inputDates.txt"));
                while (reader.ready()) {
                    stringBuilder.append(reader.readLine()).append("\n");
                }
                reader.close();
                String[] sequences = stringBuilder.toString().split("\\s+|[\n]+");
                Set<String> uniqueDatas = new LinkedHashSet<>();
                for (String sequence : sequences) {
                    if (sequence.matches("\\d{0,2}/\\d{0,2}/\\d{0,4}")) {
                        String[] parts = sequence.split("/");
                        int month = Integer.parseInt(parts[1]);
                        int day = Integer.parseInt(parts[0]);
                        int year = Integer.parseInt(parts[2]);
                        if (isValid(month, day, year)) {
                            uniqueDatas.add(parts[2] + parts[1] + parts[0]);
                        }
                    } else if (sequence.matches("\\d{0,4}/\\d{0,2}/\\d{0,2}")) {
                        String[] parts = sequence.split("/");
                        int month = Integer.parseInt(parts[1]);
                        int day = Integer.parseInt(parts[2]);
                        int year = Integer.parseInt(parts[0]);
                        if (isValid(month, day, year)) {
                            uniqueDatas.add(parts[0] + parts[1] + parts[2]);
                        }
                    } else if (sequence.matches("\\d{0,2}-\\d{0,2}-\\d{0,4}")) {
                        String[] parts = sequence.split("-");
                        int month = Integer.parseInt(parts[0]);
                        int day = Integer.parseInt(parts[1]);
                        int year = Integer.parseInt(parts[2]);
                        if (isValid(month, day, year)) {
                            uniqueDatas.add(parts[2] + parts[0] + parts[1]);
                        }
                    }
                }
                try (BufferedWriter writter = new BufferedWriter(new FileWriter(
                        "module_2/src/main/java/ua/com/alevel/input/outputDates.txt"))) {
                    for (String value : uniqueDatas) {
                        writter.write(value + "\n");
                    }
                }

            } catch (FileNotFoundException fe) {
                System.out.println("[ERROR] Input file for dates not found");
            } catch (IOException ioe) {
                System.out.println("[ERROR] Error occurred. Info: " + ioe.getMessage());
            }
        }
    }


    private static boolean isValid(int month, int day, int year) {
        if (month < 0 || day < 0 || year < 0) {
            return false;
        }
        if ((month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) && day > 31) {
            return false;
        } else if ((month == 4 | month == 6 | month == 9 | month == 11) && day > 30) {
            return false;
        } else if (isBissextile(year) && month == 2 && day > 29) {
            return false;
        } else return isBissextile(year) || month != 2 || day <= 28;
    }

    private static boolean isBissextile(int year) {
        if (year % 400 == 0) {
            return true;
        } else return year % 4 == 0 && year % 100 != 0;
    }
}
