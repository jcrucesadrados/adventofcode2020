package day12;

import java.util.HashMap;

public class Ship {
	
	protected static HashMap<Character, Integer> cardinalsToDegrees = new HashMap<Character, Integer>();
	protected static HashMap<Integer, Character> degreesToCardinals = new HashMap<Integer, Character>();
	protected static char shipOrientedAt;
	protected static HashMap<Character, Integer> position = new HashMap<Character, Integer>();
	
	public Ship() {
		cardinalsToDegrees.put('N', 90);
		cardinalsToDegrees.put('S', 270);
		cardinalsToDegrees.put('E', 0);
		cardinalsToDegrees.put('W', 180);
		
		degreesToCardinals.put(90, 'N');
		degreesToCardinals.put(270, 'S');
		degreesToCardinals.put(0, 'E');
		degreesToCardinals.put(180, 'W');
		
		shipOrientedAt = 'E';
		
		position.put('N', 0);
		position.put('S', 0);
		position.put('E', 0);
		position.put('W', 0);

	}
	
	public Ship(char orientation) {
		cardinalsToDegrees.put('N', 90);
		cardinalsToDegrees.put('S', 270);
		cardinalsToDegrees.put('E', 0);
		cardinalsToDegrees.put('W', 180);
		
		degreesToCardinals.put(90, 'N');
		degreesToCardinals.put(270, 'S');
		degreesToCardinals.put(0, 'E');
		degreesToCardinals.put(180, 'W');
		
		shipOrientedAt = orientation;
		
		position.put('N', 0);
		position.put('S', 0);
		position.put('E', 0);
		position.put('W', 0);

	}
	
	protected static void advanceShip(char orientation, int units) {
		
		if(orientation=='F') {
			orientation = shipOrientedAt;
		}
		position.put(orientation, position.get(orientation)+units);

	}
	
	protected static void rotateShip(char orientation, int units) {
		
		//get the degrees of the current orientation
		int currentOrientationDegrees = cardinalsToDegrees.get(shipOrientedAt);
		
		if(orientation=='R') {
			currentOrientationDegrees-=units;
			if(currentOrientationDegrees<0) {
				currentOrientationDegrees+=360;
			}
		}
		else {
			if(orientation=='L') {
				currentOrientationDegrees+=units;
				if(currentOrientationDegrees>=360) {
					currentOrientationDegrees-=360;
				}
			}
		}
		
		// Update the ship orientation
		shipOrientedAt=degreesToCardinals.get(currentOrientationDegrees);
	}
	
	public void moveShip(String command) {
		char order = command.charAt(0);
		int units = Integer.parseInt(command.substring(1, command.length()));
		
		if(order=='R' || order=='L') {
			rotateShip(order, units);
		}
		else {
			advanceShip(order, units);
		}
	}
	
	public int getManhattanDistance() {
		int manhattanDistance = Math.abs(position.get('N')-position.get('S')) + Math.abs(position.get('E')-position.get('W'));
		
		return manhattanDistance;
	}
	
	public void printPosition() {
		System.out.println("-- Position: N: "+position.get('N')+"; S: "+position.get('S')+"; E: "+position.get('E')+"; W: "+position.get('W'));
	}
	
}
