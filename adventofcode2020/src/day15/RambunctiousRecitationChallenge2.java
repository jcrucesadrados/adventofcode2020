package day15;


public class RambunctiousRecitationChallenge2 {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long[] input = new long[] {2,1,10,11,0,6};
		long stepsToGetTheDinnerReady = 30000000;
		
		RambunctiousRecitation game = new RambunctiousRecitation(input);
		
		while(game.getTurn()<stepsToGetTheDinnerReady) {
		
			game.playStep();
		
		}
		
		System.out.println("Number of steps played: "+game.getTurn());
		System.out.println("Last number spoken: "+game.getLastSpokenNumber());
		
	}

}
