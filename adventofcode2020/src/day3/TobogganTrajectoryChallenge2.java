package day3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TobogganTrajectoryChallenge2 {

	// for the second challenge we only need to parameterize the function to add the rowsAdvance and the colsAdvance
	public static int countNumberOfTreesFound(char[][] pattern, int rowsAdvance, int colsAdvance) {
		int numberOfTreesFound = 0;
		
		int numberOfRows = pattern.length;
		int numberOfCols = pattern[0].length; // All the columns have the same length, then choose one of them.
		int rowIndex=0;
		int colIndex=0;
		
		while(rowIndex<numberOfRows-1) { // Until we arrive to the last line...
			
			// advance 3 positions to the right
			colIndex+=colsAdvance;
			
			// Are we out of range?
			if(colIndex>=numberOfCols) {
				// Then we need to repeat the pattern to the right, what is the same than reset the index and advance
				colIndex=colIndex%numberOfCols;
				
			}
			
			rowIndex+=rowsAdvance;
			
			if(pattern[rowIndex][colIndex]=='#') {
				numberOfTreesFound++;
			}
			
		}
		
		System.out.println("We have found "+numberOfTreesFound+" trees");
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
		
		long productOfTrees = 1; // Be careful!! The product grows too much and rises the integer range limit 
		productOfTrees=productOfTrees*countNumberOfTreesFound(patternArray,1,1);
		productOfTrees=productOfTrees*countNumberOfTreesFound(patternArray,1,3);
		productOfTrees=productOfTrees*countNumberOfTreesFound(patternArray,1,5);
		productOfTrees=productOfTrees*countNumberOfTreesFound(patternArray,1,7);
		productOfTrees=productOfTrees*countNumberOfTreesFound(patternArray,2,1);
		
		
		System.out.println("The final product is "+productOfTrees+" trees");

	}
}
