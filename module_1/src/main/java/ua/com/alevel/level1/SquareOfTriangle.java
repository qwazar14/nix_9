package ua.com.alevel.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquareOfTriangle {

    public static void squareTriangle() {
        double x1, y1, x2, y2, x3, y3;
        System.out.println("Введите вершину А(x1,y1)");
        System.out.print("x1: ");
        x1 = getUserInputAndParseToDouble();
        System.out.print("y1: ");
        y1 = getUserInputAndParseToDouble();

        System.out.println("Введите вершину B(x2,y2)");
        System.out.print("x2: ");
        x2 = getUserInputAndParseToDouble();
        System.out.print("y2: ");
        y2 = getUserInputAndParseToDouble();

        System.out.println("Введите вершину C(x3,y3)");
        System.out.print("x3: ");
        x3 = getUserInputAndParseToDouble();
        System.out.print("y3: ");
        y3 = getUserInputAndParseToDouble();

        double square = (double) calculateSquareOfTriangle(x1, y1, x2, y2, x3, y3);
        String formattedSquare = String.format("%.2f", square).replace(',', '.');

        System.out.println("Площадь треугольника ABC - " + formattedSquare);
    }

    private static Object calculateSquareOfTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        double lengthAB = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double lengthAC = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double lengthBC = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double semiPerimeter = (lengthAB + lengthAC + lengthBC) / 2;
        double res = Math.sqrt(semiPerimeter * (semiPerimeter - lengthAB) *
                (semiPerimeter - lengthAC) * (semiPerimeter - lengthBC));
        if (res <= 0) {
            System.out.println("Ошибка, невозможно рассчитать площадь при таких параметрах:");
            System.out.println("x1: " + x1 + ", y1: " + y1);
            System.out.println("x2: " + x2 + ", y2: " + y2);
            System.out.println("x3: " + x3 + ", y3: " + y3);
            return 0.0;
        } else {
            return res;
        }
    }

    private static double getUserInputAndParseToDouble() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = reader.readLine();
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException n) {
                System.out.println("Ошибка. Принимаются только цифры!");
                return 0.0;
            }
        } catch (IOException e) {
            System.out.println("Ошибка. Принимаются только цифры!");
            return 0.0;
        }
    }
}