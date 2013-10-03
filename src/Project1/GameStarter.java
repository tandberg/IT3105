package project1;

import java.util.Scanner;

public class GameStarter {
	private final static int NUMBER_OF_GAMES = 100;
	private Statistics statistics;
	
	public GameStarter(boolean tournament) {

		if(tournament) {

			String p1 = "MiniMax-4-PlayerST";
			String p2 = "MiniMax-4-PlayerHT";
			
			statistics = new Statistics(p1, p2);
			
			for (int i = 0; i < NUMBER_OF_GAMES; i++) {
				System.out.println("Game num: #" + (i+1));
				Game g = new Game(p1, p2);
				statistics.addGame(g);
			}

			System.out.println(statistics);
			System.exit(0);
			
		}
		
		String player1 = choosePlayer(true);

        String player2 = choosePlayer(false);
        if (player1 == null || player2 == null) {
            System.out.println("Please select valid players!");
            new GameStarter(false);
        }

        statistics = new Statistics(player1, player2);
		
		for (int i = 0; i < NUMBER_OF_GAMES; i++) {
			System.out.println("Game num: #" + (i+1));
			Game g = new Game(player1, player2);
			statistics.addGame(g);
		}
		
		System.out.println(statistics);
	}

    public String choosePlayer(boolean player) {
        String p = player ? "1" : "2";
        System.out.println("Please choose player " + p + ": ");
        System.out.println("HumanPlayer [0]");
        System.out.println("RandomPlayer [1]");
        System.out.println("NovicePlayer [2]");
        System.out.println("MiniMax-3-Player [3]");
        System.out.println("MiniMax-4-Player [4]");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 0:
                return "HumanPlayer";
            case 1:
                return "RandomPlayer";
            case 2:
                return "NovicePlayer";
            case 3:
                return "MiniMax-3-Player";
            case 4:
                return "MiniMax-4-Player";
            default:
                return null;
        }
    }
	
	public static void main(String[] args) {
		new GameStarter(true);
	}
}
