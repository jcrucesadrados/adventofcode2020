package day6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;

import java.util.Scanner;


public class CustomCustomsChallenge1 {
		
	public static int getNumberOfYeses(String answers) {
		
		if(answers.length()!=1) {
			HashSet<Character> answersHashSet = new HashSet<Character>();
			for(int i=0; i<answers.length(); i++) {
				answersHashSet.add(answers.charAt(i));
			}
			return answersHashSet.size();
		}
		else {
			return 1;
		}	
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		String testFilePath = "test-cases\\Day6.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
		
		String answers="";
		int sumOfYes = 0;
		while(scanner.hasNext()) {
			String line = scanner.nextLine();

			if(line.isBlank()) { // Change of group
				sumOfYes+=getNumberOfYeses(answers);
				answers="";
			}
			else {
				if(!scanner.hasNext()) {
					if(answers.isEmpty()) {
						answers=line;
					}
					else {
						answers=answers+line;
					}
					sumOfYes+=getNumberOfYeses(answers);
					answers="";
				}
				else {
					if(answers.isEmpty()) {
						answers=line;
					}
					else {
						answers=answers+line;
					}
				}
			}
			
		}
		
		System.out.println("The sum is "+sumOfYes);
		
	}

}
