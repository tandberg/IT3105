package Project1;

import java.util.Random;

public class NovicePlayer extends Player {
	
	private Random random;
	
	public NovicePlayer(Quarto game) {
		super(game);
		this.random = new Random();
	}

	@Override
	public void placeBrick(int brickIndex) {
		Quarto tempGame = game.copy();
		
		for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
			for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
				if(tempGame.setPiece(i, j, brickIndex)) {
					if(tempGame.isComplete() == Quarto.WINNER) {
						game.setPiece(i, j, brickIndex);
						return;
					}
				}
				tempGame.removePiece(i, j, brickIndex);
			}
		}

		while(!game.setPiece(random.nextInt(Quarto.BOARD_SIZE), random.nextInt(Quarto.BOARD_SIZE), brickIndex));
		
	}

	@Override
	public int pickOpponentsBrick() {
		Quarto tempGame = game.copy();
		for (int brickIndex = 0; brickIndex < tempGame.getBricks().size(); brickIndex++) {
			boolean brickIsOk = true;
			for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
				for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
					if(tempGame.setPiece(i, j, brickIndex)) {
						if(tempGame.isComplete() != Quarto.NOT_FINISHED) {
							brickIsOk = false;
							//game.setPiece(i, j, brickIndex);
//							return brickIndex;
						}
					}
					tempGame.removePiece(i, j, brickIndex);
				}
			}
			if (brickIsOk) {
				return brickIndex;
			}
			brickIsOk = true;
		}
		return 0;
	}
	
	public String stringify() {
		return "NovicePlayer";
	}

}
