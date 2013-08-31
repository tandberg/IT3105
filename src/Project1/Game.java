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
			brickIndex = player1.pickOpponentsBrick(); // PLAYER 1 PICK BRICK
			
			int complete = quarto.isComplete();
			if(complete == Quarto.WINNER) {
				this.winner = player1;
				//System.out.println("Player1 wins");
				break;
			}
			else if (complete == Quarto.DRAW) {
				this.winner = null;
				//System.out.println("It's a draw!");
				break;
			}

			player2.placeBrick(brickIndex); // PLAYER 2 PLACE BRICK
			
			brickIndex = player2.pickOpponentsBrick(); // PLAYER 2 PICK BRICK
			complete = quarto.isComplete();
			
			if(complete == Quarto.WINNER) {
				//System.out.println("Player2 wins");
				this.winner = player2;
				break;
			}
			else if (complete == Quarto.DRAW) {
				this.winner = null;
				//System.out.println("It's a draw!");
				break;
			}
			
			player1.placeBrick(brickIndex); // PLAYER 1 PLACE BRICK
		}
	}
	
	public Player getWinner() {
		return winner;
	}

	/*public static void main(String[] args) {
		Game game = new Game();
	}*/
}
