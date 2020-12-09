package day3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TobogganTrajectoryChallenge1 {

	public static int countNumberOfTreesFound(char[][] pattern) {
		int numberOfTreesFound = 0;
		
		/* We need to advance 3 positions to the right + 1 down 
		 */
		
		int numberOfRows = pattern.length;
		int numberOfCols = pattern[0].length; // All the columns have the same length, then choose one of them.
		int rowIndex=0;
		int colIndex=0;
		
		while(rowIndex<numberOfRows-1) { // Until we arrive to the last line...
			
			// advance 3 positions to the right
			colIndex+=3;
			
			// Are we out of range?
			if(colIndex>=numberOfCols) {
				// Then we need to repeat the pattern to the right, what is the same than reset the index and advance
				colIndex=colIndex%numberOfCols;
				
			}
			
			rowIndex++;
			
			if(pattern[rowIndex][colIndex]=='#') {
				numberOfTreesFound++;
			}
			
		}
		
		return numberOfTreesFound;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Load the test 
		String testFilePath = "test-cases\\Day3.txt";
		Scanner scannerForCounts = new Scanner(new FileReader(testFilePath)); 
				
		//We are going to consider the patron as a Matrix (2D array) of number of lines rows x number of chars/line cols
		// As a 2D is a fixed dimensions data structure, we need to know first the dimensions
		
		int numberOfRows = 0;
		int numberOfCols = 0;
		while(scannerForCounts.hasNext()) {
			String line = scannerForCounts.next();
			numberOfCols=line.length();
			numberOfRows++;
		}
		System.out.println("We need a matrix of "+numberOfRows+" rows and "+numberOfCols+" cols");
		
		scannerForCounts.close();
		
		// Create a the pattern matrix
		Scanner scannerForOperations = new Scanner(new FileReader(testFilePath)); 
		char[][] patternArray = new char[numberOfRows][numberOfCols];
		
		int rowIndex = 0;
		while(scannerForOperations.hasNext()) {
			String line = scannerForOperations.next();
			for(int colIndex = 0; colIndex<line.length(); colIndex++) {
				patternArray[rowIndex][colIndex]=line.charAt(colIndex);
			}
			rowIndex++;
		}
		
		scannerForOperations.close();
		
		int numberOfTreesFound = countNumberOfTreesFound(patternArray);
		
		System.out.println("We have found "+numberOfTreesFound+" trees");

	}
}
