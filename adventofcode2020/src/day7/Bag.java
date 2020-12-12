package day7;

import java.util.HashMap;

public class Bag {
	
	private String bagName;
	private HashMap <Bag, Integer> hashMapOfcontainedBags = new HashMap<Bag, Integer>();
	
	public Bag(String bagName) {
		this.bagName=bagName;
	}
	
	public void addContainedBag(Bag containedBag, int number) {
		hashMapOfcontainedBags.put(containedBag, number);
	}
	
	public boolean containsBag(String bagName) {
		if(this.bagName.equals(bagName)) {
			return false;
		}
		else {
			for(Bag bagContained : hashMapOfcontainedBags.keySet()) {
				if(bagContained.bagName.equals(bagName) || bagContained.containsBag(bagName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public HashMap <Bag, Integer> getContainedBags(){
		return hashMapOfcontainedBags;
	}
	
	public String getBagName() {
		return bagName;
	}

}
