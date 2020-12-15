package day10;

import java.util.ArrayList;

public class Node {
	private int nodeID;
	private ArrayList<Integer> connectsWith = new ArrayList<Integer>();
	
	public Node(int nodeID) {
		this.nodeID=nodeID;
	}
	
	public void connectNode(int toNodeID) {
		connectsWith.add(toNodeID);
	}
	
	public ArrayList<Integer> getConnectedNodes(){
		return this.connectsWith;
	}
	
	public int getNodeID() {
		return nodeID;
	}
}
