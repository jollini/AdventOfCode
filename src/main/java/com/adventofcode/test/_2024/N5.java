package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class N5 {

    public static void main(String[] args) {
        exercise();
    }

    public static void exercise() {
        try {
            String filePath = "src/main/resources/2024/input5.txt";
            FileReader fileReader = new FileReader(filePath);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                List<String> lines = reader.lines().toList();
                int part1 = part1(lines);
                System.out.println("Part 1: " + part1);
                long part2 = part2(lines);
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

    public static int part2(List<String> lines) {
        int sum = 0;
        List<String> rules = new ArrayList<>();
        List<String> numbersSequence = new ArrayList<>();
        List<String> invalidRecords = new ArrayList<>();
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

        for (String n : numbersSequence) {
            if (!isValidRecord(rules, n)) {
                invalidRecords.add(n);
            }
        }

        for (String sequence : invalidRecords) {
            List<String> numbers = new ArrayList<>(Arrays.asList(sequence.split(",")));
            boolean needsSorting;

            do {
                needsSorting = false;
                for (String rule : rules) {
                    String[] parts = rule.split("\\|");
                    String first = parts[0].trim();
                    String second = parts[1].trim();

                    if (numbers.contains(first) && numbers.contains(second)) {
                        int firstIndex = numbers.indexOf(first);
                        int secondIndex = numbers.indexOf(second);

                        if (firstIndex > secondIndex) {
                            Collections.swap(numbers, firstIndex, secondIndex);
                            needsSorting = true;
                        }
                    }
                }
            } while (needsSorting); // Reorder the record

            validRecords.add(String.join(",", numbers));
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
}