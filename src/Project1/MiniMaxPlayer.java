package Project1;

import java.util.*;

public class MiniMaxPlayer extends Player {

	private int depth;
	private Random random;
	private final static int WAIT_MOVES = 4;
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

            double a = this.alphabeta(this.game, this.depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            
            game.setPiece(this.goodMove.row, this.goodMove.column, brickIndex);

		} else {
			randomPlaceBrick(brickIndex);
		}
	}
	
	// player = true -> you. Initial call with true.
	private double alphabeta(Quarto gameNode, int depth, double alpha, double beta, boolean player) { 
		
		int gameState = gameNode.isComplete();
        if(depth == 0 || gameState != Quarto.NOT_FINISHED) {
            if(gameState == Quarto.WINNER && !player) {
                return Double.MAX_VALUE;
            }
            else if (gameState == Quarto.WINNER && player) {
                return Double.MIN_VALUE;
            }
            else if (gameState == Quarto.DRAW) {
                return 0;
            } 
            else if(gameState == Quarto.NOT_FINISHED) {
            	return heuristics(gameNode, player);
            }
        }
		
		if(player) {
			outerAlphaLoop:
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
                Quarto child = gameNode.copy();
                
                for(Move move : getPossibleMoves(child)) {
                    child.setPiece(move.row, move.column, i);
                    double tempAlpha = alphabeta(child, depth - 1, alpha, beta, !player);
                    child.removePiece(move.row, move.column, i);

                    if(alpha < tempAlpha) {
                        alpha = tempAlpha;

                        if(depth == this.depth) {
                            this.setGoodMove(move);
                        }
                    }

                    if(beta <= alpha) {
                        break outerAlphaLoop;
                    }
                }
			}
			return alpha;

		} else {
			outerBetaLoop:
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
				Quarto child = gameNode.copy();
				
                for(Move move : getPossibleMoves(child)) {
                    child.setPiece(move.row, move.column, i);
                    double tempBeta = alphabeta(child, depth - 1, alpha, beta, !player);
                    child.removePiece(move.row, move.column, i);
                    
                    if(beta > tempBeta) {
                    	beta = tempBeta;
                    	
                    	if(depth == this.depth) {
                    		this.setBadMove(move);
                    	}
                    }
                    
                    if(beta <= alpha) {
                        break outerBetaLoop;
                    }
                }
			}
			return beta;
		}
	}

	private double heuristics(Quarto gameNode, boolean player) {
        return StateEvaluator.evaluate(gameNode, player);
	}

	private void randomPlaceBrick(int brickIndex) {
		
		Quarto tempGame = game.copy();
		
		for (Move move : this.getPossibleMoves(tempGame)) { // Loops through all possible moves to check if there is a immediate winner move.
			tempGame.setPiece(move.row, move.column, brickIndex);
			if(tempGame.isComplete() == Quarto.WINNER) {
				game.setPiece(move.row, move.column, brickIndex);
				return;
			}
			tempGame.removePiece(move.row, move.column, brickIndex);
		}
		
		while(!game.setPiece(random.nextInt(Quarto.BOARD_SIZE), random.nextInt(Quarto.BOARD_SIZE), brickIndex));
	}

	@Override
	public int pickOpponentsBrick() {
		Quarto tempGame = game.copy();

        for (int brickIndex = 0; brickIndex < game.getBricks().size(); brickIndex++) {
				boolean brickIsOk = true;
				for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
					for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
						if(tempGame.setPiece(i, j, brickIndex)) {

                            if(tempGame.isComplete() == Quarto.WINNER) {

								brickIsOk = false;
							}
                            tempGame.removePiece(i, j, brickIndex);
						}

					}
				}
				if (brickIsOk) {
                    System.out.println("Index til picked brick: " + brickIndex);
					return brickIndex;
				}
			}
            int random = randomPickOpponentsBrick();
            System.out.println("Index til random picked brick: " + random);
			
			return randomPickOpponentsBrick();
	}

	private int randomPickOpponentsBrick() {
        if(game.getBricks().size() == 0) return 0;
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
