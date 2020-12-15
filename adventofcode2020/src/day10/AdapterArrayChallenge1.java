package day10;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class AdapterArrayChallenge1 {
	
	public static void main(String[] args) throws IOException {
		// Read the input
		String testFilePath = "test-cases\\Day10.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
		ArrayList<Integer> arrayListOfadapters = new ArrayList<Integer>();
		
		// Start with 0
		arrayListOfadapters.add(0);
		
		while(scanner.hasNext()) {
			int line = scanner.nextInt();		
			arrayListOfadapters.add(line);
		}
		
		Collections.sort(arrayListOfadapters);
			
		// Add the last adapter is 3 jolts upper the last adapter
		arrayListOfadapters.add(arrayListOfadapters.get(arrayListOfadapters.size()-1)+3);
		//System.out.println(arrayListOfadapters);
		
		HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
		
		for(int i=0; i<arrayListOfadapters.size()-1; i++) {
			int difference = arrayListOfadapters.get(i+1)-arrayListOfadapters.get(i);
			occurrences.put(difference, occurrences.getOrDefault(difference, 0)+1);
		}
		
		long productOfJoltDifferences = 1;
		
		for(int key : occurrences.keySet()) {
			System.out.println("Difference: "+key+" ; occurrences: "+occurrences.get(key));
			productOfJoltDifferences=productOfJoltDifferences*occurrences.get(key);
		}
		
		System.out.println("The product of the differences is: "+productOfJoltDifferences);
	}

}
