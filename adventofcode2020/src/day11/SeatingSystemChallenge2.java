package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SeatingSystemChallenge2 {
	
	public static char[][] grid;
	public static int numOfOccupiedSeats = 0;
	public static boolean gridHasChanged = true;
	
	public static void applyRules(char[][] gridCopy){
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(gridCopy[i][j]!='.') {
					int adjacentOccupiedSeats = checkOccupiedAdjacentSeats(gridCopy, i, j);
					//System.out.println("-- Occupied "+adjacentOccupiedSeats);
					if(gridCopy[i][j]=='L' && adjacentOccupiedSeats==0) {
						grid[i][j]='#';
						numOfOccupiedSeats++;
						gridHasChanged=true;
					}
					else {
						if(gridCopy[i][j]=='#' && adjacentOccupiedSeats>=5) {
							grid[i][j]='L';
							numOfOccupiedSeats--;
							gridHasChanged=true;
						}
					}
				}
			}
		}
		
	}
	
	public static int checkOccupiedAdjacentSeats(char[][] gridCopy, int positionI, int positionJ) {
		//System.out.println("Checking for positions with center ("+positionI+", "+positionJ+")");
			int occupiedSeats = 0;
dirLoop:	for(Direction thisDirection : Direction.values()) { // For each direction stored in the enum...
				int i=positionI+thisDirection.i;
				int j=positionJ+thisDirection.j;
				//We are going to advance until we are inside the matrix or we find a seat
				boolean weAreInsideTheMatrix = areWeInsideTheMatrix(gridCopy,i,j);
				//System.out.println("-- Starting on position ("+i+", "+j+")");
				while(weAreInsideTheMatrix) { 
					// Let check the value of the position
					//System.out.println("---- Checking for positions("+i+", "+j+")");
					char valueAtPosition = gridCopy[i][j];
					switch(valueAtPosition) {
					case 'L':
						// It is a free place
						// As we have seen a seat, just break the while and check the next direction. Jump to dirLopp
						//System.out.println("------ Free seat");
						continue dirLoop;
					case '#':
						// It is an occupied place
						// Increment the count and let see the next direction
						//System.out.println("------ Occuped seat");
						occupiedSeats++;
						continue dirLoop;
					default:
						// This is the case for no L and no # so a . is expected.
						// Checking for the next position in the same direction is needed so: increment i,j and repeat
						// if we carry on been inside the matrix
						//System.out.println("------ NO seat");
						i+=thisDirection.i;
						j+=thisDirection.j;
						weAreInsideTheMatrix = areWeInsideTheMatrix(gridCopy,i,j);
					break;
	
					}
				}
		}

		
		return occupiedSeats;
	}
	
	/*
	 *  [ ] [ ] [ ] [ ] [ ]
	 *  
	 *  [ ]             [ ]
	 *  
	 *  [ ]      X      [ ]
	 *  
	 *  [ ]             [ ]
	 *  
	 *  [ ] [ ] [ ] [ ] [ ]
	 */
	
	public static boolean isOccupied (char[][] gridCopy, int i, int j) {
		boolean isOccupied = false;
		
		if(i<0 || i>gridCopy.length-1) {
			return false;
		}
		if(j<0 || j>gridCopy[i].length-1) {
			return false;
		}
		if(gridCopy[i][j]=='#') {
			isOccupied=true;
		}
		
		return isOccupied;
	}
	
	public static boolean areWeInsideTheMatrix(char[][] gridCopy, int i, int j) {
		if(i<0 || i>gridCopy.length-1) {
			return false;
		}
		if(j<0 || j>gridCopy[i].length-1) {
			return false;
		}
		return true;
	}
	
	public static void printMatrix() {
		System.out.println();
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(j==grid[0].length-1) {
					System.out.println(grid[i][j]);
				}
				else {
					System.out.print(grid[i][j]+" ");
				}
			}
		}
	}
	
	public static char[][] copyMatrix() {
		char[][] copy = new char[grid.length][grid[0].length];
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				copy[i][j]=grid[i][j];
			}
		}
		
		return copy;
	}

	public static void main(String[] args) throws IOException {
		// Read the input
		String testFilePath = "test-cases\\Day11.txt";
		List<String> input = Files.readAllLines(Path.of(testFilePath));
		
		// Create a Matrix
		grid = new char[input.size()][input.get(0).length()]; 
		for(int i=0; i<input.size(); i++) {
			for(int j=0; j<input.get(i).length(); j++){
				grid[i][j] = input.get(i).charAt(j);
			}	
		}
		
		//printMatrix();		
		while(gridHasChanged) {
			gridHasChanged=false; // Prevent infinite loops
			char[][] copyOfGrid = copyMatrix();
			applyRules(copyOfGrid);	
			//printMatrix();
		}
		
		System.out.println("There are "+numOfOccupiedSeats+" seats occupied");
		
		
	}

}
