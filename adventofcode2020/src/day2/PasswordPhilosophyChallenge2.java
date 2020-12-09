package day2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PasswordPhilosophyChallenge2 {
	
	public static boolean passwordFollowsPolicy(String line) {
		// Example of line--> 1-3 a: abcde
		boolean pwFollowsPolicy = false;
		
		// First we need to get all the information from the string
		String[] lineArray = line.split(" "); // [1-3][a:][abcde] (there are no spaces)
		
		// Now we can get the data:
		String[] ranges = lineArray[0].split("-"); // [1][3]
		int firstPositionToCheck = Integer.parseInt(ranges[0]); // 1 (need to parse it as integer)
		int secondPositionToCheck = Integer.parseInt(ranges[1]); // 3 (need to parse it as integer)
		char letterOfPolicy = lineArray[1].charAt(0); // a
		String passwordToCheck = lineArray[2];
		
		// Check the number of occurrences (frequency)
		int frequency = 0;
		if(passwordToCheck.charAt(firstPositionToCheck-1)==letterOfPolicy){ // Be Careful!! Policy starts on 1 but the structure at 0
			frequency++;	
		}
		if(passwordToCheck.charAt(secondPositionToCheck-1)==letterOfPolicy){
			frequency++;	
		}
		
		if(frequency==1) { // Only one occurrence is allowed
			pwFollowsPolicy = true;
		}

		return pwFollowsPolicy;
	}

	public static void main(String[] args) throws FileNotFoundException {
			
		//Load the test 
		Scanner scanner = new Scanner(new FileReader("test-cases\\Day2.txt")); 
		int numberOfPasswordsThatFollows = 0;
		while(scanner.hasNext()) {
			String line = scanner.nextLine();       
			boolean lineFollowsPolicy = passwordFollowsPolicy(line); 
			if(lineFollowsPolicy) {
				numberOfPasswordsThatFollows++; 
			}
		}
		
		System.out.println("There are "+numberOfPasswordsThatFollows+" passwords that follows their policy");
		scanner.close();
	}
}
