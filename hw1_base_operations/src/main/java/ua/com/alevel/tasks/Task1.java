package ua.com.alevel.tasks;

import java.util.Scanner;

public class Task1 {

    public void task1SumOfNumbers() {
        int sum = 0;
        System.out.println("Input string");
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (Character.isDigit(symbol)) {
                int digit = Integer.parseInt(String.valueOf(symbol));
                sum += digit;
            }
        }
        System.out.println("Sum of digits is " + sum);
    }
}
