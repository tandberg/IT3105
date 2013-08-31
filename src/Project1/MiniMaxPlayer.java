package Project1;

public class MiniMaxPlayer extends Player {

	private int depth;
	
	public MiniMaxPlayer(Quarto game, int depth) {
		super(game);
		this.depth = depth;
	}
	
	@Override
	public void placeBrick(int brickIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public int pickOpponentsBrick() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String stringify() {
		return "MiniMax-"+this.depth+"-Player";
	}

}
