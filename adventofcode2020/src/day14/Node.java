package day14;

import java.util.HashSet;

public class Node {

	private String data;
	private HashSet<Node> hashSetOfLinkedNodes = new HashSet<Node>();
	
	public Node(String data) {
		this.data=data;
	}
	
	public void addLinkedNode(Node linkedNode) {
		this.hashSetOfLinkedNodes.add(linkedNode);
	}
	
	public HashSet<Node> getLinkedNodes(){
		return this.hashSetOfLinkedNodes;
	}
	
	public String get() {
		return this.data;
	}
		
}
