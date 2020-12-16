package day12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RainRiskChallenge2 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String testFilePath = "test-cases\\Day12.txt";
		Scanner scan = new Scanner(new FileReader(testFilePath));
		
		MegaShip ferry = new MegaShip();
		
		// Set the position
		ferry.setWayPointValue('E', 10);
		ferry.setWayPointValue('N', 1);
		ferry.printPosition();
		ferry.printWayPoint();
		while(scan.hasNext()) {
			String command = scan.next();
			System.out.println(command);
			ferry.moveShip(command);
			ferry.printPosition();
			ferry.printWayPoint();
		}
		
		System.out.println("The Manhattan Distance is "+ferry.getManhattanDistance());
		
	}

}
