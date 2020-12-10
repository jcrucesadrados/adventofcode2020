package day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NorthPolePassport implements Passport {

	// Constrains to validation
	protected HashMap<String, String> passport;
	
	protected int[] byrRange;
	protected int[] iyrRange;
	protected int[] eyrRange;
	protected int[] hgtCmRange;
	protected int[] hgtInRange;

	protected Pattern hairColorPattern;
	protected HashSet<String> eyeColorsAllowed;
	protected Pattern passportIDPattern;
	protected ArrayList<String> requiredFields;
	
	
	// All the setters 
	
	public void setMaxByr(int maxByr) {
		this.byrRange[1]=maxByr;
	}
	
	public void setMinByr(int minByr) {
		this.byrRange[0]=minByr;
	}
	
	public void setMaxIyr(int maxIyr) {
		this.iyrRange[1]=maxIyr;
	}
	
	public void setMinIyr(int minIyr) {
		this.iyrRange[0]=minIyr;
	}
	
	public void setMaxEyr(int maxEyr) {
		this.eyrRange[1]=maxEyr;
	}
	
	public void setMinEyr(int minEyr) {
		this.eyrRange[0]=minEyr;
	}
	
	public void setMinHgt(int minHeight, String units) throws Exception  {
	
		switch(units) {
		case "cm":
			hgtCmRange[0]=minHeight;
			break;
		case "in":
			hgtInRange[0]=minHeight;
			break;
		default:
			throw new Exception("Unit not avalailable for this type of Passport");		
		}
	}
	
	public void setMaxHgt(int maxHeight, String units) throws Exception  {
		
		switch(units) {
		case "cm":
			hgtCmRange[1]=maxHeight;
			break;
		case "in":
			hgtInRange[1]=maxHeight;
			break;
		default:
			throw new Exception("Unit not avalailable for this type of Passport");		
		}
	}
	
	public void setHairColorPattern(Pattern hairColorPattern) {
		this.hairColorPattern=hairColorPattern;
	}
	
	public void addEyeColorAllowed(String ecl) {
		this.eyeColorsAllowed.add(ecl);
	}
	
	public void removeEyeColorAllowed(String ecl) {
		this.eyeColorsAllowed.remove(ecl);
	}
	
	public void clearEyeColorsAllowed() {
		this.eyeColorsAllowed.clear();
	}
	
	public void setPassportIDPattern(Pattern passportIDPattern) {
		this.passportIDPattern=passportIDPattern;
	}
	
	
	// All the getters
	
	public int getMaxByr() {
		return this.byrRange[1];
	}
	
	public int getMinByr() {
		return this.byrRange[0];
	}
	
	public int getMaxIyr() {
		return this.iyrRange[1];
	}
	
	public int getMinIyr() {
		return this.iyrRange[0];
	}
	
	public int getMaxEyr() {
		return this.eyrRange[1];
	}
	
	public int getMinEyr() {
		return this.eyrRange[0];
	}
	
	public int getMinHgt(String units) throws Exception  {
	
		switch(units) {
		case "cm":
			return this.hgtCmRange[0];
		case "in":
			return this.hgtInRange[0];
		default:
			throw new Exception("Unit not avalailable for this type of Passport");		
		}
	}
	
	public int getMaxHgt(String units) throws Exception  {
		
		switch(units) {
		case "cm":
			return this.hgtCmRange[1];
			
		case "in":
			return this.hgtInRange[1];
			
		default:
			throw new Exception("Unit not avalailable for this type of Passport");		
		}
	}
	
	public Pattern getHairColorPattern() {
		return this.hairColorPattern;
	}
	
	public HashSet<String> getEyeColorsAllowed() {
		return this.eyeColorsAllowed;
	}
		
	public Pattern getPassportIDPattern() {
		return this.passportIDPattern;
	}
	
	// Constructor
	
	public NorthPolePassport(String passport) {
		
		// Initialize all the variables
		this.byrRange = new int[]{1920,2002};
		this.iyrRange = new int[]{2010,2020};
		this.eyrRange = new int[]{2020,2030};
		this.hgtCmRange = new int[]{150,193};
		this.hgtInRange = new int[]{59,76};
		
		this.hairColorPattern = Pattern.compile("#{1}[0-9a-f]{6}");
		
		this.eyeColorsAllowed = new HashSet<String>();
		this.eyeColorsAllowed.add("amb");
		this.eyeColorsAllowed.add("blu");
		this.eyeColorsAllowed.add("brn");
		this.eyeColorsAllowed.add("gry");
		this.eyeColorsAllowed.add("grn");
		this.eyeColorsAllowed.add("hzl");
		this.eyeColorsAllowed.add("oth");
		
		this.passportIDPattern = Pattern.compile("[0-9]{9}");
		
		// Required fields are a constrain of the passport and can not be changed
		this.requiredFields = new ArrayList<String>();
		this.requiredFields.add("byr");
		this.requiredFields.add("iyr");
		this.requiredFields.add("eyr");
		this.requiredFields.add("hgt");
		this.requiredFields.add("hcl");
		this.requiredFields.add("ecl");
		this.requiredFields.add("pid");
		
		// Set the passport
		this.passport = new HashMap<String, String>();
		String[] passportFields = passport.split(" ");
		for(int i=0; i<passportFields.length; i++) {
			String[] keyValueField = passportFields[i].split(":");
			this.passport.put(keyValueField[0], keyValueField[1]);
		}
	}
	
	public boolean allRequiredFieldsPresent() {
		boolean thePassportPass = true;
		
		for(int i=0; i<this.requiredFields.size(); i++) {
			if(!this.passport.containsKey((this.requiredFields.get(i)))){
				return false;
			}
		}
		
		// If we are here is because all the mandatory fields are present. Lets Validate
		
		return thePassportPass;
		
	}

	public boolean checkBirthYear(int byr) {
		if(byr>=getMinByr() && byr<=getMaxByr()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkIssueYear(int iyr) {
		if(iyr>=getMinIyr() && iyr<=getMaxIyr()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkExpirationYear(int eyr) {
		if(eyr>=getMinEyr() && eyr<=getMaxEyr()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkHeight(String hgt) {
		// Need to see if its in or cm --> Example of hgt 180cm
		// So, get the last 2 positions for get the units
		String units =  hgt.substring(hgt.length()-2, hgt.length());
		int hight =  Integer.parseInt(hgt.substring(0, hgt.length()-2));
		
		try {
			if(hight>=getMinHgt(units) && hight<=getMaxHgt(units)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}

	public boolean checkHairColor(String hairColor) {
		Matcher match = getHairColorPattern().matcher(hairColor);                                                                           
	    if (match.matches()) {
	        return true;
	    } 
	    else {
	       return false;                                                                                
	    }
	}

	public boolean checkEyeColor(String ecl) {
		return this.eyeColorsAllowed.contains(ecl);
	}

	public boolean checkPassportID(String pid) {
		Matcher match = getPassportIDPattern().matcher(pid);                                                                           
	    if (match.matches()) {
	        return true;
	    } 
	    else {
	       return false;                                                                                
	    }
	}
	
	protected boolean checkPassportField(String fieldName) {
		boolean isValid;
		
		try {
			switch(fieldName){
			case "byr":
				isValid = checkBirthYear(Integer.parseInt(passport.get(fieldName)));
				break;
			case "iyr":
				isValid = checkIssueYear(Integer.parseInt(passport.get(fieldName)));
				break;
			case "eyr":
				isValid = checkExpirationYear(Integer.parseInt(passport.get(fieldName)));
				break;
			case "hgt":
				isValid = checkHeight(passport.get(fieldName));
				break;
			case "hcl":
				isValid = checkHairColor(passport.get(fieldName));
				break;
			case "ecl":
				isValid = checkEyeColor(passport.get(fieldName));
				break;
			case "pid":
				isValid = checkPassportID(passport.get(fieldName));
				break;
			default:
				throw new Exception("Field not valid for this passport type");
			}
		}
		catch (Exception e) {
			isValid = false;
		}

		return isValid;
	}
	
	public boolean isAValidPassport() {
		
		if(allRequiredFieldsPresent()) {
			for(String field : this.requiredFields) {
				if(!checkPassportField(field)) {
					return false;
				}
			}
		}
		else {
			return false;
		}
		
		return true;
	}

}
