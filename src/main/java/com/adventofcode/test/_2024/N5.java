package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N5 {

    public static void main(String[] args) {
        n1();
    }

    public static void n1() {
        try {
            String filePath = "src/main/resources/2024/input5.txt";
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
        List<String> rules = new ArrayList<>();
        List<String> numbersSequence = new ArrayList<>();
        List<String> validRecords = new ArrayList<>();

        // Create the two records
        for (String line : lines) {
            if (!line.isEmpty()) {
                if (line.contains("|")) {
                    rules.add(line);
                } else {
                    numbersSequence.add(line);
                }
            }
        }

        // Cycling records and checks if is a valid record
        for (String n : numbersSequence) {
            if (isValidRecord(rules, n)) {
                validRecords.add(n);
            }
        }

        // Find the middle number of the record add it to the sum
        for (String vr : validRecords) {
            List<String> stringNumbers = Arrays.stream(vr.split(",")).toList();
            int middleIndex = (stringNumbers.size() / 2);
            int numToSum = Integer.parseInt(stringNumbers.get(middleIndex));
            sum += numToSum;
//            System.out.println("numToSum : " + numToSum);
        }

//        System.out.println("validRecords : " + validRecords);
        return sum;
    }

    private static boolean isValidRecord(List<String> rules, String n) {
        List<String> numbersList = Arrays.stream(n.split(",")).toList();
        for (String r : rules) {
            String[] splitRules = r.split("\\|");
            String firstNumber = splitRules[0];
            String secondNumber = splitRules[1];
            if (numbersList.contains(firstNumber) &&
                    numbersList.contains(secondNumber) &&
                    numbersList.indexOf(firstNumber) > numbersList.indexOf(secondNumber)
            ) {
                return false;
            }
        }
        return true;
    }
}