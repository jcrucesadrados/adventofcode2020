package day13;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* For this case is necessary to apply the Chinese Reminder Theorem
 * https://www.youtube.com/watch?v=zIFehsBHB8o
 */

public class ShuttleSearchChallenge2 {
	
	public static long chineseRemindAlgorithm(String stringOfBusIDs) {
		// This theorem applies modular-algebra. Let implement it according with the video
		
		// 1) Lets convert the data input into an array
		String[] listOfStringsOfBusIDs = stringOfBusIDs.split(","); // Be carefoul, remember we have the x so it must be String
	
		
		// 2) We are going to call to previous launch of the buses, that is, busID - timeOffset, and we are going to store them in an arrayList
		// We are going to store too values as integers without the x, as we do not need more the x positions
		int timeOffset = 0;
		List<Integer> listOfBusesIDs = new ArrayList<Integer>();
		ArrayList<Integer> bi = new ArrayList<Integer>();
		for(String busID : listOfStringsOfBusIDs) {
			if(!busID.equals("x")) {
				bi.add(Integer.parseInt(busID)-timeOffset);
				listOfBusesIDs.add(Integer.parseInt(busID));
			}
			// Always increment the offset
			timeOffset++;
		}
		

		// 3) Calculate N as the product of all the elements of the list
		long N = 1;
		for(int busID : listOfBusesIDs) {
			N*=busID;	
		}
		
		// 4) Calculate Ni as N/ni where ni is the BusID
		
		ArrayList<Long> Ni = new ArrayList<Long>();
		for(int busID : listOfBusesIDs) {
			Ni.add(N/busID);
		}
		
		// 5) Calculate xi --> xi = bi (mod ni)
		ArrayList<Long> Xi = new ArrayList<Long>();		

		for(int i=0; i<listOfBusesIDs.size(); i++) {
			int busID = listOfBusesIDs.get(i); // (mod a) => busID
			long coefficient = Ni.get(i) % busID;
			System.out.println("busID: "+busID + " coeficient "+coefficient+" Ni "+Ni.get(i));
			// Now we have the coefficient but need to reduce it. For that we use the factor(xi) and try to iterate
			Long xi = (long) 1;
			while((coefficient * xi)%busID != 1) {
				xi++;
				//System.out.println(xi);
			}
			Xi.add(xi);
		}
		
		// 6) Calculate the product of bi*ni*xi and store it. Get the sum of all of them
		
		List<Long> product = new ArrayList<Long>();
		long sumOfAll = 0;
		for(int i=0; i<bi.size(); i++) {
			long productElements = bi.get(i)*Ni.get(i)*Xi.get(i);
			product.add(productElements);
			sumOfAll+=productElements;
		}
		
		// 7) Return the value
		
		return sumOfAll%N;
		
	}

	public static void main (String[] args) throws FileNotFoundException {
		String testFilePath = "test-cases\\Day13.txt";
		Scanner scan = new Scanner(new FileReader(testFilePath));
		
		// The arriving time is the first line
		String buses = scan.next();
		buses = scan.next();

		Long chineseResult = chineseRemindAlgorithm(buses);
		
		System.out.println("Te result is "+chineseResult);
		
	}
}
