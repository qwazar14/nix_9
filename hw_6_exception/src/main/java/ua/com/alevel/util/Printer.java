package ua.com.alevel.util;

public class Printer {
    public static void printDateFormat() {
        System.out.println("""
                1 - dd/mm/yy
                2 - m/d/yyyy
                3 - mmm-d-yy
                4 - dd-mmm-yyyy
                NOTE: in case 3 and 4 - use "Jan", "Feb", "Mar", etc.""");
    }

    public static void printNavigation() {
        System.out.println();
        System.out.println("""
                HW6 Calendar/Exceptions
                ==========================
                    1. Input calendar format
                    2. Output calendar format
                    3. Difference
                    4. Add
                    5. Subtract
                    6. Sort
                    0. Exit
                Enter your choice:
                """);
        System.out.println();
    }
}
