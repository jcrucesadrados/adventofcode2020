package day10;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
/*
 * Important note: This script will never end because of time complexity 
 * As using recursive functions needs 2^n iterations we need to improve the time
 * For this, we use dynamic programming. Please see AdapterArrayChallenge2
 */
public class AdapterArrayChallenge2NoMemo {
	
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
		System.out.print("Starting fetch. We have "+hashMapOfNodes.size()+" nodes");
		long sum=advanceNode(hashMapOfNodes, 0);
		
		//System.out.println(arrayListOfadapters);
		System.out.println("There are "+sum+" possible options");
		
		
	}
	
	
	
	public static long advanceNode(TreeMap<Integer, Node> hashMapOfNodes, int nodeID) {
		//System.out.println("Checking node "+nodeID);
		Node thisNode = hashMapOfNodes.get(nodeID);
		ArrayList<Integer>nodeConections=thisNode.getConnectedNodes();
		long sum = 0;
		if(nodeConections.isEmpty()) {
			return 1;
		}
		
		for(int nextNode : nodeConections) {
			sum+=advanceNode(hashMapOfNodes, nextNode);
		}
		
		return sum;
		
	}

}
