package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N2 {

    public static void main(String[] args) {
        exercise();
    }

    public static void exercise() {
        try {
            String filePath = "src/main/resources/2024/input2.txt";
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
        for (String l : lines) {
            String[] numberArray = l.split(" ");
            List<Integer> numberList = Arrays.stream(numberArray).map(Integer::parseInt).toList();
            if (isSafe(numberList)) {
                sum++;
            }
        }
//        System.out.println("Sum p1: " + sum);
        return sum;
    }

    public static int part2(List<String> lines) {
        int sum = 0;
        for (String l : lines) {
            String[] numberArray = l.split(" ");
            List<Integer> numberList = Arrays.stream(numberArray).map(Integer::parseInt).toList();

            if (isSafe(numberList)) {
                sum++;
            } else {
                for (int i = 0; i < numberList.size(); i++) {
                    if (removeNumberAndTestAgain(numberList, i)) {
                        sum++;
                        break;
                    }
                }
            }

        }
//        System.out.println("Sum p2: " + sum);
        return sum;
    }

    private static boolean isSafe(List<Integer> numberList) {
        boolean increasing = false;
        boolean decreasing = false;
        for (int i = 1; i < numberList.size(); i++) {
            int diff = numberList.get(i) - numberList.get(i - 1);
            // minimum difference allowed 1 and max difference allowed 3
            if (diff == 0 || diff < -3 || diff > 3) {
                return false;
            }
            if (diff > 0) {
                increasing = true;
            } else {
                decreasing = true;
            }
        }
        // increasing and decreasing in the same line is not allowed
        boolean safe = !(increasing && decreasing);
        if (safe) {
            System.out.println("Safe: " + numberList);
        }
        return safe;
    }

    private static boolean removeNumberAndTestAgain(List<Integer> numberList, int indexToRemove) {
        List<Integer> modifiedList = new ArrayList<>(numberList);
        modifiedList.remove(indexToRemove);
        return isSafe(modifiedList);
    }
}