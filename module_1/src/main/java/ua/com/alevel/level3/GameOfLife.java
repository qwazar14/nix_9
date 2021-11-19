package ua.com.alevel.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class GameOfLife {

    public static void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите длину доски");
        int M = abs(Integer.parseInt(reader.readLine()));

        System.out.println();
        System.out.println("Первое поколение");
        int[][] cells = new int[M][M];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = (int) (Math.random() * 2);
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
        newGeneration(cells, M);
    }

    private static void newGeneration(int[][] cells, int M) {
        int[][] newBoard = new int[M][M];
        for (int i = 1; i < M - 1; i++) {
            for (int m = 1; m < M - 1; m++) {
                int livingNeighbor = 0;
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        livingNeighbor += cells[i + j][m + k];
                    }
                }
                livingNeighbor -= cells[i][m];
                if (cells[i][m] == 1 && livingNeighbor < 2 || cells[i][m] == 1 && livingNeighbor > 3) {
                    newBoard[i][m] = 0;
                } else if (cells[i][m] == 0 && livingNeighbor == 3) {
                    newBoard[i][m] = 1;
                } else {
                    newBoard[i][m] = cells[i][m];
                }
            }
        }
        System.out.println();
        System.out.println("Новое поколение");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(newBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

}


