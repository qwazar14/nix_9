package ua.com.alevel.tasks;

import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public void task2CharCount() {
        System.out.println("Input string");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        char[] chars = line.toCharArray();
        Arrays.sort(chars);
        String s = String.valueOf(chars);
        for (int i = 0; i < chars.length; i += (s.lastIndexOf(chars[i]) - s.indexOf(chars[i]) + 1)) {
            if (Character.isLetter(chars[i])) {
                System.out.println(chars[i] + "-" + (s.lastIndexOf(chars[i]) - s.indexOf(chars[i]) + 1));
            }
        }
    }
}

