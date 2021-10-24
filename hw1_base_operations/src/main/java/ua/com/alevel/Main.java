package ua.com.alevel;

import ua.com.alevel.tasks.Task1;
import ua.com.alevel.tasks.Task2;
import ua.com.alevel.tasks.Task3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. Parsing string and getting the sum of all numbers");
        System.out.println("2. Parsing string and getting the count of each letter");
        System.out.println("3. Lessons timetable");
        System.out.println("    Select task:");
        Scanner input = new Scanner(System.in);
        String operationType = input.nextLine();

        switch (operationType) {
            case "1":
                Task1 task1SumOfNumbers = new Task1();
                task1SumOfNumbers.task1SumOfNumbers();
                break;
            case "2":
                Task2 task2CharCount = new Task2();
                task2CharCount.task2CharCount();
                break;
            case "3":
                Task3 task3LessonsTimetable = new Task3();
                task3LessonsTimetable.task3LessonsTimetable();
                break;
            default:
                System.out.println("Only 1, 2, 3 available. Exiting...");
        }
        input.close();
    }
}
