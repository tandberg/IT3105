package Project1;

public class Game {
	
	private Quarto quarto;
	private Player player1, player2;
	
	public Game() {
		quarto = new Quarto();
		
		player1 = new HumanPlayer(quarto);
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

			System.out.println("Player1's turn");
			Thread.sleep(1000);
			brickIndex = player1.pickOpponentsBrick();
			player2.placeBrick(brickIndex);
			System.out.println(quarto);
			
			if(quarto.isComplete()) {
				System.out.println("Player2 wins");
				break;
			}
			
			System.out.println("Player1's turn");
			Thread.sleep(1000);
			brickIndex = player2.pickOpponentsBrick();
			player1.placeBrick(brickIndex);
			System.out.println(quarto);
			if(quarto.isComplete()) {
				System.out.println("Player1 wins");
				break;
			}
			
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
	}
}
