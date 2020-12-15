package day10;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class AdapterArrayChallenge2 {
	
	/* This is my final approach for the second part of Adapter Array
	 * You can see the first approach at AdapterArrayChallenge2NoMemo.
	 * As I use recursive functions, time complexity becomes 2^n and the first approach never ends
	 * In order to solve this situation and improve the time complexity we need to use Dynamic Programming, in this
	 * case Momoization. That is, we are going to store in a HashMap the count from a given node through the end
	 * and in this way avoid to repeat the process, getting a O(1) situation  
	 * 
	 */
	
	// Memoization: Create a HashMap to store the counts
	public static HashMap<Integer, Long> memoizationHashMap = new HashMap<Integer, Long>();
	
	// Memoization performance: Create a counter to get the number of times that we used the Momoization process
	public static long countOfMemoizationUses = 0;
	
	public static void main(String[] args) throws IOException {
		// Read the input
		String testFilePath = "test-cases\\Day10.txt";
		Scanner scanner = new Scanner(new FileReader(testFilePath)); 
		ArrayList<Integer> arrayListOfadapters = new ArrayList<Integer>();
		TreeMap<Integer, Node> hashMapOfNodes = new TreeMap<Integer,Node>();
		// Start with 0
		arrayListOfadapters.add(0);
		hashMapOfNodes.put(0, new Node(0));
		
		while(scanner.hasNext()) {
			int line = scanner.nextInt();		
			arrayListOfadapters.add(line);
			hashMapOfNodes.put(line, new Node(line));
		}
		
		Collections.sort(arrayListOfadapters);
		// Add the last adapter is 3 jolts upper the last adapter
		arrayListOfadapters.add(arrayListOfadapters.get(arrayListOfadapters.size()-1)+3);
		hashMapOfNodes.put(arrayListOfadapters.get(arrayListOfadapters.size()-1), new Node(arrayListOfadapters.get(arrayListOfadapters.size()-1)));
		
		
		// Create a tree with all the relations
		//System.out.println(hashMapOfNodes.get(2).getNodeID());
		for(int i=0; i< arrayListOfadapters.size()-1; i++) {
			for(int j=1; j<=3; j++) {
				if(i+j<arrayListOfadapters.size()) {
					int toNode = i+j;
					if(arrayListOfadapters.get(toNode)-arrayListOfadapters.get(i)<=3) {
						//System.out.println("Connect Node "+i+" ("+arrayListOfadapters.get(i)+") with node "+toNode+" ("+arrayListOfadapters.get(toNode)+")");
						hashMapOfNodes.get(arrayListOfadapters.get(i)).connectNode(arrayListOfadapters.get(i+j));
					}
					else {
						break;
					}
				}
				else {
					break;
				}
			}
		}
		
		//Fetch the tree
		System.out.println("Starting fetch. We have "+hashMapOfNodes.size()+" nodes");
		long sum=advanceNode(hashMapOfNodes, 0);
		
		//System.out.println(arrayListOfadapters);
		System.out.println("There are "+sum+" possible options");
		
		// Memoization Performance
		System.out.println("Memoization shorts have been used "+countOfMemoizationUses+" times");
		// double percent = countOfMemoizationUses/hashMapOfNodes.size();
		double percent = ((double)64/102)*100;
		System.out.println("That represents a "+percent+"% of the computing process");
		
		
	}
	
	
	
	public static long advanceNode(TreeMap<Integer, Node> hashMapOfNodes, int nodeID) {
		//System.out.println("Checking node "+nodeID);
		Node thisNode = hashMapOfNodes.get(nodeID);
		ArrayList<Integer>nodeConections=thisNode.getConnectedNodes();
		long sum = 0;
		if(nodeConections.isEmpty()) {
			return 1;
		}
		
		// Memoization: Check if we have fetched the node realtions before
		if(memoizationHashMap.containsKey(nodeID)) {
			// Memoization Performance: Just increment the counter for future evaluations
			countOfMemoizationUses++;
			return memoizationHashMap.get(nodeID);
		}
		
		for(int nextNode : nodeConections) {
			sum+=advanceNode(hashMapOfNodes, nextNode);
		}
		
		// Memoization: Store the result in order to avoid repeating
		memoizationHashMap.put(nodeID, sum);
		return sum;
		
	}

}
