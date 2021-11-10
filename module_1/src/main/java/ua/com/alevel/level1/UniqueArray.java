package ua.com.alevel.level1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UniqueArray {

    public static void parseStringToArr() {
        int arrSize = 0;

        String userInput = getUserInput();
        char[] charArr = new char[userInput.length()];
        userInput.getChars(0, userInput.length(), charArr, 0);

        for (int i = 0; i < userInput.length(); i++) {
            if (Character.isDigit(charArr[i])) arrSize++;
        }

        System.out.printf("Количество уникальных символов: %d", getUniqueNumbers(arrSize, userInput, charArr));
        System.out.println();
    }

    private static String getUserInput() {
        String userInput = "";
        System.out.println("Введите строку (в формате - 1 4 5 1 1 3):");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userInput = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return userInput;
    }

    private static int getUniqueNumbers(int arrSize, String string, char[] charArr) {
        int count = 0;
        char[] numbersArray = new char[arrSize];

        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(charArr[i])) {
                for (int j = 0; j <= count; j++) {
                    if (numbersArray[j] == charArr[i]) {
                        break;
                    } else {
                        if (j == count) {
                            numbersArray[count] = charArr[i];
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }
}


