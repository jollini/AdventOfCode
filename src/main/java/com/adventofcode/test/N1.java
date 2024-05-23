package com.adventofcode.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class N1 {
	
	public static void main(String[] args) {
		n1();
	}

	public static Integer n1() {
		try {
			String filePath = "src/main/resources/input1.txt";
			FileReader fileReader = new FileReader(filePath);
			try (BufferedReader reader = new BufferedReader(fileReader)) {
				Integer sum = reader
						.lines()
						.map(l -> l.replaceAll("[a-zA-Z]","") )
						.map(ln -> ln.charAt(0)+""+ln.charAt(ln.length()-1))
						.map(Integer::valueOf)
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
