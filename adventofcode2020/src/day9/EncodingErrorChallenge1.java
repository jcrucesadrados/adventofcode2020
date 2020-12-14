package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EncodingErrorChallenge1 {

	public static void main(String[] args) throws IOException {
		// Read the input
		int windowsSize = 25;
		List<String> example = Files.readAllLines(Path.of("test-cases\\Day9.txt"));
		Queue<Long> slideWindow = new LinkedList<Long>();
		Queue<HashSet<Long>> queueOfsumOfElemens = new LinkedList<HashSet<Long>>();
		for (int i=0; i<example.size(); i++) {
			// parse to integer
			Long line = Long.parseLong(example.get(i));
			if(i<windowsSize) {
				Iterator<Long> slideWindowIterator = slideWindow.iterator();
				Iterator<HashSet<Long>> QueueOfsumOfElemensIterator = queueOfsumOfElemens.iterator();
				for(int j=0; j<slideWindow.size(); j++) {
					long slideWindowElement = slideWindowIterator.next();
					HashSet<Long>HashSetOfSumElements = QueueOfsumOfElemensIterator.next();
					HashSetOfSumElements.add(line+slideWindowElement);
				}
				slideWindow.add(line);
				queueOfsumOfElemens.add(new HashSet<Long>());
			}
			else {
				Iterator<HashSet<Long>> queueOfsumOfElemensIteratorForRev = queueOfsumOfElemens.iterator();
				boolean sumContained = false;
				while(queueOfsumOfElemensIteratorForRev.hasNext()) {
					HashSet<Long> hashMapOfsumOfElement = queueOfsumOfElemensIteratorForRev.next();
					if(hashMapOfsumOfElement.contains(line)) {
						sumContained=true;
					}
				}
				
				if(!sumContained) {
					System.out.println("The number "+line+" doesn't follows the rules");
					System.out.println("Found at line "+i);
					break;
				}
				else {
					// Poll the head
					slideWindow.poll();
					queueOfsumOfElemens.poll();
					
					Iterator<Long> slideWindowIterator = slideWindow.iterator();
					Iterator<HashSet<Long>> QueueOfsumOfElemensIterator = queueOfsumOfElemens.iterator();
					for(int j=0; j<slideWindow.size(); j++) {
						long slideWindowElement = slideWindowIterator.next();
						HashSet<Long>HashSetOfSumElements = QueueOfsumOfElemensIterator.next();
						HashSetOfSumElements.add(line+slideWindowElement);
					}
					slideWindow.add(line);
					queueOfsumOfElemens.add(new HashSet<Long>());
				}
	
			}
		}
		
	}

}
