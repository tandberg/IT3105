package Project1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

//import Project1.MiniMaxPlayer.Move;

public class NovicePlayer extends Player {
	
	private Random random;
	
	public NovicePlayer(Quarto game) {
		super(game);
		this.random = new Random();
	}

	@Override
	public void placeBrick(int brickIndex) {
		Quarto tempGame = game.copy();
		
		for (Move move : this.getPossibleMoves(tempGame)) { // Loops through all possible moves to check if there is a immediate winner move.
			tempGame.setPiece(move.row, move.column, brickIndex);
			if(tempGame.isComplete() == Quarto.WINNER) {
				game.setPiece(move.row, move.column, brickIndex);
				return;
			}
			tempGame.removePiece(move.row, move.column, brickIndex);
		}
		
		// Else just place it randomly
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
						}
                        tempGame.removePiece(i, j, brickIndex);
					}

				}
			}
			if (brickIsOk) {
				return brickIndex;
			}
			brickIsOk = true;
		}

		return 0;
	}
	
	private Collection<Move> getPossibleMoves(Quarto game) {
        Brick[][] board = game.getBoard();
        Collection<Move> possibleMoves = new HashSet<Move>();
        for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
            for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
                if (board[i][j] == null) {
                    possibleMoves.add(new Move(i, j));
                }
            }
        }
        return possibleMoves;
    }
	
	private class Move {
        int row, column;

        public Move(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
	
	public String stringify() {
		return "NovicePlayer";
	}

}
