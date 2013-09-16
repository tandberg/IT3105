package Project1;

public class GameStarter {
	private final static int NUMBER_OF_GAMES = 1000;
	private Statistics statistics;
	
	public GameStarter() {

		String player1 = "MiniMax-3-Player";
		String player2 = "NovicePlayer";
		//String player2 = "RandomPlayer";
		
		statistics = new Statistics(player1, player2);
		
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			System.out.println("Game num: #" + (i+1));
			Game g = new Game(player1, player2);
			statistics.addGame(g);
		}
		
		System.out.println(statistics);
	}
	
	public static void main(String[] args) {
		new GameStarter();
	}
}
