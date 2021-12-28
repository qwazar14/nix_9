package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static ua.com.alevel.util.Navigation.*;
import static ua.com.alevel.util.UserInputController.*;

public class MathSetController {
    MathSet mathSet;
    MathSet ms;
    String[] stringArray;
    Number[] numberArray;
    String input;

    public void run1() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printNavigationConstruct();
        System.out.println("select your option");
        String position;
        try {
            while ((position = reader.readLine()) != null) {
                setNavigationConstruct(position, reader);
                position = reader.readLine();
                if (position.equals("x")) {
                    System.exit(0);
                }
                setNavigationConstruct(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void run() throws IOException {
        String position = getUserInput();
        BufferedReader reader = getUserBufferedReader();
        do{
            setNavigationAddJoinIntersection(position, reader);
        }
        while (!position.equals("x"));
    }

    private void setNavigationConstruct(String position, BufferedReader reader) throws IOException {
        printNavigationConstruct();
        switch (position) {
            case "1" -> {
                createMathSet();
                System.out.println(Arrays.toString(mathSet.getMathSet()));

            }
            case "2" -> {
                createMathSetWithCapacity(reader);
                System.out.println(Arrays.toString(mathSet.getMathSet()));

            }
            case "3" -> {
                createMathSetFromArray(reader);
                System.out.println(Arrays.toString(mathSet.getMathSet()));

            }
            case "4" -> {
                createMathSetFromArrayVarargs(reader);
                System.out.println(Arrays.toString(mathSet.getMathSet()));

            }
            case "5" -> {
                System.out.println("введите элементы матсета через пробел, чтоб завершить, нажмите Enter");
                try {
                    input = reader.readLine();
                    mathSet = new MathSet(fromInputMathset(input));
                } catch (NumberFormatException | IOException e) {
                    System.out.println("неправильно введены данные");
                }
            }
            case "6" -> {
                System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
                        "разделите массивы с помощью ';' , для конца ввода намите enter");
                System.out.println("Например:2 3;9 10;1 739 -5 0.25");
                try {
                    input = reader.readLine();
                    mathSet = new MathSet(fromInputToVarargsMathsets(input));
                } catch (NumberFormatException | IOException e) {
                    System.out.println("неправильно введены данные");
                }
            }
            case "+" -> setNavigationAddJoinIntersection(position, reader);
            case "x" -> System.exit(0);
        }
    }

    private void setNavigationAddJoinIntersection(String position, BufferedReader reader) throws IOException {
        printNavigationAddJoinIntersection();
        switch (position) {

            case "1" -> {
                System.out.println("введите число");
                try {
                    mathSet.add(parseToDouble(reader.readLine()));
                } catch (NumberFormatException e) {
                    System.out.println("неправильно введены данные");
                }
            }
            case "2" -> {
                System.out.println("введите элементы массива через пробел, чтоб завершить, нажмите Enter");
                input = reader.readLine();
                stringArray = input.split(" ");
                numberArray = new Number[stringArray.length];
                try {
                    for (int i = 0; i < numberArray.length; i++) {
                        numberArray[i] = parseToDouble(stringArray[i]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("неправильно введены данные");
                    break;
                }
                mathSet.add(numberArray);
            }
            case "3" -> {
                System.out.println("введите элементы нового матсета через пробел, чтоб завершить, нажмите Enter");
                input = reader.readLine();
                stringArray = input.split(" ");
                numberArray = new Number[stringArray.length];
                try {
                    for (int i = 0; i < numberArray.length; i++) {
                        numberArray[i] = parseToDouble(stringArray[i]);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("неправильно введены данные");
                    break;
                }
                ms = new MathSet(numberArray);
                mathSet.join(ms);
            }
            case "4" -> {
                System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
                        "разделите массивы с помощью ';' , для конца ввода намите enter");
                System.out.println("Например:2 3;9 10;1 739 -5 0.25");
                input = reader.readLine();
                mathSet.join(fromInputToVarargsMathsets(input));
            }
            case "5" -> {
                System.out.println("введите элементы нового матсета через пробел, чтоб завершить, нажмите Enter");
                input = reader.readLine();
                stringArray = input.split(" ");
                numberArray = new Number[stringArray.length];
                try {
                    for (int i = 0; i < numberArray.length; i++) {
                        numberArray[i] = parseToDouble(stringArray[i]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("неправильно введены данные");
                    break;
                }
                ms = new MathSet(numberArray);
                mathSet.intersection(ms);
            }
            case "6" -> {
                System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
                        "разделите массивы с помощью ';' , для конца ввода намите enter");
                System.out.println("Например:2 3;9 10;1 739 -5 0.25");
                input = reader.readLine();
                try {
                    mathSet.intersection(fromInputToVarargsMathsets(input));
                } catch (NumberFormatException e) {
                    System.out.println("неправильно введены данные");
                }
            }
            case "-" -> setNavigationConstruct(position, reader);
            case "+" -> setNavigationSort(position, reader);
            case "x" -> System.exit(0);
        }
    }

    private void setNavigationSort(String position, BufferedReader reader) throws IOException {
        printNavigationSort();
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("чтоб создать динамический MathSet с начальным размером 0 нажмите 1");
        System.out.println("чтоб создать MathSet с опредеоенным размером нажмите 2");
        System.out.println("чтоб создать динамический MathSet из массива нажмите 3");
        System.out.println("чтоб создать динамический MathSet из нескольких массивов нажмите 4");
        System.out.println("чтоб создать динамический MathSet из MathSet'a нажмите 5");
        System.out.println("чтоб создать динамический MathSet из MathSet'ов5 нажмите 6");
        System.out.println("чтоб добавить элемент, нажмите 7");
        System.out.println("чтоб добавить несколько элементов нажмите 8");
        System.out.println("чтоб найти объединение с другим Mathset'ом нажмите 9");
        System.out.println("чтоб найти объединение с другими Mathset'ами нажмите 10");
        System.out.println("чтоб найти пересечение с другим Mathset'ом нажмите 11");
        System.out.println("чтоб найти пересечение с другими Mathset'ами нажмите 12");
        System.out.println("чтоб отсортировать MathSet по убыванию нажмите 13");
        System.out.println("чтоб отсортировать часть MathSet'а по убыванию нажмите 14");
        System.out.println("чтоб отсортировать часть MathSet'а по убыванию, начиная с некоторого значения нажмите 15");
        System.out.println("чтоб отсортировать MathSet по возрастанию нажмите 16");
        System.out.println("чтоб отсортировать часть MathSet'а по возрастанию нажмите 17");
        System.out.println("чтоб отсортировать часть MathSet'а по возрастанию, начиная с некоторого значения нажмите 18");
        System.out.println("чтоб найти элемент по индексу, нажмите 19");
        System.out.println("чтоб найти минимальное значение, нажмите 20");
        System.out.println("чтоб найти максимальное значение, нажмите 21");
        System.out.println("чтоб найти среднее значение, нажмите 22");
        System.out.println("чтоб найти медиану выборки, нажмите 23");
        System.out.println("чтоб очистить MathSet нажмите 24");
        System.out.println("чтоб вырезать из MathSet'а часть нажмите 25");
        System.out.println("чтоб очистить из MathSet'а элементы массива нажмите 26");
        System.out.println("чтоб выйти нажмите 0");
        System.out.println();
    }

//    private void go(String position, BufferedReader reader) {
//        try {
//            switch (position) {
//                case "1":
//                    createMathSet();
//                    break;
//                case "2":
//                    createMathSetWithCapacity(reader);
//                    break;
//                case "3":
//                    createMathSetFromArray(reader);
//                    break;
//                case "4":
//                    createMathSetFromArrayVarargs(reader);
//                    break;
//                case "5":
//                    System.out.println("введите элементы матсета через пробел, чтоб завершить, нажмите Enter");
//                    try {
//                        input = reader.readLine();
//                        mathSet = new MathSet(fromInputMathset(input));
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                    }
//                    break;
//                case "6":
//                    System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
//                            "разделите массивы с помощью ';' , для конца ввода намите enter");
//                    System.out.println("Например:2 3;9 10;1 739 -5 0.25");
//                    try {
//                        input = reader.readLine();
//                        mathSet = new MathSet(fromInputToVarargsMathsets(input));
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                    }
//                    break;
//                case "7":
//                    System.out.println("введите число");
//                    try {
//                        mathSet.add(parseNumber(reader.readLine()));
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                    }
//                    break;
//                case "8":
//                    System.out.println("введите элементы массива через пробел, чтоб завершить, нажмите Enter");
//                    input = reader.readLine();
//                    stringArray = input.split(" ");
//                    numberArray = new Number[stringArray.length];
//                    try {
//                        for (int i = 0; i < numberArray.length; i++) {
//                            numberArray[i] = parseNumber(stringArray[i]);
//                        }
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                        break;
//                    }
//                    mathSet.add(numberArray);
//                    break;
//                case "9":
//                    System.out.println("введите элементы нового матсета через пробел, чтоб завершить, нажмите Enter");
//                    input = reader.readLine();
//                    stringArray = input.split(" ");
//                    numberArray = new Number[stringArray.length];
//                    try {
//                        for (int i = 0; i < numberArray.length; i++) {
//                            numberArray[i] = parseNumber(stringArray[i]);
//                        }
//
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                        break;
//                    }
//                    ms = new MathSet(numberArray);
//                    mathSet.join(ms);
//                    break;
//                case "10":
//                    System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
//                            "разделите массивы с помощью ';' , для конца ввода намите enter");
//                    System.out.println("Например:2 3;9 10;1 739 -5 0.25");
//                    input = reader.readLine();
//                    mathSet.join(fromInputToVarargsMathsets(input));
//                    break;
//                case "11":
//                    System.out.println("введите элементы нового матсета через пробел, чтоб завершить, нажмите Enter");
//                    input = reader.readLine();
//                    stringArray = input.split(" ");
//                    numberArray = new Number[stringArray.length];
//                    try {
//                        for (int i = 0; i < numberArray.length; i++) {
//                            numberArray[i] = parseNumber(stringArray[i]);
//                        }
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                        break;
//                    }
//                    ms = new MathSet(numberArray);
//                    mathSet.intersection(ms);
//                    break;
//                case "12":
//                    System.out.println("Введите численные значения mathseta через пробела, чтоб начать новый массив, " +
//                            "разделите массивы с помощью ';' , для конца ввода намите enter");
//                    System.out.println("Например:2 3;9 10;1 739 -5 0.25");
//                    input = reader.readLine();
//                    try {
//                        mathSet.intersection(fromInputToVarargsMathsets(input));
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                    }
//                    break;
//                case "13":
//                    mathSet.sortDesc();
//                    break;
//                case "14":
//                    int firstIndex = 0;
//                    int lastIndex = 0;
//                    try {
//                        System.out.println("введите начальный индекс, начиная с 0");
//                        firstIndex = Integer.parseInt(reader.readLine());
//                        System.out.println("введите конечный индекс");
//                        lastIndex = Integer.parseInt(reader.readLine());
//                        mathSet.sortDesc(firstIndex, lastIndex);
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильный ввод данных");
//                    } catch (ArrayIndexOutOfBoundsException exception) {
//                        System.out.println("вы вышли за пределы массива");
//                    }
//                    break;
//                case "15":
//                    Number value = 0;
//                    try {
//                        System.out.println("введите значение");
//                        value = parseNumber(reader.readLine());
//                    } catch (NoSuchElementException e) {
//                        System.out.println("такого элемента нет в матсете");
//                    } catch (NumberFormatException exception) {
//                        System.out.println("неправильный вод данных");
//                    }
//                    mathSet.sortDesc(value);
//                    break;
//                case "16":
//                    mathSet.sortAsc();
//                    break;
//                case "17":
//                    try {
//                        System.out.println("введите начальный индекс,начиная с 0");
//                        firstIndex = Integer.parseInt(reader.readLine());
//                        System.out.println("введите конечный индекс");
//                        lastIndex = Integer.parseInt(reader.readLine());
//                        mathSet.sortAsc(firstIndex, lastIndex);
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильный ввод данных");
//                    } catch (ArrayIndexOutOfBoundsException exception) {
//                        System.out.println("вы вышли за пределы массива");
//                    }
//                    break;
//                case "18":
//                    Number val = 0;
//                    try {
//                        System.out.println("введите значение");
//                        val = parseNumber(reader.readLine());
//                        mathSet.sortAsc(val);
//                    } catch (NoSuchElementException e) {
//                        System.out.println("такого элемента нет в матсете");
//                    } catch (NumberFormatException exception) {
//                        System.out.println("неправильный вод данных");
//                    }
//                    break;
//                case "19":
//                    int index = 0;
//                    try {
//                        System.out.println("введите индекс");
//                        index = Integer.parseInt(reader.readLine());
//                    } catch (NumberFormatException exception) {
//                        System.out.println("неправильный вод данных");
//                    }
//                    try {
//                        System.out.println(mathSet.get(index));
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("вы вышли за пределы матсета");
//                    }
//                    break;
//                case "20":
//                    System.out.println(mathSet.getMin());
//                    break;
//                case "21":
//                    System.out.println(mathSet.getMax());
//                    break;
//                case "22":
//                    System.out.println(mathSet.getAverage());
//                    break;
//                case "23":
//                    System.out.println(mathSet.getMedian());
//                    break;
//                case "24":
//                    mathSet.clear();
//                    break;
//                case "25":
//                    try {
//                        System.out.println("введите начальный индекс");
//                        firstIndex = Integer.parseInt(reader.readLine());
//                        System.out.println("введите конечный индекс");
//                        lastIndex = Integer.parseInt(reader.readLine());
//                        mathSet.cut(firstIndex, lastIndex);
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильный ввод данных");
//                    } catch (ArrayIndexOutOfBoundsException exception) {
//                        System.out.println("вы вышли за пределы массива");
//                    }
//                    break;
//                case "26":
//                    System.out.println("введите элементы нового матсета через пробел, чтоб завершить, нажмите Enter");
//                    input = reader.readLine();
//                    String[] subArray = input.split(" ");
//                    numberArray = new Number[subArray.length];
//                    try {
//                        for (int i = 0; i < numberArray.length; i++) {
//                            if (subArray[i] != null) {
//                                numberArray[i] = parseNumber(subArray[i]);
//                            }
//                        }
//                    } catch (NumberFormatException e) {
//                        System.out.println("неправильно введены данные");
//                        break;
//                    }
//                    mathSet.clear(numberArray);
//                    break;
//                case "0":
//                    System.exit(0);
//            }
//        } catch (NullPointerException e) {
//            System.out.println("сначала создайте матсет, используя пункты 1-6");
//        } catch (IndexOutOfBoundsException exception) {
//            System.out.println("вы вышли за пределы матсета");
//        } catch (IOException exception2) {
//            System.out.println("проблема с чтением файла");
//        }
//        if (mathSet != null) {
//            System.out.println(mathSet);
//        }
//        printNavigationConstruct();
////        runNavigation();
//    }

    public void createMathSet() {
        mathSet = new MathSet();
    }

    public void createMathSetWithCapacity(BufferedReader reader) {
        System.out.println("Введите численное значение capacity");
        int capacity = 0;
        try {
            capacity = Integer.parseInt(reader.readLine());
            if (capacity < 0) {
                System.out.println("capacity должно быть целочисленным и положительным");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("capacity должно быть целочисленным и положительным");
            return;
        } catch (IOException exception) {
            System.out.println("проблема с чтением строки");
            return;
        }
        mathSet = new MathSet(capacity);
    }

    public void createMathSetFromArray(BufferedReader reader) {
        System.out.println("Введите целочисленные значения массива через пробела, для конца ввода намите enter");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("неправильный ввод данных");
            return;
        }
        String stringArray[] = input.split(" ");
        Number numberArray[] = new Number[stringArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            try {
                numberArray[i] = Long.parseLong(stringArray[i]);
            } catch (RuntimeException e) {
                try {
                    numberArray[i] = Double.parseDouble(stringArray[i]);
                } catch (RuntimeException exception) {
                    System.out.println("Массив должен сожержать только числа");
                    return;
                }
            }
        }
        mathSet = new MathSet(numberArray);
    }

    public void createMathSetFromArrayVarargs(BufferedReader reader) {
        System.out.println("Введите численные значения массива через пробела, чтоб начать новый массив, " +
                "разделите массивы с помощью ';' , для конца ввода намите enter");
        System.out.println("Например:2 3;9 10;1 739 -5 0.25");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("неправильный ввод данных");
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

    private Number parseNumber(String input) {
        try {
            return Long.parseLong(input);
        } catch (Exception e) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                throw exception;
            }
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
        for (int j = 0; j < numberArray.length; j++) {
            numberArray[j] = parseNumber(subArray[j]);
        }
        mathSet = new MathSet(numberArray);
        return mathSet;
    }
}
