package Project1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class MiniMaxPlayer extends Player {

	private int depth;
	private Random random;
	private final static int WAIT_MOVES = 8;
	private final static int MAX_BRICKS = 16;
    private Move goodMove;
	
	public MiniMaxPlayer(Quarto game, int depth) {
		super(game);
		this.depth = depth;
		this.random = new Random();
	}
	
	public boolean shouldPrune() {
		return this.game.getBricks().size() > MAX_BRICKS - WAIT_MOVES;
	}
	
	@Override
	public void placeBrick(int brickIndex) {
		if(shouldPrune()) {
			// do minimax alpha beta pruning to chose placement
			// prunePlaceBrick(brickIndex);
			// ?

            this.alphabeta(this.game, this.depth, -1, 1, true);
            game.setPiece(this.goodMove.row, this.goodMove.column, brickIndex);

		} else {
			randomPlaceBrick(brickIndex);
		}
	}
	
	private int alphabeta(Quarto gameNode, int depth, int alpha, int beta, boolean player) { // player = true -> you. Initial call with true.
        if(depth == 0) {
            if(gameNode.isComplete() == Quarto.WINNER && !player) {
                return 1;
            }
            else if (gameNode.isComplete() == Quarto.WINNER && player) {
                return -1;
            }
            else if (gameNode.isComplete() == Quarto.DRAW) {
                return 0;
            }
        }
		
		if(player) {
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
                Quarto child = gameNode.copy();
                for(Move move : getPossibleMoves(child)) {
                    child.setPiece(move.row, move.column, i);

                    int tempAlpha = alphabeta(child, depth - 1, alpha, beta, !player);
                    if(alpha < tempAlpha) {
                        alpha = tempAlpha;

                        if(depth == this.depth) {
                            this.setGoodMove(move);
                        }
                    }

                    alpha = Math.max(alpha, alphabeta(child, depth - 1, alpha, beta, !player));
                    if(beta <= alpha) {
                        break;
                    }
                }
			}
			return alpha; // Should be a move? dunno

		} else {
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
				Quarto child = gameNode.copy();
                for(Move move : getPossibleMoves(child)) {
                    child.setPiece(move.row, move.column, i); 		// Same as above.

                    beta = Math.min(beta, alphabeta(child, depth - 1, alpha, beta, !player));
                    if(beta <= alpha) {
                        break;
                    }
                }

			}
			return beta;
		}
		
		//TODO: Make more meaningful Winner/draw/not_finished to fit into alphabeta
	}

	private void randomPlaceBrick(int brickIndex) {
		while(!game.setPiece(random.nextInt(Quarto.BOARD_SIZE), random.nextInt(Quarto.BOARD_SIZE), brickIndex));
	}

	@Override
	public int pickOpponentsBrick() {
		if(shouldPrune()) {
			// do minimax alpha beta pruning to chose brick
			return 0;
		} else {
			return randomPickOpponentsBrick();
		}
	}

	private int randomPickOpponentsBrick() {
		return random.nextInt(game.getBricks().size());
	}

	@Override
	public String stringify() {
		return "MiniMax-"+this.depth+"-Player";
	}

    private void setGoodMove(Move move) {
        this.goodMove = move;
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

}
