package Project1;

public class NovicePlayer extends Player {
	
	public NovicePlayer(Quarto game) {
		super(game);
	}

	@Override
	public void placeBrick(int brickIndex) {
		Quarto tempGame = game.copy();
		
	}

	@Override
	public int pickOpponentsBrick() {
		// TODO Auto-generated method stub
		return 0;
	}

}
