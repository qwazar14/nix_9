package ua.com.alevel.util;

public class PrintUIUtil {
    public static void printMainNavigation(){
        System.out.println("""
                HW7 IO/NIO
                ==========================
                    1. Category menu
                    2. Item menu
                    0. Exit
                Enter your choice:
                """);
    }

    public static void printCategoryMenu(){

        System.out.println("""
                HW7 IO/NIO - Category menu
                ==========================
                    1. Create
                    2. Update
                    3. Delete
                    4. FindById
                    5. FindALL
                    0. Exit
                Enter your choice:
                """);
    }

    public static void printItemMenu(){

        System.out.println("""
                HW7 IO/NIO - Item menu
                ==========================
                    1. Create
                    2. Update
                    3. Delete
                    4. FindById
                    5. FindALL
                    0. Exit
                Enter your choice:
                """);
    }

}
