package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N7 {

    public static void main(String[] args) {
        exercise();
    }

    public static void exercise() {
        try {
            String filePath = "src/main/resources/2024/input7.txt";
            FileReader fileReader = new FileReader(filePath);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                List<String> lines = reader.lines().toList();
                long part1 = part1(lines);
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

    public static long part1(List<String> lines) {
        List<Long> validRecords = new ArrayList<>();

        for (String line : lines) {
            String[] splitArray = line.split(":");
            long result = Long.parseLong(splitArray[0]);
            List<Long> numberSequence = Arrays.stream(splitArray[1].trim().split(" ")).map(Long::parseLong).toList();
            if (isValidSequence(result, numberSequence)) {
                validRecords.add(result);
            }
//            System.out.println("result : " + result);
//            System.out.println("numberSequence : " + Arrays.toString(numberSequence));
        }
        System.out.println("validRecords : " + validRecords);

        return validRecords.stream().mapToLong(o -> o).sum();
    }

    private static boolean isValidSequence(long result, List<Long> numbers) {
        return findCombinations(numbers, result, 0, 0);
    }

    private static boolean findCombinations(List<Long> numbers, long result, int i, long resultOperation) {
        if (i == numbers.size()) {
            return resultOperation == result;
        }

        Long currentNumber = numbers.get(i);

        if (findCombinations(numbers, result, i + 1, currentNumber + resultOperation)) {
            return true;
        }
        return findCombinations(numbers, result, i + 1, currentNumber * resultOperation);
    }

}