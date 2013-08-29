package Project1;

import java.util.Random;

public class RandomPlayer extends Player {

	private Random random;
	
	public RandomPlayer(Quarto game) {
		super(game);
		this.random = new Random();
	}

	@Override
	public void placeBrick(int brickIndex) {
		while(!game.setPiece(random.nextInt(Quarto.BOARD_SIZE), random.nextInt(Quarto.BOARD_SIZE), brickIndex));
	}

	@Override
	public int pickOpponentsBrick() {
		return random.nextInt(game.getBricks().size());
	}
	
	public String stringify() {
		return "RandomPlayer";
	}
	
}
