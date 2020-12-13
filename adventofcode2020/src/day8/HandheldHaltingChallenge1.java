package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HandheldHaltingChallenge1 {
	
	public static void main (String[] args) throws IOException {
		List<String> example = Files.readAllLines(Path.of("test-cases\\Day8.txt"));
		int carriedSum = 0;
		// Load the program
		int programLoadCounter = 1;
		HashMap<Integer, Instruction> programInstructions = new HashMap<Integer, Instruction>();
		for(String line : example) {
			Instruction readedInstruction = new Instruction (line, programLoadCounter);
			programInstructions.put(programLoadCounter, readedInstruction);
			programInstructions.get(programLoadCounter).printInstruction();
			programLoadCounter++;
		}
		
		System.out.println("\nStarting program execution...\n");

		// start the program
		int programCounter=1;
		int stepNumber = 1;
		HashSet<Integer> runnedInstructions = new HashSet<Integer>();
		while(true) {
			System.out.println("Starting step "+stepNumber+" with carriedSum: "+carriedSum);
			Instruction instruction = programInstructions.get(programCounter);
			instruction.printInstruction();
			if(!runnedInstructions.contains(instruction.getInstructionID())) {
				try {
					runnedInstructions.add(instruction.getInstructionID());
					carriedSum = instruction.processInstruction(carriedSum);					
					programCounter=instruction.getNextInstruction();
					System.out.println("carriedSum Post: "+carriedSum+"\n");
					if(!programInstructions.containsKey(instruction.getNextInstruction())) {
						break;
					}
				}
				catch (Exception e){
					break;
				}
			}
			else {
				System.out.println("Alert! Infinite loop detected... Aborting program\n");
				break;
			}	
			
			stepNumber++;
		}
		
		
		System.out.println("Te value of carriedSum is: "+carriedSum);
		
	}
}
