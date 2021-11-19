package ua.com.alevel;

import ua.com.alevel.level1.ChessKnight;
import ua.com.alevel.level1.SquareOfTriangle;
import ua.com.alevel.level1.UniqueArray;
import ua.com.alevel.level2.BinTree;
import ua.com.alevel.level2.BracketValidator;
import ua.com.alevel.level3.GameOfLife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Module1 {

    public static void main(String[] args) throws IOException {
        String menu;
        do {
            printLevelMenu();
            menu = getUserInput();
            switch (menu) {
                case "1" -> {
                    printSublevel1Menu();
                    menu = getUserInput();

                    switch (menu) {
                        case "1" -> UniqueArray.run();
                        case "2" -> SquareOfTriangle.run();
                        case "3" -> ChessKnight.run();
                    }
                }
                case "2" -> {
                    printSublevel2Menu();
                    menu = getUserInput();

                    switch (menu) {
                        case "1" -> BracketValidator.run();
                        case "2" -> BinTree.run();
                    }
                }
                case "3" -> {
                    printSublevel3Menu();
                    menu = getUserInput();
                    if ("1".equals(menu)) {
                        GameOfLife.run();
                    }
                }
            }

        } while (!menu.equals("X"));
    }

    private static String getUserInput() {
        String userInput = "";
        System.out.print("Ваш выбор: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userInput = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return userInput;
    }


    private static void printLevelMenu() {
        System.out.println();
        System.out.println("1. level 1 (Уникальный массив, площадь треугольника, ход конём)");
        System.out.println("2. level 2 (Проверка скобок, Максимальная глубина дерева)");
        System.out.println("3. level 3 (Game Of Life)");
        System.out.println("X. Закрыть программу");
        System.out.println();
    }

    private static void printSublevel1Menu() {
        System.out.println();
        System.out.println("    1. Уникальный");
        System.out.println("    2. Площадь треугольника");
        System.out.println("    3. Ход конём");
        System.out.println("    X. Вернутся назад");
        System.out.println();
    }

    private static void printSublevel2Menu() {
        System.out.println();
        System.out.println("    1. Проверка скобок");
        System.out.println("    2. Максимальная глубина дерева");
        System.out.println("    X. Вернутся назад");
        System.out.println();
    }

    private static void printSublevel3Menu() {
        System.out.println();
        System.out.println("    1. Game Of Life");
        System.out.println("    X. Вернутся назад");
        System.out.println();
    }
}
