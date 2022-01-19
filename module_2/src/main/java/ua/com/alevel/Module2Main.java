package ua.com.alevel;


import ua.com.alevel.cities.CitiesController;
import ua.com.alevel.dates.DatesController;
import ua.com.alevel.names.NamesController;


public class Module2Main {
    public static void main(String[] args) {
        System.out.println("Task 1. Dates");
        DatesController.run();
        System.out.println("\t...done!");

        System.out.println("Task 2. Names");
        NamesController.run();
        System.out.println("\t...done!");

        System.out.println("Task 3. Cities");
        CitiesController.run();
        System.out.println("\t...done!");
    }
}
