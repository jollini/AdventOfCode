package com.adventofcode.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class N2 {
	
	public static void main(String[] args) {
		n2();
	}
	
	public static Integer n2() {
		class CubeGame {
			int id;
			int green = 0;
			int red = 0;
			int blue = 0;
			
			public CubeGame(int id) {
				super();
				this.id = id;
			}
			
			public int getId() {
				return id;
			}
			public int getGreen() {
				return green;
			}
			public void setGreen(int green) {
				this.green = green;
			}
			public int getRed() {
				return red;
			}
			public void setRed(int red) {
				this.red = red;
			}
			public int getBlue() {
				return blue;
			}
			public void setBlue(int blue) {
				this.blue = blue;
			}

			@Override
			public String toString() {
				return "CubeGame [id=" + id + ", green=" + green + ", red=" + red + ", blue=" + blue + "]";
			}
		}
		try {
			/*
			 * Determine which games would have been possible if the bag had been loaded
			 * with only 12 red cubes, 13 green cubes, and 14 blue cubes. 
			 * What is the sum of the IDs of those games?
			 */
			String filePath = "src/main/resources/input2.txt";
			FileReader fileReader = new FileReader(filePath);
			try (BufferedReader reader = new BufferedReader(fileReader)) {
				List<CubeGame> cubeGameList = new ArrayList<>();
				reader
				.lines()
				.forEach(r -> {
					Integer id = Integer.valueOf(r.split(String.valueOf(':'))[0].replace("Game ",""));

					String cubesString = r.split(String.valueOf(':'))[1].replaceAll("\\s+","");
					List<String> cubesList = Arrays.asList(cubesString.split(String.valueOf(";")));

					cubesList
					.stream()
					.forEach(c -> {
						CubeGame cb = new CubeGame(id);
						cubeGameList.add(cb);

						final Pattern redRegex = Pattern.compile("([1-9]||[1-9][0-9])(?:red)");
						final Matcher redMatcher = redRegex.matcher(c);
						while (redMatcher.find()) {
							Integer eachRedCubes = Integer.valueOf(redMatcher.group(1));
							cb.setRed(eachRedCubes);
						}

						final Pattern greenRegex = Pattern.compile("([1-9]||[1-9][0-9])(?:green)");
						final Matcher greenMatcher = greenRegex.matcher(c);
						while (greenMatcher.find()) {
							Integer eachGreenCubes = Integer.valueOf(greenMatcher.group(1));
							cb.setGreen(eachGreenCubes);
						}

						final Pattern blueRegex = Pattern.compile("([1-9]||[1-9][0-9])(?:blue)");
						final Matcher blueMatcher = blueRegex.matcher(c);
						while (blueMatcher.find()) {
							Integer eachBlueCubes = Integer.valueOf(blueMatcher.group(1));
							cb.setBlue(eachBlueCubes);
						}
					});
				});
				System.out.println(cubeGameList);

				Set<Integer> idToRemove = cubeGameList
						.stream()
						.filter(o -> o.getRed() > 12 || o.getGreen() > 13 || o.getBlue() > 14)
						.map(v -> v.getId())
						.collect(Collectors.toSet());
				System.out.println(idToRemove);

				Integer sum = cubeGameList
						.stream()
						.map(v -> v.getId())
						.distinct()
						.filter(id -> !idToRemove.contains(id))
						.reduce(0, Integer::sum);
				System.out.println(sum);
				return sum;
			}  catch (FileNotFoundException f){
		        System.out.println(filePath+" does not exist");
		        return null;
		    } catch (IOException e) {
		    	e.printStackTrace();
				return null;
			}	        
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
