package day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;

import java.util.Scanner;


public class CustomCustomsChallenge2 {
	
	private static boolean hashSetOfCommonAnswersInitialized = false;
	private static HashSet<Character> hashSetOfCommonAnswers;
	
	public static void computeHashSetOfAnswer(String answers) {
		
		
		HashSet<Character> hashSetOfAnswers = new HashSet<Character>();
		for(int i=0; i<answers.length(); i++) {
			hashSetOfAnswers.add(answers.charAt(i));
		}
		
		//System.out.println(hashSetOfAnswers.toString());
		updateCommonAnswers(hashSetOfAnswers);	
		
	}
	
	public static void updateCommonAnswers(HashSet<Character> hashSetOfAnswers) {
		if(!hashSetOfCommonAnswersInitialized) {
			// Load all the hashset because is the first time
			hashSetOfCommonAnswers = hashSetOfAnswers;
			hashSetOfCommonAnswersInitialized = true;
		}
		else {
			hashSetOfCommonAnswers.retainAll(hashSetOfAnswers); // Saves the join 
		}
		
		// System.out.println(hashSetOfCommonAnswers.toString());
		//System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		String testFilePath = "test-cases\\Day6.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
		
		
		int sumOfCommons = 0;
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			
			if(line.isBlank()) { // Change of group
				sumOfCommons+=hashSetOfCommonAnswers.size();	
				hashSetOfCommonAnswers.clear();
				hashSetOfCommonAnswersInitialized = false;
				
			}
			else {
				if(!scanner.hasNext()) {
					computeHashSetOfAnswer(line);
					sumOfCommons+=hashSetOfCommonAnswers.size();
					
				}
				else {
					computeHashSetOfAnswer(line);
				}
			}
			
			
		}
		
		System.out.println("The sum is "+sumOfCommons);
		
	}

}
