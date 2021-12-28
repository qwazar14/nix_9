package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputController {

    public static String getUserInput() {
        String userInput = "";
        System.out.print("Ваш выбор: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userInput = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return userInput;
    }

    public static BufferedReader getUserBufferedReader() {
        String userInput = "";
        System.out.print("Ваш выбор: ");

        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static double parseToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException n) {
            System.out.println("Ошибка. Принимаются только цифры!");
            return 0.0;
        }
    }

}
