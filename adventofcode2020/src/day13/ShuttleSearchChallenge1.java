package day13;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ShuttleSearchChallenge1 {

	public static void main (String[] args) throws FileNotFoundException {
		String testFilePath = "test-cases\\Day13.txt";
		Scanner scan = new Scanner(new FileReader(testFilePath));
		
		// The arriving time is the first line
		long estimatedDepartTime = scan.nextLong();
		String buses = scan.next();
		long selectedBusID = 0;
		long departureOfSelectedBus= Integer.MAX_VALUE;
		
		System.out.println(estimatedDepartTime);
		System.out.println(buses);
		
		String[] busesArray = buses.split(",");
		
		for(String thisBus : busesArray) {
			if(!thisBus.equals("x")) {
				long busID = Integer.parseInt(thisBus);
				long coeficientLastTimeArrived = estimatedDepartTime/busID;
				long lastTimeArrived = coeficientLastTimeArrived*busID;
				System.out.print("Bus "+busID+" arrived at "+lastTimeArrived+" for the last time. ");
				long nextArrival = lastTimeArrived+busID;
				System.out.println("The next arrival will be at "+nextArrival);
				if(nextArrival<departureOfSelectedBus) {
					selectedBusID=busID;
					departureOfSelectedBus=nextArrival;
				}
			}
		}
		
		long waitingTime = departureOfSelectedBus-estimatedDepartTime;
		System.out.println("You can get the bus "+selectedBusID+" at "+departureOfSelectedBus);
		System.out.println("You will need to wait for "+waitingTime+" minutes. Your final transport ID is "+(waitingTime*selectedBusID));
	}
}
