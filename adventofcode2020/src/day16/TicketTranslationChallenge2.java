package day16;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TicketTranslationChallenge2 {
	
	/* Variables for the different actions*/
	
	private static HashMap<String, ArrayList<ArrayList<Integer>>> translationMatrix = new HashMap<String,  ArrayList<ArrayList<Integer>>>();
	private static HashSet<String> hashSetOfFieldsNames = new HashSet<String>();
	private static ArrayList<HashSet<String>> positionAndFieldName = new ArrayList<HashSet<String>>();
	private static HashSet<String> hashSetOfExistingValues = new HashSet<String>();
	private static int numberOfInvalid=0;
	private static int numberOfTickets=0;
	
	/* Given an array with the values of the ticket, checks if any value isn't contained in the set of possible elements */
	private static boolean isValid(String[] fieldsArray) {
		
		for(String field : fieldsArray) {
			if(!hashSetOfExistingValues.contains(field)) {
				return false;
			}
		}	
		return true;
	}
	
	private static void acquireRulesForTranslation(String rule) {
		// The format is: class: 1-3 or 5-7
		String[] ruleArray = rule.split(":"); //[class] [ 1-3 or 5-7]
		String fieldName = ruleArray[0];
		String[] arrayOfRanges = ruleArray[1].split("or"); //[ 1-3] [5-7] --> Be careful whit the spaces!!
		
		// Populate the tranlationMatrix
		// We are going to store, for a field a collection of possible ranges. Each range expressed as an array of min-max
		ArrayList<ArrayList<Integer>> arrayListOfRanges = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<arrayOfRanges.length; i++) {
			String ranges[] = arrayOfRanges[i].split("-"); // [5][7]
			ArrayList<Integer> arrayListOfRangeValues = new ArrayList<Integer>();
				arrayListOfRangeValues.add(Integer.parseInt(ranges[0].trim()));
				arrayListOfRangeValues.add(Integer.parseInt(ranges[1].trim()));
			Collections.sort(arrayListOfRangeValues); // Ensure that the first position is the lowest value (for the future) 
			arrayListOfRanges.add(arrayListOfRangeValues); // Save the range in the arrayList with the range for the field
			
			// For the future validation, we need to check if the value is valid for any field. Store the values for future
			// O(1) search operation
			for(int j=Integer.parseInt(ranges[0].trim()); j<=Integer.parseInt(ranges[1].trim()); j++) {
				hashSetOfExistingValues.add(Integer.toString(j));
			}
		}
		
		translationMatrix.put(fieldName, arrayListOfRanges); // Store the rule
		
		// For the future we are going to need a list with the possible names of the fields in order to dismiss the names
		hashSetOfFieldsNames.add(fieldName);
	}
	
	private static void rulesCompilanceProcess(String[] ticketValuesArray) {
		// For each element of the ticket (for each position)
		for(int position = 0; position<ticketValuesArray.length; position++) {
			// Get the list of possible field names for the position
			HashSet<String> fieldsOnPosition =  positionAndFieldName.get(position); 
			// Need to copy the Hash Set in order to manipulate the lists and do not have outbound exceptions
			HashSet<String> newFieldsOnPosition =  new HashSet<String>(fieldsOnPosition);
			// Get the value in order to check the policy
			Integer fieldValueInteger = Integer.parseInt(ticketValuesArray[position]);
			
			// Once we have our list of possible field names, we are going to check if the value agrees with the range of values
			// Allowed. If it doesn't, then we can remove the name from the list.
			for(String fieldNameOnPosition : fieldsOnPosition) {
				//Get the range from the policy of the field name
				ArrayList<ArrayList<Integer>> arrayListOfRangesOnPosition = translationMatrix.get(fieldNameOnPosition);
				// For each range, check if the value is out of range
				boolean isOutRange = true;
				for(ArrayList<Integer> thisRange : arrayListOfRangesOnPosition) {
					if(fieldValueInteger>=thisRange.get(0) && fieldValueInteger<=thisRange.get(1)) {
						isOutRange = false;
						break;
					}
				}
				// If the value is out of range, remove it
				if(isOutRange) {
					newFieldsOnPosition.remove(fieldNameOnPosition);
				}
			}
			
			// Finally update the global list of lists
			positionAndFieldName.set(position, newFieldsOnPosition);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String testFilePath = "test-cases\\Day16.txt";
		Scanner inputScan = new Scanner(new FileReader(testFilePath));
		
		
		int blockNumber = 1;
		boolean firstLineSkiped = false;
		long myFieldsProduct = 1;
		ArrayList<String> valuesOfOurTicket = new ArrayList<String>();
		
		
		while(inputScan.hasNext()) {
			String line = inputScan.nextLine(); // Read the line
			// The input has 3 blocks of information separated by a blank line. Each block operates by it self
			if(line.isBlank()) {
				// we have a block change
				blockNumber++;
				firstLineSkiped = false; // Restart the contol for the first line
			}
			else{
				//Need to manage it depending on the block number
				switch(blockNumber) {
				case 1:
					// We are acquiring the rules / translation set 
					// The format is: class: 1-3 or 5-7
					acquireRulesForTranslation(line);
					
					break;
				case 2:
					// Is our ticket. As we are only checking the information, but we need to set the positions, 
					// in this point of the execution we only have to store it for the future
					// First line is non profitable information, so dismiss it. We need the second line that it is the information
					if(firstLineSkiped==true) {
						String[] fieldsValuesArray = line.split(",");
						for(String fieldValueString : fieldsValuesArray) {
							valuesOfOurTicket.add(fieldValueString);
						}
						
					}
					else {
						firstLineSkiped=true; // Check the first line as skipped for the parsing process
						// We are going to take advantage of this case for set a list for the different positions of the tickets
						// and their possible field name. The idea is have a list of field names for each position and then
						// remove each elements of the list that don't agrees with the rules of the field name according to
						// the given value of the ticker (it's the learning process of the case 3)
						for(int i=0; i<hashSetOfFieldsNames.size(); i++) {
							positionAndFieldName.add(new HashSet<String>(hashSetOfFieldsNames));
						}
						
					}
					
					break;
				case 3:
					/*
					 * This is the learning case. In this block we have a collection of tickets. For each ticket the order of the fields
					 * is always the same, that is, if the first field is, for example, duration in one ticket, the first position is 
					 * duration for all the other tickets.
					 * The idea is parse all the valid tickets. For each position, we will get the list of possible field names and 
					 * we are going to check the value with the given range at the first case. If the value doesn't agrees with the
					 * policy of the field name selected, then we can remove the name from the list.
					 * 
					 * ¡Be careful! There are some tickets hose values are not valid for any field so, if we use this ticket, it will
					 * remove all the names from the list. This is bad...
					 * 
					 */
					
					// Like in the second case, first line has no valuable information, so we need to skip it in the same way.
					if(firstLineSkiped==true) {
						
						// First, we need to detect the invalid (not in the translationMatrix) ticket and dismiss them.
						// The format is 7,3,47
						String[] fieldsValuesArray = line.split(","); //[7][3][47]
						numberOfTickets++; // just for statistics
						
						// Given the array of values, need to check that it value agrees with almost 1 policy (is valid)
						if(isValid(fieldsValuesArray)) { 
							rulesCompilanceProcess(fieldsValuesArray);
						}
						else {
							numberOfInvalid++; // Just for statistics
						}
					}
					else {
						firstLineSkiped=true;
					}
					break;
				}
			}
		}
		
		/*
		 * Once we have finished the learning process, having at least one field position identified is expected. But it is possible
		 * that other fields haven't an unique element (we haven dismissed all the impossible options).
		 * So, if we find an unique element, we can save it as definitive position-name and remove it from the other lists
		 * Iterating it while there are not changes, it is expected to empty the possible names list and have all the elements
		 * in their correct position
		 */
		
		// Set the final relationship data structure
		HashMap<String, Integer> finalFieldsPosition = new HashMap<String, Integer>();
		
		// Loop control variable. No changes means no option to simplify the list so, if false, is expected to have find all the elements
		boolean thereAreChanges = true;
		
		while(thereAreChanges) {
			thereAreChanges = false; // Infinite loop prevention
			String fieldName="";
			for(int position=0; position<positionAndFieldName.size(); position++) {
				
				if(positionAndFieldName.get(position).size()==1) { // Fin the first element with an unique value
					
					fieldName = positionAndFieldName.get(position).iterator().next(); // Get the field name
					finalFieldsPosition.put(fieldName, position); // Save it with it final position
					thereAreChanges = true; // Allow the next iteration of the while loop
					break;
				}	
			}
			
			if(thereAreChanges) { // That means that we have found an unique element and we have to delete it from the lists
				
				// Create an auxiliar list of lists of fields in order to manipulate it avoiding possible outbound exceptions
				ArrayList<HashSet<String>> newPositionAndFieldName = new ArrayList<HashSet<String>>();
				
				// Edit the hashsets
				for(HashSet<String> positionHashSet : positionAndFieldName) {
					positionHashSet.remove(fieldName);
					newPositionAndFieldName.add(positionHashSet);
				}
				positionAndFieldName = new ArrayList<HashSet<String>>(newPositionAndFieldName);
			}
		}
		
		System.out.println(valuesOfOurTicket);
		for(String field : finalFieldsPosition.keySet()) {
			System.out.println(field+": "+valuesOfOurTicket.get(finalFieldsPosition.get(field)));
			String[] fieldArray = field.split(" ");
			if(fieldArray[0].equals("departure")) {
				myFieldsProduct*=Long.parseLong(valuesOfOurTicket.get(finalFieldsPosition.get(field)));
			}
		}
	
		System.out.println("Invalid Tickets: "+numberOfInvalid+" of "+numberOfTickets+" total tickets");
		System.out.println("The product of my departure fields is: "+myFieldsProduct); 
	}
}
