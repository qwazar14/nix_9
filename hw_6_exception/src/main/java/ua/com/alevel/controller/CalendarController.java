package ua.com.alevel.controller;

import ua.com.alevel.calendar.Calendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.alevel.util.Printer.printDateFormat;
import static ua.com.alevel.util.Printer.printNavigation;
import static ua.com.alevel.util.Validator.isDate;

public class CalendarController {
    private static final String[] FORMATS = {"dd/mm/yy", "m/d/yyyy", "mmm-d-yy", "dd-mmm-yyyy"};
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int inputFormatMode = 1;
    int outputFormatMode = 1;

    public void run() throws IOException {
        printNavigation();
        String choice = reader.readLine();
        calendarMenu(choice);
    }

    private void calendarMenu(String choice) throws IOException {
        switch (choice) {
            case "0" -> System.exit(0);
            case "1" -> setInputFormatMode();
            case "2" -> setOutputFormatMode();
            case "3" -> findDifferences();
            case "4" -> addDate();
            case "5" -> subtractDate();
            case "6" -> sortDate();
        }
        run();
    }

    private void setInputFormatMode() throws IOException {
        printDateFormat();
        int mode = Integer.parseInt(reader.readLine());
        inputFormatMode = dateFormat(mode);
    }

    private void setOutputFormatMode() throws IOException {
        printDateFormat();
        int mode = Integer.parseInt(reader.readLine());
        outputFormatMode = dateFormat(mode);
    }

    private int dateFormat(int mode) {
        int defaultMode = 1;
        switch (mode) {
            case 1 -> {
                System.out.println("dd/mm/yy (example: 01/12/07)");
                return 1;
            }
            case 2 -> {
                System.out.println("m/d/yyyy (example: 3/4/0621)");
                return 2;
            }
            case 3 -> {
                System.out.println("mmm-d-yy (example: March-4-08)");
                return 3;
            }
            case 4 -> {
                System.out.println("dd-mmm-yyyy (example: 09-April-0789)");
                return 4;
            }
        }
        System.out.println("Default mode set:");
        System.out.println("dd-mmm-yyyy (example: 09-April-0789)");
        return defaultMode;
    }

    private void findDifferences() throws IOException {
        System.out.print("Input Date 1 (" + FORMATS[inputFormatMode - 1] + "): ");
        String strMinuend = reader.readLine().trim();
        if (isDate(strMinuend, inputFormatMode)) {
            System.out.print("Input Date 2 (" + FORMATS[inputFormatMode - 1] + "): ");
            String strSubtrahend = reader.readLine().trim();
            if (isDate(strSubtrahend, inputFormatMode)) {
                try {
                    Calendar minuend = new Calendar(strMinuend, FORMATS[inputFormatMode - 1]);
                    Calendar subtrahend = new Calendar(strSubtrahend, FORMATS[inputFormatMode - 1]);
                    long difference = Math.abs(minuend.time() - subtrahend.time());
                    System.out.println("Difference\n"
                            + "ms: " + difference + "\n"
                            + "secs: " + difference / 1000 + "\n"
                            + "mins: " + difference / 60000 + "\n"
                            + "hours: " + difference / 3600000 + "\n"
                            + "days: " + difference / 86400000);
                    if (minuend.getYear() > subtrahend.getYear()) {
                        if (minuend.timeWithoutYears() >= subtrahend.timeWithoutYears()) {
                            System.out.println("years: " + (minuend.getYear() - subtrahend.getYear()));
                        } else
                            System.out.println("years: " + (minuend.getYear() - subtrahend.getYear() - 1));
                    } else if (minuend.getYear() < subtrahend.getYear()) {
                        if (minuend.timeWithoutYears() <= subtrahend.timeWithoutYears()) {
                            System.out.println("years: " + (subtrahend.getYear() - minuend.getYear()));
                        } else
                            System.out.println("years: " + (subtrahend.getYear() - minuend.getYear() - 1));
                    } else System.out.println("years: 0");
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Incorrect date. Please try again");
                }
            }
        }
    }

    private void addDate() throws IOException {
        System.out.print("Input Date (" + FORMATS[inputFormatMode - 1] + "): ");
        String stringDate = reader.readLine().trim();
        if (isDate(stringDate, inputFormatMode)) {
            try {
                Calendar calendar = new Calendar(stringDate, FORMATS[inputFormatMode - 1]);
                long ms = 0;
                long sec = 0;
                long min = 0;
                long hour = 0;
                long day = 0;
                long year = 0;
                System.out.print("Input millis to add: ");
                String str = reader.readLine().trim();
                if (!str.isEmpty()) ms = Long.parseLong(str);
                System.out.print("Input seconds to add: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) sec = Long.parseLong(str);
                System.out.print("Input minutes to add: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) min = Long.parseLong(str);
                System.out.print("Input hours to add: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) hour = Long.parseLong(str);
                System.out.print("Input days to add: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) day = Long.parseLong(str);
                System.out.print("Input years to add: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) year = Long.parseLong(str);
                if (calendar.addTime(ms, sec, min, hour, day, year)) {
                    System.out.println(calendar.toString(FORMATS[outputFormatMode - 1]));
                } else System.out.println("Calendar is above limits. Please try again");
            } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
                System.out.println("Incorrect date. Please try again");
            }
        }
    }

    private void subtractDate() throws IOException {
        System.out.print("Input Date (" + FORMATS[inputFormatMode - 1] + "): ");
        String stringDate = reader.readLine().trim();
        if (isDate(stringDate, inputFormatMode)) {
            try {
                Calendar calendar = new Calendar(stringDate, FORMATS[inputFormatMode - 1]);
                long ms = 0;
                long sec = 0;
                long min = 0;
                long hour = 0;
                long day = 0;
                long year = 0;
                System.out.print("Input millis to subtract: ");
                String str = reader.readLine().trim();
                if (!str.isEmpty()) ms = Long.parseLong(str);
                System.out.print("Input seconds to subtract: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) sec = Long.parseLong(str);
                System.out.print("Input minutes to subtract: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) min = Long.parseLong(str);
                System.out.print("Input hours to subtract: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) hour = Long.parseLong(str);
                System.out.print("Input days to subtract: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) day = Long.parseLong(str);
                System.out.print("Input years to subtract: ");
                str = reader.readLine().trim();
                if (!str.isEmpty()) year = Long.parseLong(str);
                if (calendar.addTime(-ms, -sec, -min, -hour, -day, -year)) {
                    System.out.println(calendar.toString(FORMATS[outputFormatMode - 1]));
                } else System.out.println("Calendar is above limits. Please try again");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Incorrect date. Please try again");
            }
        }
    }

    private void sortDate() {
        {
            System.out.println("Input Dates (" + FORMATS[inputFormatMode - 1] + ") via 'Enter' (0 - Stop): ");
            List<Calendar> calendars = new ArrayList<>();
            try {
                while (true) {
                    String src = reader.readLine().trim();
                    if (!src.equals("0")) {
                        if (isDate(src, inputFormatMode)) {
                            calendars.add(new Calendar(src, FORMATS[inputFormatMode - 1]));
                        } else System.out.println("Incorrect date. Please try again");
                    } else break;
                }
                if (calendars.size() != 0) {
                    String finalOutputMethod = FORMATS[outputFormatMode - 1];
                    List<Calendar> sortedCalendars = calendars.stream()
                            .sorted(Comparator.comparing(Calendar::time)
                                    .thenComparing(Calendar::time))
                            .collect(Collectors.toList());
                    System.out.println("upwards:");
                    sortedCalendars.forEach(i -> System.out.println(i.toString(finalOutputMethod)));
                    System.out.println("downwards:");
                    for (int i = sortedCalendars.size() - 1; i >= 0; i--) {
                        System.out.println(sortedCalendars.get(i).toString(finalOutputMethod));
                    }
                }
            } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
                System.out.println("Incorrect date. Please try again");
            }
        }
    }
}
