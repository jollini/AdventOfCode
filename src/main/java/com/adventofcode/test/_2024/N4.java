package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class N4 {

    public static void main(String[] args) {
        exercise();
    }

    public static void exercise() {
        try {
            String filePath = "src/main/resources/2024/input4.txt";
            FileReader fileReader = new FileReader(filePath);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                List<String> lines = reader.lines().toList();
                int part1 = part1(lines);
                System.out.println("Part 1: " + part1);
                int part2 = part2(lines);
                System.out.println("Part 2: " + part2);
            } catch (FileNotFoundException f) {
                System.out.println(filePath + " does not exist");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int part1(List<String> lines) {
        int sum = 0;
        int matrixWidth = lines.get(0).length();
        int matrixHeight = lines.size();

        char[][] matrix = new char[matrixHeight][matrixWidth];

        // Creating matrix
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                matrix[i][j] = lines.get(i).toCharArray()[j];
            }
        }

        // Finding the word
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (matrix[i][j] == 'X') {
                    sum = searchInDirections(matrix, i, j, "XMAS", sum);
                }
            }
        }

//        System.out.println("Matrix " + Arrays.deepToString(matrix));
        System.out.println("Sum p1: " + sum);
        return sum;
    }

    private static int searchInDirections(char[][] matrix, int i, int j, String word, int sum) {
        int[][] directions = {
                {0, 1}, // orizzontale destra
                {0, -1}, // orizzontale sinistra
                {1, 0}, // verticale giù
                {-1, 0}, // verticale su
                {1, 1}, // diagonale giù-destra
                {1, -1}, // diagonale giù-sinistra
                {-1, 1}, // diagonale su-destra
                {-1, -1} // diagonale su-sinistra
        };

        for (int[] dir : directions) {
            if (checkDirection(matrix, i, j, dir[0], dir[1], word)) {
                System.out.println("Found 'XMAS' starting at (" + i + ", " + j + ")");
                sum++;
            }
        }
        return sum;
    }

    private static boolean checkDirection(char[][] matrix, int i, int j, int di, int dj, String word) {
        int n = word.length();
        int x = i;
        int y = j;

        if (x + (n - 1) * di < 0 || x + (n - 1) * di >= matrix.length ||
                y + (n - 1) * dj < 0 || y + (n - 1) * dj >= matrix[0].length) {
            return false;
        }

        for (int k = 0; k < n; k++) {
            if (matrix[x][y] != word.charAt(k)) {
                return false;
            }
            x += di;
            y += dj;
        }
        return true;
    }

    public static int part2(List<String> lines) {
        int sum = 0;
        int matrixWidth = lines.get(0).length();
        int matrixHeight = lines.size();

        char[][] matrix = new char[matrixHeight][matrixWidth];

        // Creating matrix
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                matrix[i][j] = lines.get(i).toCharArray()[j];
            }
        }

        // Finding the X
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (matrix[i][j] == 'A') {
                    sum = findX(matrix, i, j, sum);
                }
            }
        }

//        System.out.println("Matrix " + Arrays.deepToString(matrix));
//        System.out.println("Sum p2: " + sum);
        return sum;
    }

    private static int findX(char[][] matrix, int i, int j, int sum) {
        if (i > 0 && j > 0 && i + 1 < matrix.length && j + 1 < matrix[0].length) {
            char rightUp = matrix[i - 1][j + 1];
            char leftUp = matrix[i - 1][j - 1];
            char rightDown = matrix[i + 1][j + 1];
            char leftDown = matrix[i + 1][j - 1];

            if ((rightUp == 'M' && leftUp == 'M' && rightDown == 'S' && leftDown == 'S') ||
                    (rightUp == 'S' && leftUp == 'S' && rightDown == 'M' && leftDown == 'M') ||
                    (rightUp == 'S' && leftUp == 'M' && rightDown == 'S' && leftDown == 'M') ||
                    (rightUp == 'M' && leftUp == 'S' && rightDown == 'M' && leftDown == 'S')) {
//                System.out.println("Found 'X-MAS' with center at (" + i + ", " + j + ")");
                sum++;
            }
        }

        return sum;
    }

}