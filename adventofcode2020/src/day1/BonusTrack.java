package day1;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/* 
 * Bonus Track: What if, instead having 2 o 3 numbers who added gives a number, we have n numbers?
 */

public class BonusTrack {
		
	protected static int solve(HashSet <Integer> hashSetOfNumbersReaded, int sumSearched, int numberOfAddedNumbers, int numbersSeted) {
		Iterator<Integer> itr = hashSetOfNumbersReaded.iterator();
		int productOfAll = 0;
		numbersSeted++;
		while(itr.hasNext()) {
			int currentValue = itr.next();
			int searched = sumSearched - currentValue;
			
			// Call to the secondary function to search the new number. This one is going to return an integer, 0 if not found
			if(numbersSeted<numberOfAddedNumbers-1) {
				productOfAll = solve(hashSetOfNumbersReaded,searched, numberOfAddedNumbers, numbersSeted) * currentValue;
				if(productOfAll!=0) {
					return productOfAll;
				}	
			}
			else {
				if(hashSetOfNumbersReaded.contains(searched)) {
					productOfAll = currentValue*searched;
					if(productOfAll!=0) {
						return productOfAll;
					}	
				}
			}
		
		}
		return productOfAll;
	}
	
	
	public static void main(String[] args) throws IOException {
			
		int sumSearched = 2020;
			int borderCase = 0;
			boolean needCheckBorderCase = false;
			int countOfBorderCases = 0;
			int numberOfAddedNumbers = 3;
	    	Scanner scanner = new Scanner(new FileReader("test-cases\\Day1.txt"));
	    	
	    	
	    	HashSet <Integer> hashSetOfNumbersReaded = new HashSet<Integer>();
	    	
	    	// Border case: If we have the n/3 number 3 times  
	    	if(sumSearched%numberOfAddedNumbers==0) {
	    		// It is even, so an even number divided by 2 its an integer so a check is needed
	    		borderCase = sumSearched/numberOfAddedNumbers;
	    		needCheckBorderCase = true;
	    	}
	    	
	    	while (scanner.hasNextLine()) {
	    		int value = scanner.nextInt();
	    		if(needCheckBorderCase==true && value==borderCase) {
	    			countOfBorderCases++;
	    			if(countOfBorderCases==numberOfAddedNumbers) {
	    				System.out.println(borderCase*borderCase);
	    			}
	    		}
	    		else {
	    			hashSetOfNumbersReaded.add(value);
	    		}
	    	}
	        
	    	
	    	System.out.println(solve(hashSetOfNumbersReaded, sumSearched, numberOfAddedNumbers, 0));
	        scanner.close();

	}

}
