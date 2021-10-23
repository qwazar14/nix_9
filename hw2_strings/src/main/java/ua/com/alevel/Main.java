package ua.com.alevel;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    while (true) {
      Scanner input = new Scanner(System.in);
      String operationType, line;
      printInstructions();
      operationType = input.nextLine();

      switch (operationType) {
        case "1" -> {
          System.out.println("Please input the line you want to be reversed");
          line = input.nextLine();
          line = StringReverseUtil.reverse(line);
          System.out.println(line);
        }
        case "2" -> {
          System.out.println("Please input the line");
          line = input.nextLine();
          System.out.println("Please input the part");
          String part = input.nextLine();
          line = StringReverseUtil.reverse(line, part);
          System.out.println(line);
        }
        case "3" -> {
          System.out.println("Please input the line");
          line = input.nextLine();
          System.out.println("Please input first index");
          int indexFirst = input.nextInt();
          System.out.println("Please input second index");
          int indexLast = input.nextInt();
          line = StringReverseUtil.reverse(line, indexFirst, indexLast);
          System.out.println(line);
        }
        case "q" -> {
          System.out.println("Exiting...");
          System.exit(0);
        }
      }
    }
  }

  public static void printInstructions() {
    String title = "hw2_strings. Made by Maksym Grebeniuk. NIX 9\n";
    String description =
      "\t1. Reverse all string\n" +
      "\t2. Reverse part in string\n" +
      "\t3. Reverse part by index in string\n" +
      "\tq. Exit\n";

    System.out.println(title+description);
    System.out.print("input: ");
  }
}
