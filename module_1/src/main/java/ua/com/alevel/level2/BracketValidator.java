package ua.com.alevel.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BracketValidator {

    public static void run() {
        boolean a = isValid();
        System.out.println(a);
    }

    private static boolean isValid() {
        String s = getUserInput();
        int length = s.length();
        char[] array = s.toCharArray();
        if (length == 0) return true;
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < length; i++) {
            if (array[i] == '(' || array[i] == '{' || array[i] == '[') {
                stack.push(array[i]);
            }
            if (array[i] == ')' || array[i] == '}' || array[i] == ']') {
                if (stack.isEmpty()) return false;
                char temp = stack.pop();
                if ((temp != '(' || array[i] != ')') && (temp != '{' || array[i] != '}') && (temp != '[' || array[i] != ']')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static String getUserInput() {
        String userInput = "";
        System.out.println("Введите строку");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userInput = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return userInput;
    }
}
