package day12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RainRiskChallenge1 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String testFilePath = "test-cases\\Day12:example.txt";
		Scanner scan = new Scanner(new FileReader(testFilePath));
		
		Ship ferry = new MegaShip();
		
		while(scan.hasNext()) {
			String command = scan.next();
			ferry.moveShip(command);
		}
		
		System.out.println("The Manhattan Distance is "+ferry.getManhattanDistance());
		
	}

}
