package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MathSetController {

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private MathSet mathSet;

    public void run() throws IOException {
        while (true) {
            startApp();
        }
    }

    private void startApp() throws IOException {
        printNavigation();
        System.out.print("Enter your choice: ");
        String choice = input.readLine();
        switch (choice) {
            case "0" -> System.exit(0);

            case "1" -> {
                mathSet = new MathSet();
                System.out.println("Created empty MathSet with dynamic capacity");
            }
            case "2" -> {
                System.out.print("Enter capacity: ");
                int capacity = Integer.parseInt(input.readLine());
                mathSet = new MathSet(capacity);
                System.out.println("Created empty MathSet with fixed capacity");
            }
            case "3" -> {
                Number[] arr = getNumbers("Enter array of numbers by space: ");
                mathSet = new MathSet(arr);
                System.out.println("Created MathSet from array of numbers");
            }
            case "4" -> {
                System.out.print("Enter the number: ");
                Number number = Double.parseDouble(input.readLine());
                mathSet.add(number);
            }
            case "5" -> {
                Number[] arr = getNumbers("Enter numbers by space: ");
                mathSet.add(arr);
            }
            case "6" -> {
                Number[] arr = getNumbers("Enter mathSet by space: ");
                MathSet temp = new MathSet(arr);
                mathSet.join(temp);
            }
            case "7" -> {
                Number[] arr = getNumbers("Enter mathSet by space: ");
                MathSet temp = new MathSet(arr);
                mathSet.intersection(temp);
            }
            case "8" -> {
                mathSet.sortDesc();
                System.out.println("Sorted mathSet by descending");
            }
            case "9" -> {
                System.out.print("Enter first index: ");
                int firstIndex = Integer.parseInt(input.readLine());
                System.out.print("Enter last index: ");
                int lastIndex = Integer.parseInt(input.readLine());
                mathSet.sortDesc(firstIndex, lastIndex);
            }
            case "10" -> {
                System.out.print("Enter number: ");
                int num = Integer.parseInt(input.readLine());
                mathSet.sortDesc(num);
            }
            case "11" -> mathSet.sortAsc();

            case "12" -> {
                System.out.print("Enter first index: ");
                int firstIndex = Integer.parseInt(input.readLine());
                System.out.print("Enter last index: ");
                int lastIndex = Integer.parseInt(input.readLine());
                mathSet.sortAsc(firstIndex, lastIndex);
            }
            case "13" -> {
                System.out.print("Enter number: ");
                int num = Integer.parseInt(input.readLine());
                mathSet.sortAsc(num);
            }
            case "14" -> {
                System.out.print("Enter index: ");
                int index = Integer.parseInt(input.readLine());
                System.out.println(mathSet.get(index));
            }
            case "15" -> System.out.println("MAX value: " + mathSet.getMax());

            case "16" -> System.out.println("MIN value: " + mathSet.getMin());

            case "17" -> System.out.println("AVERAGE value: " + mathSet.getAverage());

            case "18" -> System.out.println("MEDIAN value: " + mathSet.getMedian());

            case "19" -> {
                System.out.print("Enter first index: ");
                int firstIndex = Integer.parseInt(input.readLine());
                System.out.print("Enter last index: ");
                int lastIndex = Integer.parseInt(input.readLine());
                MathSet temp = mathSet.cut(firstIndex, lastIndex);
                System.out.println("Range that has been cut:");
                for (Number n : temp.toArray()) {
                    System.out.print(n + " ");
                }
            }
            case "20" -> mathSet.clear();

            case "21" -> {
                Number[] arr = getNumbers("Enter numbers by space: ");
                mathSet.clear(arr);
            }

            case "22" -> {
                System.out.println("MathSet:");
                for (Number n : mathSet.toArray()) {
                    System.out.print(n + " ");
                }
            }

            case "23" -> {
                System.out.print("Enter first index: ");
                int firstIndex = Integer.parseInt(input.readLine());
                System.out.print("Enter last index: ");
                int lastIndex = Integer.parseInt(input.readLine());
                System.out.println("A range in MathSet by indexes:");
                for (Number n : mathSet.toArray(firstIndex, lastIndex)) {
                    System.out.print(n + " ");
                }
            }
        }
    }

    private Number[] getNumbers(String s) throws IOException {
        System.out.print(s);
        String[] numbers = input.readLine().split(" ");
        Number[] arr = new Number[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Double.parseDouble(numbers[i]);
        }
        return arr;
    }

    private void printNavigation() {
        System.out.println();
        System.out.println("""
                0. Exit
                ======================CONSTRUCTORS======================
                1. Create empty MathSet with dynamic capacity
                2. Create empty MathSet with fixed capacity
                3. Create MathSet from array of numbers
                ==================ADD, JOIN, INTERSECTION===============
                4. Add number
                5. Add vararg of numbers
                6. Join with another MathSet
                7. Intersect with another MathSet
                =======================SORT=============================
                8. Sort MathSet by descending
                9. Sort a range of numbers in a MathSet by descending
                10. Sort starting from value in a MathSet by descending
                11. Sort MathSet by ascending
                12. Sort a range of numbers in a MathSet by ascending
                13. Sort starting from value in a MathSet by ascending
                =======================GET==============================
                14. Get number by index
                15. Get MAX value in MathSet
                16. Get MIN value in MathSet
                17. Get AVERAGE value in MathSet
                18. Get MEDIAN value in MathSet
                ====================CLEAR, CUT==========================
                19. Cut a range from MathSet by indexes
                20. Clear MathSet
                21. Clear from MathSet a range of numbers
                ====================DISPLAY=============================
                22. Display MathSet
                23. Display a range in MathSet by indexes
                """);
        System.out.println();
    }
}