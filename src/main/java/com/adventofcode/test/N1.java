package com.adventofcode.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class N1 {

	public static void main(String[] args) {
		n1();
	}

	public static void n1() {
		try {
			String filePath = "src/main/resources/input1.txt";
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

	private static void part1(List<String> lines) {
		Integer sum = lines.stream()
				.map(l -> l.replaceAll("[a-zA-Z]", ""))
				.map(ln -> ln.charAt(0) + "" + ln.charAt(ln.length() - 1))
				.map(Integer::valueOf)
				.reduce(0, Integer::sum);
		System.out.println(sum);
	}

	private static void part2(List<String> lines) {
		Pattern firstRegex = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine).*");
		Pattern lastRegex = Pattern.compile(".*(one|two|three|four|five|six|seven|eight|nine)");
		List<String> parsedList = new ArrayList<>();
		lines.stream().forEach(l -> {
			int index = lines.indexOf(l);
			String initialString = new String(l);
			Matcher firstMatcher = firstRegex.matcher(l);
			while (firstMatcher.find()) {
				String stringNumber = firstMatcher.group(1);
//				System.out.println("Found " + stringNumber + " on line " + index);
				int startIdx = firstMatcher.start();
				String stringBefore = initialString.substring(0, startIdx);
				Matcher beforeMatcher = Pattern.compile("\\d").matcher(stringBefore);
				if (!beforeMatcher.find()) {
					l = l.replaceAll(stringNumber, getStringNumber(stringNumber));
				}
			}
			Matcher lastMatcher = lastRegex.matcher(l);
			while (lastMatcher.find()) {
				String stringNumber = lastMatcher.group(1);
//				System.out.println("Found " + stringNumber + " on line " + index);
				int endIdx = lastMatcher.end();
				int afterIndex = endIdx < initialString.length() ? endIdx + 1 : endIdx;
				String stringAfter = initialString.substring(afterIndex, initialString.length());
				Matcher afterMatcher = Pattern.compile("\\d").matcher(stringAfter);
				if (!afterMatcher.find()) {
					l = l.replaceAll(stringNumber, getStringNumber(stringNumber));
				}
			}
			l = l.replaceAll("[a-zA-Z]", "");
			l = l.charAt(0) + "" + l.charAt(l.length() - 1);
			parsedList.add(l);
			System.out.println(l + " at index " + index + " from input " + initialString);
		});

		int realSum = parsedList.stream()
				.map(Integer::valueOf)
				.reduce(0, Integer::sum);
		System.out.println(realSum);
	}

	private static String getStringNumber(String stringNumber) {
		switch (stringNumber) {
		case "one":
			return "1";
		case "two":
			return "2";
		case "three":
			return "3";
		case "four":
			return "4";
		case "five":
			return "5";
		case "six":
			return "6";
		case "seven":
			return "7";
		case "eight":
			return "8";
		case "nine":
			return "9";
		default:
			return "";
		}
	}

}
