package Project1;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
	
	List<Game> games;
	String player1, player2;

	public Statistics(String player1, String player2) {
		games = new ArrayList<Game>();
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
	
	public String toString() {
		int player1wins = 0, player2wins = 0, draw = 0;
		
		for (Game game : games) {
			if (game.getWinner() != null && game.getWinner().stringify().equals(this.player1)) {
				player1wins++;
			} else if (game.getWinner() != null && game.getWinner().stringify().equals(this.player2)) {
				player2wins++;
			} else {
				draw++;
			}
		}
		
		return "\nAfter " + games.size() + " games: \n" +
				player1 + ": " + player1wins + ", "+player2+": " + player2wins + ", Draws: " + draw + "\n";
	}
}
