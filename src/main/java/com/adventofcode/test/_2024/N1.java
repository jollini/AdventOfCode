package com.adventofcode.test._2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class N1 {

    public static void main(String[] args) {
        n1();
    }

    public static void n1() {
        try {
            String filePath = "src/main/resources/2024/input1.txt";
            FileReader fileReader = new FileReader(filePath);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                List<String> lines = reader.lines().toList();
                part1(lines);
                part2(lines);
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
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        // Cycling lines to create the lists
        for (String l : lines) {
            String replacedString = l.replaceAll(" {2}", "");
            String[] split = replacedString.split(" ");
            leftList.add(Integer.valueOf(split[0].trim()));
            rightList.add(Integer.valueOf(split[1].trim()));
        }
        // Reordering the lists
        List<Integer> leftOrdered = leftList.stream().sorted().toList();
//		System.out.println("LeftList  " + leftOrdered);
        List<Integer> rightOrdered = rightList.stream().sorted().toList();
//		System.out.println("RightList  " + rightOrdered);
        for (int i = 0; i < leftOrdered.size(); i++) {
            Integer numberLeft = leftOrdered.get(i);
            Integer numberRight = rightOrdered.get(i);
//            System.out.println("numberLeft  " + numberLeft);
//            System.out.println("numberRight  " + numberRight);
            // Finding differences and summing them
            int difference = Math.abs(numberLeft - numberRight);
//            System.out.println("difference  " + difference);
            sum += difference;
        }
        System.out.println("Sum p1: " + sum);
        return sum;
    }

    public static int part2(List<String> lines) {
        int sum = 0;
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        // Cycling lines to create the lists
        for (String l : lines) {
            String replacedString = l.replaceAll(" {2}", "");
            String[] split = replacedString.split(" ");
            leftList.add(Integer.valueOf(split[0].trim()));
            rightList.add(Integer.valueOf(split[1].trim()));
        }
        for (Integer currentNumber : leftList) {
            // Evaluating duplicates of current number in right list
            long numberCount = rightList.stream().filter(l -> l.equals(currentNumber)).count();
//            System.out.println("currentNumber  " + currentNumber);
//            System.out.println("numberCount  " + numberCount);
            // Multiplying currentNumber to his duplicating count then summing to others
            sum += (int) (currentNumber * numberCount);
//            System.out.println("sum  " + sum);
        }
        System.out.println("Sum p2: " + sum);
        return sum;
    }
}