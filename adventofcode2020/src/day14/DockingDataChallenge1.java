package day14;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class DockingDataChallenge1 {
	
	public static long applyMask (long input, String mask) {
		
		// Convert integer input to a binary string
		String inputConverted = Long.toBinaryString(input);
		String zero = "0";
		String inputString = zero.repeat(mask.length()-inputConverted.length())+inputConverted;
		
		StringBuilder maskedInput = new StringBuilder(mask);
		
		int indexOfxFound = maskedInput.indexOf("X");
		boolean thereAreMoreX = true;
		while(thereAreMoreX) {
			thereAreMoreX=false;
			String element = ""+inputString.charAt(indexOfxFound);
			maskedInput.replace(indexOfxFound, indexOfxFound+1, element);
			indexOfxFound = maskedInput.indexOf("X");
			if(indexOfxFound>0) {
				thereAreMoreX = true;
			}	
		}
		
		long output = Long.parseUnsignedLong(maskedInput.toString(),2);
		
		return output;
		
	    
	}
	
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String testFilePath = "test-cases\\Day14.txt";
		Scanner scan = new Scanner(new FileReader(testFilePath));
		String mask="";
		Long memoryAddress;
		Long valueToStore;
		Long valueReaded;
		HashMap<Long, Long> memory = new HashMap<Long, Long>();
		while(scan.hasNext()) {
			String line = scan.nextLine();
			System.out.println(line);
			String[] splitedLine = line.split(" = ");
			if(splitedLine[0].equals("mask")) { // We have the mask
				mask=splitedLine[1];
				System.out.println("Mask: "+mask+"\n");
			}
			else {
				// We have a program line
				memoryAddress = Long.parseLong(splitedLine[0].substring(4, splitedLine[0].length()-1));
				valueReaded = Long.parseLong(splitedLine[1]);
				valueToStore = applyMask(valueReaded, mask);
				System.out.println("Memory Address: "+memoryAddress+" ; Value readed: "+valueReaded);
				System.out.println("Number to store: "+valueToStore+"\n");
				memory.put(memoryAddress, valueToStore);
			}
		}
		
		Long sumOfData = (long) 0;
		for(long memoryPointer : memory.keySet()) {
			sumOfData+=memory.get(memoryPointer);
		}
		
		System.out.println("The sum of all the values in the memory is "+sumOfData);
		
	}

}
