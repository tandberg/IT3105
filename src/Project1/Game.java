package project1;

public class Game {
	
	private Quarto quarto;
	private Player player1, player2;
	private Player winner;
	
	public Game(String playerName1, String playerName2) {
		quarto = new Quarto();
		
		player1 = selectPlayer(playerName1);
		player2 = selectPlayer(playerName2);

		try {
			startGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Player selectPlayer(String playername) {
		if(playername.equals("RandomPlayer")) {
			return new RandomPlayer(quarto);
		} else if(playername.equals("NovicePlayer")) {
			return new NovicePlayer(quarto);
		} else if(playername.equals("HumanPlayer")) {
			return new HumanPlayer(quarto);
		} else if(playername.equals("MiniMax-3-Player")) {
			return new MiniMaxPlayer(quarto, 3);
		} else if(playername.equals("MiniMax-4-Player")) {
			return new MiniMaxPlayer(quarto, 4);
		} else if(playername.equals("MiniMax-4-PlayerHT")) {
			return new MiniMaxPlayerHT(quarto, 4);
		} else if(playername.equals("MiniMax-4-PlayerST")) {
			return new MiniMaxPlayer(quarto, 4);
		}
		
		return null;
	}

	private void startGame() throws Exception {
		int brickIndex = -1;
		while(true) {
			int complete = quarto.isComplete();
			brickIndex = player1.pickOpponentsBrick(); // PLAYER 1 PICK BRICK			
			
			if(complete == Quarto.WINNER) {
				this.winner = player1;
				System.out.println("Player1 wins (" + player1.stringify() + ")");
				System.out.println(quarto);
				break;
			}
			else if (complete == Quarto.DRAW) {
				this.winner = null;
				break;
			}

			player2.placeBrick(brickIndex); // PLAYER 2 PLACE BRICK

			System.out.println(""+player2.stringify()+" Plasserte siste:\n");
            System.out.println(quarto);
			complete = quarto.isComplete();
			
			if(complete == Quarto.WINNER) {
				this.winner = player2;
				System.out.println(quarto);
				break;
			}
			else if (complete == Quarto.DRAW) {
				this.winner = null;
				break;
			}

			brickIndex = player2.pickOpponentsBrick(); // PLAYER 2 PICK BRICK
			player1.placeBrick(brickIndex); // PLAYER 1 PLACE BRICK

			System.out.println(""+player1.stringify()+" Plasserte siste:\n");
            System.out.println(quarto);
		}
	}
	
	public Player getWinner() {
		return winner;
	}
}
