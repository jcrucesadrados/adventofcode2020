package day16;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class TicketTranslationChallenge1 {
	private static HashMap<Integer, String> tranlationMatrix = new HashMap<Integer, String>();
	
	public static void main(String[] args) throws FileNotFoundException {
		String testFilePath = "test-cases\\Day16.txt";
		Scanner inputScan = new Scanner(new FileReader(testFilePath));
		
		// The input has 3 blocks of information separated by a blank line
		int blockNumber = 1;
		boolean firstLineSkiped = false;
		int ticketsErrorRate = 0;
		
		while(inputScan.hasNext()) {
			// First block: rules acquisition
			String line = inputScan.nextLine();
			if(line.isBlank()) {
				// we have a block change
				blockNumber++;
				firstLineSkiped = false;
			}
			else{
				//Need to manage it depending on the block number
				switch(blockNumber) {
				case 1:
					// We are acquiring the rules / translation set 
					// The format is: class: 1-3 or 5-7
					// String line = "class: 1-3 or 5-7";
					String[] lineArray = line.split(":"); //[class] [ 1-3 or 5-7]
					String fieldName = lineArray[0];
					String[] arrayOfRanges = lineArray[1].split("or");
					
					// Populate the tranlationMatrix
					for(int i=0; i<arrayOfRanges.length; i++) {
						String ranges[] =  arrayOfRanges[i].trim().split("-");
						for(int j=Integer.parseInt(ranges[0]); j<=Integer.parseInt(ranges[1]); j++) {
							tranlationMatrix.put(j, fieldName);
						}
					}
					break;
				case 2:
					// Is our ticket 
					if(firstLineSkiped==true) {
						//Skip for challenge 1
					}
					else {
						firstLineSkiped=true;
					}
					
					break;
				case 3:
					if(firstLineSkiped==true) {
						// Need to detect the invalid (not in the translationMatrix)
						// The format is 7,3,47
						String[] fieldsValuesArray = line.split(",");
						for(String fieldValueString : fieldsValuesArray) {
							if(!tranlationMatrix.containsKey(Integer.parseInt(fieldValueString))) {
								ticketsErrorRate+=Integer.parseInt(fieldValueString);
							}
						}
					}
					else {
						firstLineSkiped=true;
					}
					break;
				}
			}
		}
		
		System.out.println("The ticket scanning error rate is: "+ticketsErrorRate);
	}
}
