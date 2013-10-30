package project1;

public abstract class Player {
	protected Quarto game;
	
	public Player(Quarto game) {
		this.game = game;
	}

	public abstract void placeBrick(int brickIndex);
	public abstract int pickOpponentsBrick();
	public abstract String stringify();
}
