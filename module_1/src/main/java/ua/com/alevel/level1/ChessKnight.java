package ua.com.alevel.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class ChessKnight {

    private static String generateBoard() {
        String brdStr = "";
        brdStr += "  a b c d e f g h\n";
        for (int r = 0; r < 8; r++) {
            brdStr += (8 - r) + "";
            for (int c = 0; c < 8; c++) {
                brdStr += " .";
            }
            brdStr += "\n"; // line break
        }
        return brdStr;
    }

    public static void run() {

        int userInputX = 0;
        int userInputY = 0;
        String strUserInputX = "";
        String strPosX = "";
        int posX = 0;
        int posY = 0;

        System.out.println(generateBoard());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите координату x (A-H)");
            strUserInputX = reader.readLine();

            System.out.println("Введите координату y (1-8)");
            userInputY = abs(Integer.parseInt(reader.readLine()));

            System.out.println("Введите новую координату x (A-H)");
            strPosX = reader.readLine();

            System.out.println("Введите новую координату y (1-8)");
            posY = abs(Integer.parseInt(reader.readLine()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        userInputX = convertXValue(strUserInputX);
        posX = convertXValue(strPosX);
        if (userInputY > 0 && userInputY <= 8 || posY > 0 && posY <= 8) {
            int dx = abs(userInputX - posX);
            int dy = abs(userInputY - posY);
            if (dx == 1 && dy == 2 || dx == 2 && dy == 1) {
                System.out.println("user input = " + strUserInputX + userInputY);
                System.out.println("new input = " + strPosX + posY);
                System.out.println("Ход возможен");
            } else {
                System.out.println("Ход невозможен");
            }
        } else {
            System.out.println("Ошибка");
        }
    }

    private static int convertXValue(String strUserInputX) {
        int userInputX;
        switch (strUserInputX) {
            case ("A") -> userInputX = 1;
            case ("B") -> userInputX = 2;
            case ("C") -> userInputX = 3;
            case ("D") -> userInputX = 4;
            case ("E") -> userInputX = 5;
            case ("F") -> userInputX = 6;
            case ("G") -> userInputX = 7;
            case ("H") -> userInputX = 8;
            default -> throw new IllegalStateException("Unexpected value: " + strUserInputX);
        }
        return userInputX;
    }
}

