package Project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quarto {

	private static final int BOARD_SIZE = 4;
	private Brick[][] board;
	private Brick[] BRICKS_PROTOTYPE =  {
			new Brick(Size.LARGE, Color.BLUE, Hole.HAS_HOLE, Shape.ROUND),
			new Brick(Size.LARGE, Color.BLUE, Hole.HAS_HOLE, Shape.SQUARE),
			new Brick(Size.LARGE, Color.BLUE, Hole.NO_HOLE, Shape.ROUND),
			new Brick(Size.LARGE, Color.BLUE, Hole.NO_HOLE, Shape.SQUARE),
			new Brick(Size.LARGE, Color.RED, Hole.HAS_HOLE, Shape.ROUND),
			new Brick(Size.LARGE, Color.RED, Hole.HAS_HOLE, Shape.SQUARE),
			new Brick(Size.LARGE, Color.RED, Hole.NO_HOLE, Shape.ROUND),
			new Brick(Size.LARGE, Color.RED, Hole.NO_HOLE, Shape.SQUARE),
			new Brick(Size.SMALL, Color.BLUE, Hole.HAS_HOLE, Shape.ROUND),
			new Brick(Size.SMALL, Color.BLUE, Hole.HAS_HOLE, Shape.SQUARE),
			new Brick(Size.SMALL, Color.BLUE, Hole.NO_HOLE, Shape.ROUND),
			new Brick(Size.SMALL, Color.BLUE, Hole.NO_HOLE, Shape.SQUARE),
			new Brick(Size.SMALL, Color.RED, Hole.HAS_HOLE, Shape.ROUND),
			new Brick(Size.SMALL, Color.RED, Hole.HAS_HOLE, Shape.SQUARE),
			new Brick(Size.SMALL, Color.RED, Hole.NO_HOLE, Shape.ROUND),
			new Brick(Size.SMALL, Color.RED, Hole.NO_HOLE, Shape.SQUARE)
	};
	private List<Brick> bricks = new ArrayList<Brick>(Arrays.asList(BRICKS_PROTOTYPE));
	
	public Quarto() {
		board = new Brick[BOARD_SIZE][BOARD_SIZE];
		this.setPiece(0, 0, 0);
		this.setPiece(0, 1, 0);
		this.setPiece(0, 2, 0);
		this.setPiece(0, 3, 0);
	}
	
	public String toString() {
		String out = "";
		out += "Availible bricks: " + bricks + "\n";
		out += "Board status:\n";
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(board[i][j] != null) {
					out += board[i][j] + "\t";
				} else {
					out += ".\t";
				}
			}
			out += "\n";
		}
		return out;
	}
	
	public void setPiece(int i, int j, int brickIndex) {
		Brick b = bricks.remove(brickIndex);
		board[i][j] = b;
	}
	
	public boolean isComplete() {
		return (hasCompleteColumn() || hasCompleteRow() || hasCompleteDiagonal());
	}
	
	private boolean hasCompleteRow() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (hasSameProperty(board[i]))
				return true;
		}
		return false;
	}
	
	private boolean hasCompleteColumn() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			Brick[] bricks = new Brick[BOARD_SIZE];
			for (int j = 0; j < BOARD_SIZE; j++) {
				bricks[j] = board[j][i];
			}
			if (hasSameProperty(bricks))
				return true;
		}
		return false;
	}
	
	private boolean hasCompleteDiagonal() {
		Brick[] diagonal1 = new Brick[BOARD_SIZE];
		Brick[] diagonal2 = new Brick[BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			diagonal1[i] = board[i][i];
			diagonal2[i] = board[i][BOARD_SIZE - 1 - i];
		}
		return (hasSameProperty(diagonal1) || hasSameProperty(diagonal2));
	}
	
	private boolean hasSameProperty(Brick[] bricks) {
		if (bricks[0] == null)
			return false;
		Size size = bricks[0].getSize();
		Hole hole = bricks[0].getHole();
		Shape shape = bricks[0].getShape();
		Color color = bricks[0].getColor();
		
		boolean sameSize = true;
		boolean sameHole = true;
		boolean sameShape = true;
		boolean sameColor = true;
		
		for (int i = 1; i < bricks.length; i++) {
			if (bricks[i] == null)
				return false;
			if (bricks[i] != null && size != bricks[i].getSize())
				sameSize = false;
			if (bricks[i] != null && hole != bricks[i].getHole())
				sameHole = false;
			if (bricks[i] != null && shape != bricks[i].getShape())
				sameShape = false;
			if (bricks[i] != null && color != bricks[i].getColor())
				sameColor = false;
		}
		return (sameSize || sameHole || sameShape || sameColor);
	}
	
    public static void main(String[] args) {
        Quarto game = new Quarto();
        System.out.println(game);
        
    }
}
