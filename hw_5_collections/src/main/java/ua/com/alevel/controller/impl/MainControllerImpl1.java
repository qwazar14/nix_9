//package ua.com.alevel.controller.impl;
//
//import ua.com.alevel.MathSet;
//import ua.com.alevel.controller.MainController;
//import ua.com.alevel.util.Navigation;
//import ua.com.alevel.util.UserInputController;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//
//public class MainControllerImpl1 implements MainController {
//
//    MathSet mathSet;
//    MathSet ms;
//    String[] stringArray;
//    Number[] numberArray;
//    String input;
//
//
//    public static void run() {
//        UserInputController input = new UserInputController();
//        Navigation.printNavigationConstruct();
//        Navigation.printNavigationAddJoinIntersection();
//        Navigation.printNavigationSort();
//        Navigation.printNavigationGet();
//        Navigation.printNavigationClearCut();
//    }
//
//    private void setNavigationConstruct(String position) {
//        switch (position) {
//            case "1" -> createMathSet();
//            case "2" -> {
//                String in = "";
//                createMathSetWithCapacity(in);
//            }
//            case "3" -> createMathSetFromArray(reader);
//            case "4" -> createMathSetFromArrayVarargs(reader);
//            case "5" -> {
//                System.out.println("введите элементы матсета через пробел, чтоб завершить, нажмите Enter");
//                try {
//                    String input = reader.readLine();
//                    mathSet = new MathSet(fromInputMathset(input));
//                } catch (NumberFormatException e) {
//                    System.out.println("неправильно введены данные");
//                }
//            }
//            case "6" -> {
//                System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
//                        "разделите массивы с помощью ';' , для конца ввода намите enter");
//                System.out.println("Например:2 3;9 10;1 739 -5 0.25");
//                try {
//                    input = reader.readLine();
//                    mathSet = new MathSet(fromInputToVarargsMathsets(input));
//                } catch (NumberFormatException e) {
//                    System.out.println("неправильно введены данные");
//                }
//            }
//            case "x" -> System.exit(0);
//        }
//    }
//
//    public void createMathSet() {
//        mathSet = new MathSet();
//    }
//
//    public void createMathSetWithCapacity(BufferedReader reader) {
//        System.out.println("Введите численное значение capacity");
//        int capacity = 0;
//        try {
//            capacity = Integer.parseInt(reader.readLine());
//            if (capacity < 0) {
//                System.out.println("capacity должно быть целочисленным и положительным");
//                return;
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("capacity должно быть целочисленным и положительным");
//            return;
//        } catch (IOException exception) {
//            System.out.println("проблема с чтением строки");
//            return;
//        }
//        mathSet = new MathSet(capacity);
//    }
//
//    public void createMathSetFromArray(BufferedReader reader) {
//        System.out.println("Введите целочисленные значения массива через пробел, для конца ввода намите enter");
//        String input = null;
//        try {
//            input = reader.readLine();
//        } catch (IOException e) {
//            System.out.println("неправильный ввод данных");
//            return;
//        }
//        String[] stringArray = input.split(" ");
//        Number[] numbers = new Number[stringArray.length];
//        for (int i = 0; i < numbers.length; i++) {
//            try {
//                numbers[i] = Long.parseLong(stringArray[i]);
//            } catch (RuntimeException e) {
//                try {
//                    numbers[i] = Double.parseDouble(stringArray[i]);
//                } catch (RuntimeException exception) {
//                    System.out.println("Массив должен сожержать только числа");
//                    return;
//                }
//            }
//        }
//        mathSet = new MathSet(numbers);
//    }
//
//    public void createMathSetFromArrayVarargs(BufferedReader reader) {
//        System.out.println("Введите численные значения массива через пробела, чтоб начать новый массив, " +
//                "разделите массивы с помощью ';' , для конца ввода намите enter");
//        System.out.println("Например:2 3;9 10;1 739 -5 0.25");
//        String input = null;
//        try {
//            input = reader.readLine();
//        } catch (IOException e) {
//            System.out.println("неправильный ввод данных");
//            return;
//        }
//        stringArray = input.split(";");
//        Number[][] array = new Number[stringArray.length][];
//        for (int i = 0; i < stringArray.length; i++) {
//            String[] subArray = stringArray[i].split(" ");
//            numberArray = new Number[subArray.length];
//            for (int j = 0; j < numberArray.length; j++) {
//                numberArray[j] = parseNumber(subArray[j]);
//            }
//            array[i] = numberArray;
//        }
//        mathSet = new MathSet(array);
//    }
//
//    private Number parseNumber(String input) {
//        try {
//            return Long.parseLong(input);
//        } catch (Exception e) {
//            return Double.parseDouble(input);
//        }
//    }
//
//}
