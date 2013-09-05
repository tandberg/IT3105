package Project1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class MiniMaxPlayer extends Player {

	private int depth;
	private Random random;
	private final static int WAIT_MOVES = 8;
	private final static int MAX_BRICKS = 16;
    private Move goodMove, badMove;
	
	public MiniMaxPlayer(Quarto game, int depth) {
		super(game);
		this.depth = depth;
		this.random = new Random();
	}
	
	public boolean shouldPrune() {
		return this.game.getBricks().size() < MAX_BRICKS - WAIT_MOVES;
	}
	
	@Override
	public void placeBrick(int brickIndex) {
		if(shouldPrune()) {
			System.out.println("Alpha-Beta function call");

            int a = this.alphabeta(this.game, this.depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            System.out.println("ALPHABETA: " + a);
            
            game.setPiece(this.goodMove.row, this.goodMove.column, brickIndex);

		} else {
			randomPlaceBrick(brickIndex);
		}
	}
	
	private int alphabeta(Quarto gameNode, int depth, int alpha, int beta, boolean player) { // player = true -> you. Initial call with true.
		
		int gameState = gameNode.isComplete();
        if(depth == 0 || gameState != Quarto.NOT_FINISHED) {
            if(gameState == Quarto.WINNER && !player) {
                return 1;
            }
            else if (gameState == Quarto.WINNER && player) {
                return -1;
            }
            else if (gameState == Quarto.DRAW) {
                return 0;
            }
        }
		
		if(player) {
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
                Quarto child = gameNode.copy();
                
                for(Move move : getPossibleMoves(child)) {
                    child.setPiece(move.row, move.column, i);
                    int tempAlpha = alphabeta(child, depth - 1, alpha, beta, !player);
                    child.removePiece(move.row, move.column, i);

                    if(alpha < tempAlpha) {
                        alpha = tempAlpha;

                        if(depth == this.depth) {
                            this.setGoodMove(move);
                        }
                    }

                    if(beta <= alpha) {
                        break;
                    }
                }
			}
			return alpha;

		} else {
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
				Quarto child = gameNode.copy();
				
                for(Move move : getPossibleMoves(child)) {
                    child.setPiece(move.row, move.column, i);
                    int tempBeta = alphabeta(child, depth - 1, alpha, beta, !player);
                    child.removePiece(move.row, move.column, i);
                    
                    if(beta > tempBeta) {
                    	beta = tempBeta;
                    	
                    	if(depth == this.depth) {
                    		this.setBadMove(move);
                    	}
                    }
                    //beta = Math.min(beta, alphabeta(child, depth - 1, alpha, beta, !player));
                    
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

			Quarto tempGame = game.copy();
			for (int brickIndex = 0; brickIndex < tempGame.getBricks().size(); brickIndex++) {
				boolean brickIsOk = true;
				for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
					for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
						if(tempGame.setPiece(i, j, brickIndex)) {
							if(tempGame.isComplete() != Quarto.NOT_FINISHED) {
								brickIsOk = false;
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
    
    private void setBadMove(Move move) {
    	this.badMove = move;
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
