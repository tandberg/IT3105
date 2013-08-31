package Project1;

import java.util.Random;

public class MiniMaxPlayer extends Player {

	private int depth;
	private Random random;
	private final static int WAIT_MOVES = 8;
	private final static int MAX_BRICKS = 16;
	
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
			// prunePlaceBrick(brickIndex); ?
		} else {
			randomPlaceBrick(brickIndex);
		}
	}
	
	private int alphabeta(Quarto gameNode, int depth, int alpha, int beta, boolean player) { // player = true -> you. Initial call with true.
		if(depth == 0 || gameNode.isComplete() == Quarto.NOT_FINISHED) {
			return gameNode.isComplete();
		}
		
		if(player) {
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
				Quarto child = gameNode.copy();
				child.setPiece(0, 0, 0);		// Something smart to determine which place and piece.
				
				alpha = Math.max(alpha, alphabeta(child, depth - 1, alpha, beta, !player));
				if(beta <= alpha) {
					break;
				}
			}
			return alpha; // Should be a move? dunno
		} else {
			for (int i = 0; i < gameNode.getBricks().size(); i++) {
				Quarto child = gameNode.copy();
				child.setPiece(0, 0, 0); 		// Same as above.
				
				beta = Math.min(beta, alphabeta(child, depth - 1, alpha, beta, !player));
				if(beta <= alpha) {
					break;
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

}
