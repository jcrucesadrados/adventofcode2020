package day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class PassportProcessingChallenge2ObjectOriented {

	
	public static void main(String[] args) throws FileNotFoundException {
		//Load the test 
		String testFilePath = "test-cases\\Day4.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
	
		// Need to check the lines. A white line means that all the information is present
		int numberOfPassports = 0;
		String passport ="";
		while(scanner.hasNext()) {

			String line = scanner.nextLine(); // Use nextLine instead next() need to get empty lines
			
			if(line.isBlank() || !scanner.hasNext()) {
				NorthPolePassport thisPassport = new NorthPolePassport(passport);
				
				if(thisPassport.isAValidPassport()) {
					numberOfPassports++;
				}
				passport="";
			}
			else {
				if(passport.isEmpty()) {
					passport=line;
				}
				else {
					passport=passport+" "+line;
				}
			}
		} 
		
				
		System.out.println("Number of valid passports: "+numberOfPassports);

	}
}
