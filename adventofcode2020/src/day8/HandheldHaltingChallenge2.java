package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class HandheldHaltingChallenge2 {
	
	public static int runProgram(ArrayList<Instruction> memoryProgram) throws FlowControlException  {
		int carriedSum = 0;
		int programCounter=0;
		
		HashSet<Integer> runnedInstructions = new HashSet<Integer>();
		while(true) {
			
			Instruction instruction = memoryProgram.get(programCounter);

			if(!runnedInstructions.contains(instruction.instructionID)) {
				runnedInstructions.add(instruction.instructionID);
				carriedSum = instruction.processInstruction(carriedSum);					
				programCounter=instruction.getNextInstruction();

				if(instruction.getNextInstruction()>memoryProgram.size()-1) {
					return carriedSum;
				}	
			}
			else {
				runnedInstructions.clear();
				throw new FlowControlException(1, "This instruccion has been used");
			}	
			
		}
	}
	
	public static void printMemoryProgram(ArrayList<Instruction> memoryProgram) {
		System.out.println("\nPrinting program...");
		for(Instruction thisInstruction : memoryProgram) {
			thisInstruction.printInstruction();
		}
		System.out.println();
	}
		
	public static void main (String[] args) throws IOException {
		List<String> example = Files.readAllLines(Path.of("test-cases\\Day8.txt"));
		int carriedSum = 0;
		// Load the program
		int programLoadCounter = 0;
		ArrayList<Instruction> memoryProgram = new ArrayList<Instruction>();
		for(String line : example) {
			Instruction readedInstruction = new Instruction (line, programLoadCounter);
			memoryProgram.add(readedInstruction);
			programLoadCounter++;
		}
		
		System.out.println("\nStarting program execution...\n");

		//Check the programMemory
		
		for(Instruction thisInstruction : memoryProgram) {
			if(!thisInstruction.instructionType.equals("acc")) {
				System.out.println("\nChangeing Instruction #"+thisInstruction.instructionID);
				try {
					thisInstruction.toggleInstruction();
					//thisInstruction.printInstruction();
					//printMemoryProgram(memoryProgram);
					try {
						carriedSum = runProgram(memoryProgram);
						break;
					}
					catch (FlowControlException e){
						System.out.println("Toggleing again...");
						e.showExceptionDetail();
						thisInstruction.toggleInstruction();
						//printMemoryProgram(memoryProgram);
						continue;
					}
				}
				catch (FlowControlException e){
					e.showExceptionDetail();
					e.getMessage();
				}
			}
		}
		
		System.out.println("Te value of carriedSum is: "+carriedSum);
		
	}
}
