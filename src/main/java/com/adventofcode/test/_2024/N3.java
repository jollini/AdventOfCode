package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class N3 {

    public static void main(String[] args) {
        exercise();
    }

    public static void exercise() {
        try {
            String filePath = "src/main/resources/2024/input3.txt";
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
        Pattern regex = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        int sum = 0;
        for (String l : lines) {
            Matcher firstMatcher = regex.matcher(l);
            while (firstMatcher.find()) {
                String stringNumber1 = firstMatcher.group(1);
                String stringNumber2 = firstMatcher.group(2);
                sum += Integer.parseInt(stringNumber1) * Integer.parseInt(stringNumber2);
            }
        }
//        System.out.println("Sum p1: " + sum);
        return sum;
    }

    public static int part2(List<String> lines) {
        Pattern regex = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        int sum = 0;
        boolean valid = true;

        for (String l : lines) {
            Matcher firstMatcher = regex.matcher(l);

            while (firstMatcher.find()) {
                if (firstMatcher.group().contains("do()")) {
                    valid = true;
                } else if (firstMatcher.group().contains("don't()")) {
                    valid = false;
                }

                String stringNumber1 = firstMatcher.group(1);
                String stringNumber2 = firstMatcher.group(2);
                if (valid && stringNumber1 != null && stringNumber2 != null) {
                    sum += Integer.parseInt(stringNumber1) * Integer.parseInt(stringNumber2);
                }
            }
        }
//        System.out.println("Sum p2: " + sum);
        return sum;
    }
}