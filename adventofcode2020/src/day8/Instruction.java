package day8;

public class Instruction {
	
	protected int instructionID;
	protected String instructionType;
	protected char instructionSign;
	protected int instructionValue;
	protected int nextInstructionID;
	protected int currentStackValue;
	
	public Instruction(String textInstruction, int sequence) {
		// Format of textInstruction: nop +0
		String[] arrayOfInstrucction = textInstruction.split(" ");
		this.instructionID = sequence;
		this.instructionType = arrayOfInstrucction[0];
		this.instructionSign = arrayOfInstrucction[1].charAt(0);
		this.instructionValue = Integer.parseInt(arrayOfInstrucction[1].substring(1, arrayOfInstrucction[1].length()));
		setNextInstructionID();
	}
	
	private void setNextInstructionID() {
		if(this.instructionType.equals("jmp")) {
			if(instructionSign=='+') {
				nextInstructionID=instructionID+instructionValue;
			}
			else {
				nextInstructionID=instructionID-instructionValue;
			}
		}
		else {
			nextInstructionID=instructionID+1;
		}
	}
	

	public int getNextInstruction() {
		
		return this.nextInstructionID;
	}
	
	public int getInstructionID() {
		return this.instructionID;
	}

	public int processInstruction(int currentStackValue) throws FlowControlException {
		int response = 0;

		if(this.instructionType.equals("acc")) {
			if(this.instructionSign=='+') {
				response = currentStackValue + instructionValue;
			}
			else {
				response = currentStackValue - instructionValue;
			}
		}
		else {
			response = currentStackValue;
		}
		
		this.currentStackValue=response;
		return response;
	}
	
	public void printInstruction() {
		System.out.println(instructionID+"-->"+instructionType+" "+instructionSign+instructionValue+" --> NextInstruction: "+nextInstructionID);
	}
	
	public void setInstructionType(String newType) {
		this.instructionType=newType;
		
		//Change the next instruction
		if(newType.equals("jmp")) {
			if(this.instructionSign=='+')
				this.nextInstructionID=this.instructionID+this.instructionValue;
			else
				this.nextInstructionID=this.instructionID-this.instructionValue;
		}
		else {
			this.nextInstructionID=this.instructionID+1;
		}
	}
	
	public void toggleInstruction() throws FlowControlException {
		switch (this.instructionType) {
		case "jmp":
			setInstructionType("nop");
			break;
		case "nop":
			setInstructionType("jmp");
			break;
		default:
			throw new FlowControlException(2, "Type of instruction not valid for toggle");
		}
		
	}
	
}
	

