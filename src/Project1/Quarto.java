package Project1;

import java.util.Arrays;

public class Quarto {

	private static final int BOARD_SIZE = 4;
	private Brick[][] board;
	private Brick[] bricks =  {
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
	
	public Quarto() {
		board = new Brick[BOARD_SIZE][BOARD_SIZE];
		printBoard();
	}
	
	public void printBoard() {
		System.out.println("Availible bricks: " + Arrays.toString(bricks) + "\n");
		System.out.println("Board status:");
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(board[i][j] != null) {
					System.out.print(board[i][j] + "\t");
				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}
	
    public static void main(String[] args) {
        Quarto game = new Quarto();
    }
}
