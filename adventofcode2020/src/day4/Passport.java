package day4;

public interface Passport {
	public boolean allRequiredFieldsPresent();
	public boolean checkBirthYear(int byr);
	public boolean checkIssueYear(int iyr);
	public boolean checkExpirationYear(int eyr);
	public boolean checkHeight(String hgt);
	public boolean checkHairColor(String hairColor);
	public boolean checkEyeColor(String ecl);
	public boolean checkPassportID(String pid);
	
}
