package day5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BinaryBoardingChallenge2 {
	
	public static int getSeatID(String coordinates) {
		int seatID = 0;
		// The coordinates has the next format: XXXXXXXYYY
		// The first 8 positions allocates the row
		// The last 3 positions allocates the column
		String rowCoordinates = coordinates.substring(0,7);
		String colCoordinates = coordinates.substring(7,coordinates.length());
		
		//System.out.println(coordinates+"--> row coordinates: "+rowCoordinates+" ; col coordinates: "+colCoordinates);
		
		int rowNumber = coordinates2numPosition(rowCoordinates);
		int colNumber = coordinates2numPosition(colCoordinates);
		
		seatID = rowNumber*8+colNumber;
		
		return seatID;
	}
	
	public static int findMySeat(ArrayList<Integer> onBoardList) {
		int mySeat = 0;
		Collections.sort(onBoardList);
		int nextExpected = 0;
		for(int i=0; i<onBoardList.size()-1; i++) {
			nextExpected=onBoardList.get(i)+1;
			if(nextExpected!=onBoardList.get(i+1)) {
				if(nextExpected+1!=onBoardList.get(i+2)) {
					return nextExpected;
				}
			}
		}
		
		return mySeat;
	}
	
	protected static int coordinates2numPosition (String coordinates) {
		int numPosition = 0;
		int[] intervalOfPositions = new int[] {0, (int)Math.pow(2, coordinates.length())-1};
		
		//System.out.println("["+intervalOfPositions[0]+", "+intervalOfPositions[1]+"]");
		
		for(int i=0; i<coordinates.length(); i++) {
			if(i!=coordinates.length()-1){ // We are in the middle case
				int avg = (intervalOfPositions[0]+intervalOfPositions[1])/2;
				
				// Then two possible options F or L means the lower half; B or R means the higher half
				if(coordinates.charAt(i)=='F' || coordinates.charAt(i)=='L') {
					// The left side of the interval is the same, update the right side
					intervalOfPositions[1]=avg;
				}
				else {
					// The left side is the avg and the right side is the same
					intervalOfPositions[0]=avg+1;
				}
				//System.out.println(coordinates.charAt(i)+"--> ["+intervalOfPositions[0]+", "+intervalOfPositions[1]+"]");
			}
			else {
				//Need to choose between a side
				if(coordinates.charAt(i)=='F' || coordinates.charAt(i)=='L') {
					numPosition=intervalOfPositions[0];
				}
				else {
					numPosition=intervalOfPositions[1];
				}
			}
		}
		
		//System.out.println("Number position is: "+numPosition);
		return numPosition;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Load the test 
		String testFilePath = "test-cases\\Day5.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
		
		ArrayList<Integer> onBoardList = new ArrayList<Integer>();
		while(scanner.hasNext()) {
			String line = scanner.next();
			onBoardList.add(getSeatID(line));			
		}
		
		int mySeat = findMySeat(onBoardList);
		System.out.println("My seat ID is "+mySeat);
		
	}
}
