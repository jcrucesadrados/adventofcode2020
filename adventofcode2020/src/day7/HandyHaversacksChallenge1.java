package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class HandyHaversacksChallenge1 {

	private static int getNumberOfBagsContainers(HashMap<String, Bag> hashMapOfexistingBags, String searchedBag) {
		int countOfBags = 0;
		for(String nameOfBag : hashMapOfexistingBags.keySet()) {
			if(hashMapOfexistingBags.get(nameOfBag).containsBag(searchedBag)) {
				countOfBags++;
			}
		}
		return countOfBags;
	}
	
	public static void main (String[] args) throws IOException {
		List<String> example = Files.readAllLines(Path.of("test-cases\\Day7.txt"));
		HashMap<String, Bag> hashMapOfexistingBags = new HashMap<String, Bag>();
		
		// Start reading the document, saved as a list
		
		for(String line : example) {
			/* Let parse the content. An example is: 
			 * light red bags contain 1 bright white bag, 2 muted yellow bags.
			 * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
			 * 
			 * So we can group them as:
			 * 
			 * dark orange ----- bags contain ----3 bright white bags, 4 muted yellow bags.
			 * 
			 */
			String[] mainAndContainedStringArray = line.split(" bags contain "); // [0] --> The bag ; [1] Contained bags
			String mainColorBag = mainAndContainedStringArray[0];
			
			// If the bag isn't contained on hashMapOfexistingBags
			if(!hashMapOfexistingBags.containsKey(mainColorBag)) {
				// Add to the list
				hashMapOfexistingBags.put(mainColorBag, new Bag(mainColorBag));
			}
			
			if(mainAndContainedStringArray.length>1) {
				String[] containedBagsStringArray = mainAndContainedStringArray[1].split(", "); // Bags contained are separated by ,
				// Check the contained ones
				for(String containedBagString : containedBagsStringArray) {
					// Time to get the color and the number of bags. There is a special case: Where no more bags presents
					if(!containedBagString.equals("no other bags.")){
						//Then we need to add a contained bag
						String[] elementsOfContainedBag = containedBagString.split(" ");
						// Get the information in a clear way (not space optimized but better for understand 
						String containedBagName = elementsOfContainedBag[1]+" "+elementsOfContainedBag[2];
						int numerOfContainedBags = Integer.parseInt(elementsOfContainedBag[0]);
						
						if(!hashMapOfexistingBags.containsKey(containedBagName)) {
							hashMapOfexistingBags.put(containedBagName, new Bag(containedBagName));
						}
						
						hashMapOfexistingBags.get(mainColorBag).addContainedBag(hashMapOfexistingBags.get(containedBagName), numerOfContainedBags);
						
					}
					
				}
			}

		}
		
		//Once we have our graph, lets process the excercise
		int count = getNumberOfBagsContainers(hashMapOfexistingBags, "shiny gold");
		System.out.println("Found "+count);
	}
	
}
