package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SeatingSystemChallenge1 {
	
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
						if(gridCopy[i][j]=='#' && adjacentOccupiedSeats>=4) {
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
		//System.out.println("Checking for positions ("+positionI+", "+positionJ+")");
		int occupiedSeats = 0;
		int distance = 1;
		
		for(int i = positionI-distance; i<=positionI+distance; i++) {
			for(int j = positionJ-distance; j<=positionJ+distance; j++) {
				//System.out.println("- Entering ("+i+", "+j+")");
				if(Math.abs(i-positionI)==distance || Math.abs(j-positionJ)==distance) {
					//System.out.println("-- Checking position ("+i+", "+j+")");
					if(isOccupied (gridCopy, i,j)) {
						//System.out.println("---- Occupied");
						occupiedSeats++;
					}
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
		String testFilePath = "test-cases\\Day11_example.txt";
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
