package day12;

import java.util.HashMap;

public class MegaShip extends Ship {
	protected static HashMap<Character, Integer> wayPoint = new HashMap<Character, Integer>();
	
	public MegaShip() {
		
		// Starts the Ship parameters
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
		
		// A MegaShip is a Ship with a waypoint
		
		wayPoint.put('N', 0);
		wayPoint.put('S', 0);
		wayPoint.put('E', 0);
		wayPoint.put('W', 0);

	}
	
	public MegaShip(HashMap<Character, Integer> initialWayPoint) {
		cardinalsToDegrees.put('N', 90);
		cardinalsToDegrees.put('S', 270);
		cardinalsToDegrees.put('E', 0);
		cardinalsToDegrees.put('W', 180);
		
		degreesToCardinals.put(90, 'N');
		degreesToCardinals.put(270, 'S');
		degreesToCardinals.put(0, 'E');
		degreesToCardinals.put(180, 'W');
		
		position.put('N', 0);
		position.put('S', 0);
		position.put('E', 0);
		position.put('W', 0);
		
		shipOrientedAt = 'E';
		
		wayPoint=initialWayPoint;

	}
	
	protected void megashipForward(int units) {
		for(char coordinate : wayPoint.keySet()) {
			//System.out.println(wayPoint);
			position.put(coordinate, (wayPoint.get(coordinate)*units)+position.get(coordinate));
		}
	}
	
	protected void moveMegaShipWayPoint(char order, int units) {
		
		if(order=='R' || order=='L') {
			rotateMegaShipWayPoint(order, units);
		}
		else {
			advanceMegaShipWayPoint(order, units);
		}
	}
	
	protected static void advanceMegaShipWayPoint(char orientation, int units) {
		
		wayPoint.put(orientation, wayPoint.get(orientation)+units);

	}
	protected static void rotateMegaShipWayPoint(char orientation, int units) {
		
		HashMap<Character, Integer> wayPointCopy = copyHashMap(wayPoint);
		for(char thisCoordinate : wayPointCopy.keySet()) {
			char newCoordinate = getRotatedCoordinate(thisCoordinate, orientation, units);
			wayPoint.put(newCoordinate, wayPointCopy.get(thisCoordinate));
		}
		
	}
	protected static HashMap<Character, Integer> copyHashMap(HashMap<Character, Integer> originalHashMap){
		HashMap<Character, Integer> newHashMap = new HashMap<Character, Integer>();
		
		for(char key : originalHashMap.keySet()) {
			newHashMap.put(key, originalHashMap.get(key));
		}
		
		return newHashMap;
		
	}
	protected static char getRotatedCoordinate(char originalCoodinate, char rotationWay, int rotationValue) {
		//get the degrees of the current orientation
		int currentOrientationDegrees = cardinalsToDegrees.get(originalCoodinate);
		
		if(rotationWay=='R') {
			currentOrientationDegrees-=rotationValue;
			if(currentOrientationDegrees<0) {
				currentOrientationDegrees+=360;
			}
		}
		else {
			if(rotationWay=='L') {
				currentOrientationDegrees+=rotationValue;
				if(currentOrientationDegrees>=360) {
					currentOrientationDegrees-=360;
				}
			}
		}
		
		// Update the ship orientation
		return degreesToCardinals.get(currentOrientationDegrees);
	}
	
	public void moveShip(String command) {
		// Now the MegaShip's movement is according to the waypoint and only moves with F
		// Otherwise its going to move the waypoint without changing the position
		
		char order = command.charAt(0);
		int units = Integer.parseInt(command.substring(1, command.length()));
		
		if(order=='F') {
			megashipForward(units);
		}
		else {
			moveMegaShipWayPoint(order, units);
		}
	}
	
	public void setWayPointValue(char wayPointCoordinate, int value) {
		wayPoint.put(wayPointCoordinate, value);
	}
	
	public void printWayPoint() {
		System.out.println("-- Waypoint: N: "+wayPoint.get('N')+"; S: "+wayPoint.get('S')+"; E: "+wayPoint.get('E')+"; W: "+wayPoint.get('W'));
	}

}
