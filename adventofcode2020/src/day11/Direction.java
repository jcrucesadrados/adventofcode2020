package day11;

public enum Direction {
	 UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), UP_RIGHT(-1, 1), UP_LEFT(-1, -1), DOWN_RIGHT(1, 1),
     DOWN_LEFT(1, -1);

     final int i;
     final int j;

     Direction(int i, int j) {
         this.i = i;
         this.j = j;
     }
 }