package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class N6 {

    public static void main(String[] args) {
        n1();
    }

    public static void n1() {
        try {
            String filePath = "src/main/resources/2024/input6.txt";
            FileReader fileReader = new FileReader(filePath);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                List<String> lines = reader.lines().toList();
                int part1 = part1(lines);
                System.out.println("Part 1: " + part1);
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

        // Move guard
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (matrix[i][j] == '^') {
                    if (i - 1 >= 0) {
                        if (matrix[i - 1][j] == '#') {
                            matrix[i][j] = '>';
                            i = 0;
                            j = 0;
                        } else {
                            matrix[i][j] = 'x';
                            matrix[i - 1][j] = '^';
                            i = i - 1;
                            j = j - 1;
                            System.out.println("Up");
                        }
                    }
                } else if (matrix[i][j] == '>') {
                    if (j + 1 < matrix[0].length) {
                        if (matrix[i][j + 1] == '#') {
                            matrix[i][j] = 'v';
                            i = 0;
                            j = 0;
                        } else {
                            matrix[i][j] = 'x';
                            matrix[i][j + 1] = '>';
                            j = j + 1;
                            i = i - 1;
                            System.out.println("Right");
                        }
                    }
                } else if (matrix[i][j] == 'v') {
                    if (i + 1 < matrix.length) {
                        if (matrix[i + 1][j] == '#') {
                            matrix[i][j] = '<';
                            i = 0;
                            j = 0;
                        } else {
                            matrix[i][j] = 'x';
                            matrix[i + 1][j] = 'v';
                            i = i + 1;
                            j = j - 1;
                            System.out.println("Bottom");
                        }
                    }
                } else if (matrix[i][j] == '<') {
                    if (j - 1 >= 0) {
                        if (matrix[i][j - 1] == '#') {
                            matrix[i][j] = '^';
                            i = 0;
                            j = 0;
                        } else {
                            matrix[i][j] = 'x';
                            matrix[i][j - 1] = '<';
                            j = j - 1;
                            i = i - 1;
                            System.out.println("Left");
                        }
                    }
                }
            }
        }

        // Count positions guard
        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (matrix[i][j] == 'x') {
                    sum++;
                }
            }
        }

        // One in addition is the final position
        return sum + 1;
    }
}