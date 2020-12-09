package day1;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class SecondChallenge {
		
	protected static void solve(HashSet <Integer> hashSetOfNumbersReaded, int sumSearched) {
		Iterator<Integer> itr = hashSetOfNumbersReaded.iterator();
		
		while(itr.hasNext()) {
			int currentValue = itr.next();
			int searched = sumSearched - currentValue;
			
			// Call to the secondary function to search the new number. This one is going to return an integer, 0 if not found
			int productOfAll = solve2(hashSetOfNumbersReaded,searched);
			
			if(productOfAll!=0) { // Number Found, so time to stop
				System.out.println(productOfAll*currentValue);
				return;
			}			
		}
	
	}
	
	protected static int solve2(HashSet <Integer> hashSetOfNumbersReaded, int sumSearched) {
		Iterator<Integer> itr = hashSetOfNumbersReaded.iterator();
		
		while(itr.hasNext()) {
			int currentValue = itr.next();
			int searched = sumSearched - currentValue;
			
			if(hashSetOfNumbersReaded.contains(searched)) {
				return currentValue*searched;
				
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
			int sumSearched = 2020;
			int borderCase = 0;
			boolean needCheckBorderCase = false;
			int countOfBorderCases = 0;
	    	Scanner scanner = new Scanner(new FileReader("test-cases\\Day1.txt"));
	    	HashSet <Integer> hashSetOfNumbersReaded = new HashSet<Integer>();
	    	
	    	// Border case: If we have the n/3 number 3 times  
	    	if(sumSearched%3==0) {
	    		// It is even, so an even number divided by 2 its an integer so a check is needed
	    		borderCase = sumSearched/3;
	    		needCheckBorderCase = true;
	    	}
	    	
	    	while (scanner.hasNextLine()) {
	    		int value = scanner.nextInt();
	    		if(needCheckBorderCase==true && value==borderCase) {
	    			countOfBorderCases++;
	    			if(countOfBorderCases==3) {
	    				System.out.println(borderCase*borderCase);
	    			}
	    		}
	    		else {
	    			hashSetOfNumbersReaded.add(value);
	    		}
	    	}
	        
	    	
	    	solve(hashSetOfNumbersReaded, sumSearched);
	        scanner.close();

	}

}
