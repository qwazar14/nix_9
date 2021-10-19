package ua.com.alevel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choose;
        String line;
        String part;
        while (true) {
            printInstructions();
            choose = input.nextInt();
            line = input.nextLine();
            switch (choose){
                case 1:
                    System.out.println(ReverseString.reverse(line));
                    break;
//                case 2:
//                    part = input.nextLine();
//                    System.out.println(ReverseString.reverse1(line,part));
////                    System.out.println(ReverseString2.reverseBySubstring(line,part));
                default:
                    System.out.println("Enter a number between 1 and 7");
                    break;
            }
        }
    }


    public static void printInstructions() {
        System.out.println("\n1. Reverse all string\n" +
                "2. Reverse part in string\n" +
                "3. Reverse part by index in string\n" +
                "4. Exit");
    }
}


