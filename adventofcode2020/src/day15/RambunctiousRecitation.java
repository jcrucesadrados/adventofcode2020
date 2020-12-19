package day15;

import java.util.ArrayList;
import java.util.HashMap;

public class RambunctiousRecitation {
	/*
	 * Rules: 
	 * - If that was the first time the number has been spoken, the current player says 0.
	 * - Otherwise, the number had been spoken before; the current player announces how many turns apart the number is from when it was previously spoken.
	 */
	
	// This hashmap stores the numbers shown and array with the number of times shown and the last turn
	private static HashMap<Long, ArrayList<Long>> hashMapOfOccurrences;
	private static long turn;
	private static long lastSpokenNumber;
	
	public RambunctiousRecitation(long[] startingArray) {
		// Initialize the hashmap
		hashMapOfOccurrences = new HashMap<Long, ArrayList<Long>>();
		turn = (long) 0;	
		for(int i=0; i<startingArray.length; i++) {
			// As all of them are starting numbers, they are non spoken numbers
			turn++;
			long spokenNumber = startingArray[i];
			// First time spoken;
			ArrayList<Long> arrayListOfTurns = new ArrayList<Long>();
			arrayListOfTurns.add(turn);
			hashMapOfOccurrences.put(spokenNumber, arrayListOfTurns);
			lastSpokenNumber=spokenNumber;
		}
	}
	
	public long getTurn() {
		return turn;
	}
	
	public long getLastSpokenNumber() {
		return lastSpokenNumber;
	}
	
	public void playStep() {
		// Advance the turn counter
		turn++; 
		long elveSais = 0;
		
		// Get the last spoken number and check if it is shown
		if(!hashMapOfOccurrences.containsKey(lastSpokenNumber)) {
			// First time spoken;
			ArrayList<Long> arrayListOfTurns = new ArrayList<Long>();
			arrayListOfTurns.add(turn);
			hashMapOfOccurrences.put(lastSpokenNumber,arrayListOfTurns);
			elveSais = 0;
		}
		else {
			int timesSpoken = hashMapOfOccurrences.get(lastSpokenNumber).size();
			ArrayList<Long> turnsSpoken = hashMapOfOccurrences.get(lastSpokenNumber);
			if(timesSpoken==1) { // It was the first time shown, 
				elveSais = 0;
			}
			else {
				elveSais = turnsSpoken.get(turnsSpoken.size()-1)-turnsSpoken.get(turnsSpoken.size()-2);
			}
			// Increase the elements
			if(hashMapOfOccurrences.containsKey(elveSais)) {
				ArrayList<Long> listOfPastTurns = hashMapOfOccurrences.get(elveSais);
				listOfPastTurns.add(turn);
				hashMapOfOccurrences.put(elveSais, listOfPastTurns);
			}
			else {
				ArrayList<Long> arrayListOfTurns = new ArrayList<Long>();
				arrayListOfTurns.add(turn);
				hashMapOfOccurrences.put(elveSais, arrayListOfTurns);
			}
			lastSpokenNumber=elveSais;
		}		
	}
	
}
