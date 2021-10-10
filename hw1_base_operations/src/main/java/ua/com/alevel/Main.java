package ua.com.alevel;

import tasks.Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println(Main.class.getSimpleName());
        System.out.println("1. Parsing string and getting the sum of all numbers");
        System.out.println("SOON 2. Parsing string and getting the count of each letter");
        System.out.println("SOON 3. Lessons timetable");
        System.out.println("    Select task:");
        Scanner input = new Scanner(System.in);
        String operationType = input.nextLine();
//        System.out.println(operationType);
        switch (operationType) {
            case "1":
                Task1 task1SumOfNumbers = new Task1();
                task1SumOfNumbers.task1SumOfNumbers();
                break;
            case "2":
//                Task2 task2CharCount = new Task1();
//                task2SumOfNumbers.task2SumOfNumbers();
                System.out.println("2");
                break;
            case "3":
//                Task3 task3LessonsTimetable = new Task1();
//                task3SumOfNumbers.task3SumOfNumbers();
                System.out.println("3");
                break;
            default:
                System.out.println("Only 1, 2, 3 available. Exiting...");
        }


        input.close();
    }
}
