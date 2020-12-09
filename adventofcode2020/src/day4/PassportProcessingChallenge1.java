package day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PassportProcessingChallenge1 {


	public static boolean checkPassport(String passport, ArrayList<String> mandatoryElements) {
		boolean thePassportPass = true;
		
		String[] passportFields = passport.split(" ");
		
		HashSet<String> fieldsPresent = new HashSet<String>();
		for(int i=0; i<passportFields.length; i++) {
			String[] keyValueField = passportFields[i].split(":");
			fieldsPresent.add(keyValueField[0]);
		}
		
		for(int i=0; i<mandatoryElements.size(); i++) {
			if(!fieldsPresent.contains(mandatoryElements.get(i))){
				return false;
			}
		}
		
		// If we are here is because all the mandatory fields are present. Lets Validate
		
		
		return thePassportPass;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		//Load the test 
		String testFilePath = "test-cases\\Day4.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
		
		// Set mandatory elements
		ArrayList<String> mandatoryElements = new ArrayList<String>();
		mandatoryElements.add("byr");
		mandatoryElements.add("iyr");
		mandatoryElements.add("eyr");
		mandatoryElements.add("hgt");
		mandatoryElements.add("hcl");
		mandatoryElements.add("ecl");
		mandatoryElements.add("pid");
		// mandatoryElements.add("cid"); CID is not mandatory, Santa is coming 
				
		// Need to check the lines. A white line means that all the information is present
		int numberOfPassports = 0;
		String passport ="";
		while(scanner.hasNext()) {

			String line = scanner.nextLine(); // Use nextLine instead next() need to get empty lines
			
			if(line.isBlank() || !scanner.hasNext()) {
				
				// System.out.println(passport);
				if(checkPassport(passport, mandatoryElements)) {
					numberOfPassports++;
					//System.out.println("Passport is valid\n");
				}
				else {
					//System.out.println("Passport is invalid\n");
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
