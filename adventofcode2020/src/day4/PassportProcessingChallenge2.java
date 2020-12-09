package day4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassportProcessingChallenge2 {

	public static boolean checkPassport(String passport, ArrayList<String> mandatoryElements) {

		String[] passportFields = passport.split(" ");
		
		HashSet<String> fieldsPresent = new HashSet<String>();
		
		HashMap<String, String> hashMapOfpassportInformation = new HashMap<String, String>();
		
		for(int i=0; i<passportFields.length; i++) {
			String[] keyValueField = passportFields[i].split(":");
			fieldsPresent.add(keyValueField[0]);
			hashMapOfpassportInformation.put(keyValueField[0], keyValueField[1]);
		}
		
		for(int i=0; i<mandatoryElements.size(); i++) {
			if(!fieldsPresent.contains(mandatoryElements.get(i))){
				System.out.println(passport);
				System.out.println("Not all the fields present"+"\n");
				return false;
			}
		}
		
		// If we are here is because all the mandatory fields are present. Lets Validate
		if(!checkBirthYear(Integer.parseInt(hashMapOfpassportInformation.get("byr")))) {
			System.out.println(passport);
			System.out.println("Error at byr validation. Value received: "+hashMapOfpassportInformation.get("byr")+"\n");
			return false;
		}
		else {
			if(!checkIssueYear(Integer.parseInt(hashMapOfpassportInformation.get("iyr")))) {
				System.out.println(passport);
				System.out.println("Error at iyr validation. Value Received: "+hashMapOfpassportInformation.get("iyr")+"\n");
				return false;
			}
			else {
				if(!checkExpirationYear(Integer.parseInt(hashMapOfpassportInformation.get("eyr")))) {
					System.out.println(passport);
					System.out.println("Error at eyr validation. Value Received: "+hashMapOfpassportInformation.get("eyr")+"\n");
					return false;
				}
				else {
					if(!checkHeight(hashMapOfpassportInformation.get("hgt"))) {
						System.out.println(passport);
						System.out.println("Error at hgt validation. Value Received: "+hashMapOfpassportInformation.get("hgt")+"\n");
						return false;
					}
					else {
						if(!checkHairColor(hashMapOfpassportInformation.get("hcl"))) {
							System.out.println(passport);
							System.out.println("Error at hcl validation. Value Received: "+hashMapOfpassportInformation.get("hcl")+"\n");
							return false;
						}
						else {
							if(!cheackEyeColor(hashMapOfpassportInformation.get("ecl"))) {
								System.out.println(passport);
								System.out.println("Error at ecl validation. Value Received: "+hashMapOfpassportInformation.get("ecl")+"\n");
								return false;
							}
							else {
								if(!checkPassportID(hashMapOfpassportInformation.get("pid"))) {
									System.out.println(passport);
									System.out.println("Error at pid validation. Value Received: "+hashMapOfpassportInformation.get("pid")+"\n");
									return false;
								}
								else {
									return true;
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	public static boolean checkBirthYear(int byr) {
		if(byr>=1920 && byr<=2002) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkIssueYear(int iyr) {
		if(iyr>=2010 && iyr<=2020) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkExpirationYear(int eyr) {
		if(eyr>=2020 && eyr<=2030) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkHeight(String hgt) {
		// Need to see if its in or cm --> Example of hgt 180cm
		// So, get the last 2 positions for get the units
		String units =  hgt.substring(hgt.length()-2, hgt.length());
		int hight =  Integer.parseInt(hgt.substring(0, hgt.length()-2));
		boolean heighPass = false;
		
		switch(units) {
		case "cm":
			if(hight>=150 && hight<=193) {
				heighPass = true;
			}
			break;
		case "in":
			if(hight>=59 && hight<=76) {
				heighPass = true;
			}
			break;
		default:
			heighPass = false;
		}
		
		return heighPass;
	}
	
	public static boolean checkHairColor(String hairColor) {
		
		Pattern pattern = Pattern.compile("#{1}[0-9a-f]{6}");
	    Matcher mat = pattern.matcher(hairColor);                                                                           
	     if (mat.matches()) {
	         return true;
	     } else {
	        return false;                                                                                
	     }
	}
	
	public static boolean cheackEyeColor(String ecl) {
		HashSet<String> eyeColorAllowed = new HashSet<String>();
		eyeColorAllowed.add("amb");
		eyeColorAllowed.add("blu");
		eyeColorAllowed.add("brn");
		eyeColorAllowed.add("gry");
		eyeColorAllowed.add("grn");
		eyeColorAllowed.add("hzl");
		eyeColorAllowed.add("oth");
		
		if(eyeColorAllowed.contains(ecl)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean checkPassportID(String pid) {
		
		Pattern pattern = Pattern.compile("[0-9]{9}");
	    Matcher match = pattern.matcher(pid);                                                                           
	     if (match.matches()) {
	         return true;
	     } else {
	        return false;                                                                                
	     }
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
