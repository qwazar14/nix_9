package ua.com.alevel;

import ua.com.alevel.util.Navigation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    MathSet mathSet;
    MathSet ms;
    String[] stringArray;
    Number[] numberArray;
    String input;


    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            Navigation.printMainNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("x")) {
                    break;
                }
                runMainNavigation(position, reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void runMainNavigation(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;

        }
    }

    private void runInterfaceForMethods(BufferedReader reader){
        String position;

    }


    private void createMathSet() {
        mathSet = new MathSet();
    }

    private void createMathSetWithCapacity(BufferedReader reader) {
        System.out.println("Input capacity: ");
        int capacity = 0;
        try {
            capacity = Integer.parseInt(reader.readLine());
            if (capacity < 0) {
                System.out.println("Capacity need to be int and >0");
                return;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return;
        }
        mathSet = new MathSet(capacity);
    }

    private void createMathSetFromArray(BufferedReader reader) {
        System.out.println("Input int variables with Space. If you want to end - press Enter");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String[] stringArray = input.split(" ");
        Number[] numberArray = new Number[stringArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            try {
                numberArray[i] = Long.parseLong(stringArray[i]);
            } catch (RuntimeException e) {
                try {
                    numberArray[i] = Double.parseDouble(stringArray[i]);
                } catch (RuntimeException ex) {
                    System.out.println("Array can't handle non-numbers elements");
                    ex.printStackTrace();
                    return;
                }
            }
        }
        mathSet = new MathSet(numberArray);
    }

    private void createMathSetFromArrayVarargs(BufferedReader reader) {
        System.out.println("Введите численные значения массива через пробела, чтоб начать новый массив, \" +\n" +
                "                \"разделите массивы с помощью ';' , для конца ввода намите enter");
        System.out.println("Например:2 3;9 10;1 739 -5 0.25");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        stringArray = input.split(";");
        Number[][] array = new Number[stringArray.length][];
        for (int i = 0; i < stringArray.length; i++) {
            String[] subArray = stringArray[i].split(" ");
            numberArray = new Number[subArray.length];
            for (int j = 0; j < numberArray.length; j++) {
                numberArray[j] = parseNumber(subArray[j]);
            }
            array[i] = numberArray;
        }
        mathSet = new MathSet(array);
    }

    private Number parseNumber(String s) {
        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            return Double.parseDouble(s);
        }
    }

    private MathSet[] fromInputToVarargsMathsets(String input) {
        stringArray = input.split(";");
        MathSet[] mathSets = new MathSet[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            String[] subArray = stringArray[i].split(" ");
            numberArray = new Number[subArray.length];
            for (int j = 0; j < numberArray.length; j++) {
                numberArray[j] = parseNumber(subArray[j]);
            }
            mathSets[i] = new MathSet(numberArray);
        }
        return mathSets;
    }

    private MathSet fromInputMathset(String input) {
        String[] subArray = input.split(" ");
        numberArray = new Number[subArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = parseNumber(subArray[i]);
        }
        mathSet = new MathSet(numberArray);
        return mathSet;
    }

}
