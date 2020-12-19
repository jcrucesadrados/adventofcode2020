package day14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DockingDataChallenge2 {
	private static ArrayList<String> listOfAddress;
	
	public static String applyMask (long input, String mask) {
		
		// Convert integer input to a binary string
		String inputConverted = Long.toBinaryString(input);
		String zero = "0";
		
		StringBuilder inputString = new StringBuilder(zero.repeat(mask.length()-inputConverted.length())+inputConverted);
		StringBuilder maskedInput = new StringBuilder(mask);
		
		/*
		 * Rules:
		 * - If the bitmask bit is 0, the corresponding memory address bit is unchanged.
		 * - If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
		 * - If the bitmask bit is X, the corresponding memory address bit is floating.

		 */
		
		int indexOf1Found = maskedInput.indexOf("1");
		boolean thereAreMore1 = true;
		if(indexOf1Found<0) {
			thereAreMore1=false;
		}
		while(thereAreMore1) {
			thereAreMore1=false;
			String element = "1";
			inputString.replace(indexOf1Found, indexOf1Found+1, element);
			int nextIndex = indexOf1Found + 1;
			if(nextIndex<mask.length()) {
				indexOf1Found = maskedInput.indexOf("1", nextIndex);
				if(indexOf1Found>0) {
					thereAreMore1 = true;
				}	
			}
		}
		
		int indexOfxFound = maskedInput.indexOf("X");
		boolean thereAreMoreX = true;
		if(indexOfxFound<0) {
			thereAreMoreX=false;
		}
		while(thereAreMoreX) {
			thereAreMoreX=false;
			String element = "X";
			inputString.replace(indexOfxFound, indexOfxFound+1, element);
			int nextIndex = indexOfxFound + 1;
			if(nextIndex<mask.length()) {
				indexOfxFound = maskedInput.indexOf("X", nextIndex);
				if(indexOfxFound>0) {
					thereAreMoreX = true;
				}	
			}
		}
		
		String output = inputString.toString();
		return output;
	}
	
	
	public static void appendNodes(Node originNode) {
		StringBuilder originalMask = new StringBuilder(originNode.get());
		int indexOfX = originalMask.indexOf("X");
		if(indexOfX<0) {
			// Finish of the combinations
			listOfAddress.add(originNode.get());
		}
		else{
			for (int i=0; i<=1; i++) {
				StringBuilder newStepMask = new StringBuilder(originalMask);
				newStepMask.replace(indexOfX, indexOfX+1, Integer.toString(i));
				Node nodeToAppend = new Node(newStepMask.toString());
				appendNodes(nodeToAppend);
			}
		}
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String testFilePath = "test-cases\\Day14.txt";
		Scanner scan = new Scanner(new FileReader(testFilePath));
		String mask="";
		long memoryAddress;
		long valueReaded;
		HashMap<Long, Long> memory = new HashMap<Long, Long>();
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String[] splitedLine = line.split(" = ");
			if(splitedLine[0].equals("mask")) { // We have the mask
				mask=splitedLine[1];
			}
			else {
				// We have a program line
				memoryAddress = Long.parseLong(splitedLine[0].substring(4, splitedLine[0].length()-1));
				valueReaded = Long.parseLong(splitedLine[1]);	
				
				// Now we need to prepare the Memory Address List
				String memoryAddressMasked = applyMask (memoryAddress, mask);
				
				// And compute for the different possibilities
				listOfAddress = new ArrayList<String>();
				Node memoryAddressNode = new Node(memoryAddressMasked);
				appendNodes(memoryAddressNode);
				
				// Once we have all the positions, store the value in all of them
				for(String finalAddress : listOfAddress) {
					memory.put(Long.parseUnsignedLong(finalAddress.toString(),2), valueReaded);
				}
				
				
			}
		}
		
		Long sumOfData = (long) 0;
		for(long memoryPointer : memory.keySet()) {
			sumOfData+=memory.get(memoryPointer);
		}
		
		System.out.println("The sum of all the values in the memory is "+sumOfData);		
	}

}
