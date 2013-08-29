package Project1;

import java.util.Collections;

public class Game {
	
	private Quarto quarto;
	private Player player1, player2;
	private Player winner;
	
	public Game() {
		quarto = new Quarto();
		player1 = new NovicePlayer(quarto);
		player2 = new RandomPlayer(quarto);
		
		try {
			startGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startGame() throws Exception {
		int brickIndex = -1;
		while(true) {
			//Thread.sleep(1000);
			System.out.println("Player1's turn");
			brickIndex = player1.pickOpponentsBrick();
			player2.placeBrick(brickIndex);
			//System.out.println(quarto);
			
			int complete = quarto.isComplete();
			
			if(complete == Quarto.WINNER) {
				this.winner = player2;
				System.out.println("Player2 wins");
				break;
			}
			else if (complete == Quarto.DRAW) {
				this.winner = null;
				System.out.println("It's a draw!");
				break;
			}
			
			System.out.println("Player2's turn");
			//Thread.sleep(1000);
			brickIndex = player2.pickOpponentsBrick();
			player1.placeBrick(brickIndex);
			//System.out.println(quarto);
			complete = quarto.isComplete();
			if(complete == Quarto.WINNER) {
				System.out.println("Player1 wins");
				this.winner = player1;
				break;
			}
			else if (complete == Quarto.DRAW) {
				this.winner = null;
				System.out.println("It's a draw!");
				break;
			}
			
		}
	}
	
	public Player getWinner() {
		return winner;
	}

	public static void main(String[] args) {
		Game game = new Game();
	}
}
