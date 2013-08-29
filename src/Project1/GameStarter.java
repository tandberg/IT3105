package Project1;

public class GameStarter {
	private final static int NUMBER_OF_GAMES = 100;
	private Statistics statistics;
	
	public GameStarter() {
		//System in to select players. Send into Game class constructor
		
		statistics = new Statistics("NovicePlayer", "RandomPlayer");
		
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			Game g = new Game();
			statistics.addGame(g);
			System.out.println("--------------------------------" + i);
		}
		
		System.out.println(statistics);
	}
	
	public static void main(String[] args) {
		new GameStarter();
	}
}
