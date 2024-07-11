package com.adventofcode.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class N3 {
	final static Pattern numberRegex = Pattern.compile("\\d+");
	final static Pattern symbolRegex = Pattern.compile("[$&+,:;=?@#|'/<>^\\*()%!-]");

	public static void main(String[] args) {
		n3();
	}

	public static void n3() {
		try {
			String filePath = "src/main/resources/input3.txt";
			FileReader fileReader = new FileReader(filePath);
			try (BufferedReader reader = new BufferedReader(fileReader)) {
				sum(reader);
			} catch (FileNotFoundException f) {
				System.out.println(filePath + " does not exist");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The engine schematic (your puzzle input) consists of a visual representation
	 * of the engine. There are lots of numbers and symbols you don't really
	 * understand, but apparently any number adjacent to a symbol, even diagonally,
	 * is a "part number" and should be included in your sum. (Periods (.) do not
	 * count as a symbol.)
	 */
	private static Integer sum(BufferedReader reader) {
		StringBuilder symbols = new StringBuilder();
		Integer sum = 0;

		List<String> lines = reader.lines().collect(Collectors.toList());
		for (int i = 0; i < lines.size(); i++) {
			// identifico la riga corrente
			String currentLine = lines.get(i);
			// identifico la riga precedente se presente
			String beforeLine = i > 0 ? lines.get(i - 1) : null;
			// identifico la riga successiva se presente
			String afterLine = i < lines.size() - 1 ? lines.get(i + 1) : null;

			String parsedString = currentLine.replaceAll("\\.", "");
			parsedString = parsedString.replaceAll("\\d+", "");
//			System.out.println(parsedString);
			symbols.append(parsedString);

			// identificare i numeri nella riga
			final Matcher numberMatcher = numberRegex.matcher(currentLine);
			int minIndex = 0;
			while (numberMatcher.find()) {
				Integer number = Integer.valueOf(numberMatcher.group(0));
//				System.out.println(number + " on line "+ i);

				int startIndex = currentLine.indexOf(number.toString(), minIndex);
				int endIndex = startIndex + number.toString().length();
				minIndex = endIndex;

//				System.out.println("startIndex: "+ startIndex);
//				System.out.println("endIndex: "+ endIndex);

				// verificare se per ogni numero, c'è un simbolo sotto, sopra anche
				// diagonalmente
				// o immediatamente di lato
				// (per questo all'indice del numero aggiungo un numero all'inizio e alla fine)
				int startIndexAdiacent = startIndex > 0 ? startIndex - 1 : startIndex;
				int endIndexAdiacent = endIndex < currentLine.length() ? endIndex + 1 : endIndex;

				// se la condizione si verifica in uno di questi casi, aggiungo il numero
				// prima verifico se ci sono simboli immediatamente vicino al numero
				if (evaluateLine(currentLine, startIndexAdiacent, endIndexAdiacent)) {
					sum = addNumber(sum, number, i);
				} else if (evaluateLine(beforeLine, startIndexAdiacent, endIndexAdiacent)) {
					// poi verifico se ci sono simboli nella riga precedente, se c'è
					sum = addNumber(sum, number, i);
				} else if (evaluateLine(afterLine, startIndexAdiacent, endIndexAdiacent)) {
					// se non c'è la riga precendente, verifico se ci sono simboli nella riga
					// successiva, se c'è
					sum = addNumber(sum, number, i);
				}
			}
		}

		Set<Character> charSet = symbols.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
		System.out.println("Symbols: " + charSet.toString());
		System.out.println("Part 1 Sum: " + sum);
		return sum;
	}

	private static boolean evaluateLine(String line, int startIndexAdiacent, int endIndexAdiacent) {
		if (line != null) {
			String lineAdiacent = line.substring(startIndexAdiacent, endIndexAdiacent);
			return symbolRegex.matcher(lineAdiacent).find();
		}
		return false;
	}

	private static int addNumber(int sum, int number, int i) {
//		System.out.println("Sum: "+ sum );
		System.out.println("Added number: " + number + " in line " + i);
		int updatedSum = sum += number;
//		System.out.println("Updated Sum: "+ sum );
		return updatedSum;
	}
}
