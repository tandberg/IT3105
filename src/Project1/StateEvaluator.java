package Project1;

import java.util.ArrayList;
import java.util.List;

public class StateEvaluator {

	
	
	public static double evaluatez(Quarto gameNode, boolean player) {
		
		double heurestics = 0.0;
		
		heurestics += getNumberOfValidTriplets(gameNode) * 100;
		heurestics += getPositionValues(gameNode) * 10;
		
		return player ? heurestics * -1 : heurestics;
		
	}
	
	public static int getNumberOfValidTriplets(Quarto gameNode) {
		List<Brick[]> lines = getAllLines(gameNode);
		
		for (Brick[] line : lines) {
			if(validTripletLine(line, gameNode)) {
				// +1
			}
		}
		
			
		return 0;
	}
	
	private static boolean validTripletLine(Brick[] line, Quarto gameNode) {
		
		int placed = 0;
		for (int i = 0; i < line.length; i++) {
			if(line[i] != null) {
				placed++;
			}
		}
		
		if(placed != Quarto.BOARD_SIZE - 1) {
			return false;
		}
		
		return possibleLine(line, gameNode);
	}
	
	
	public static boolean possibleLine(Brick[] bricks, Quarto gameNode) {
		if (bricks[0] == null && bricks[1] == null)
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
		
		if(sameColor) {
			return hasSameColorLeft(color, gameNode);
		}
		if(sameHole) {
			return hasSameHoleLeft(hole, gameNode);
		}
		if(sameShape) {
			return hasSameShapeLeft(shape, gameNode);
		}
		if(sameSize) {
			return hasSameSizeLeft(size, gameNode);
		}
		return false;
	}

	private static boolean hasSameSizeLeft(Size size, Quarto gameNode) {
		for (Brick brick : gameNode.getBricks()) {
			if(brick.getSize() == size)
				return true;
		}
		return false;
	}

	private static boolean hasSameShapeLeft(Shape shape, Quarto gameNode) {
		for (Brick brick : gameNode.getBricks()) {
			if(brick.getShape() == shape)
				return true;
		}
		return false;
	}

	private static boolean hasSameHoleLeft(Hole hole, Quarto gameNode) {
		for (Brick brick : gameNode.getBricks()) {
			if(brick.getHole() == hole)
				return true;
		}
		return false;
	}

	private static boolean hasSameColorLeft(Color color, Quarto gameNode) {
		for (Brick brick : gameNode.getBricks()) {
			if(brick.getColor() == color)
				return true;
		}
		
		return false;
	}

	public static List<Brick[]> getAllLines(Quarto gameNode) {
		List<Brick[]> lines = new ArrayList<Brick[]>();
		
		for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
			lines.add(gameNode.getBoard()[i]);
		}
		
		for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
			Brick[] line = new Brick[Quarto.BOARD_SIZE];
			
			line[0] = gameNode.getBoard()[0][i];
			line[1] = gameNode.getBoard()[1][i];
			line[2] = gameNode.getBoard()[2][i];
			line[3] = gameNode.getBoard()[3][i];
		}
		

		Brick[] diagonal = new Brick[Quarto.BOARD_SIZE];
		Brick[] codiagonal = new Brick[Quarto.BOARD_SIZE];

		diagonal[0] = gameNode.getBoard()[0][0];
		diagonal[1] = gameNode.getBoard()[1][1];
		diagonal[2] = gameNode.getBoard()[2][2];
		diagonal[3] = gameNode.getBoard()[3][3];
		
		codiagonal[0] = gameNode.getBoard()[3][0];
		codiagonal[1] = gameNode.getBoard()[2][1];
		codiagonal[2] = gameNode.getBoard()[1][2];
		codiagonal[3] = gameNode.getBoard()[0][3];
		
		lines.add(diagonal);
		lines.add(codiagonal);	
		
		return lines;
	}
	
	
	
	
	
	
	
	
	
	
	
	
    public static double evaluate(Quarto gameNode, boolean player) {
        Brick[][] board = gameNode.getBoard();
        List<Brick[]> possibleRow = new ArrayList<Brick[]>();

        Brick[] diagonal1 = new Brick[Quarto.BOARD_SIZE];
        Brick[] diagonal2 = new Brick[Quarto.BOARD_SIZE];
        for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
            diagonal1[i] = board[i][i];
            diagonal2[i] = board[i][Quarto.BOARD_SIZE - 1 - i];

            Brick[] col = new Brick[Quarto.BOARD_SIZE];
            Brick[] row = new Brick[Quarto.BOARD_SIZE];
            for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
                col[j] = board[j][i];
                row[j] = board[i][j];
            }
            possibleRow.add(col);
            possibleRow.add(row);
        }
        possibleRow.add(diagonal1);
        possibleRow.add(diagonal2);


        double heuri = 0.0;

        heuri += calculate(possibleRow);
        heuri += getPositionValues(gameNode);


        // System.out.println("heuri: "+ heuri);

        return player ? heuri : heuri * -1;
    }


    public static int countSameProperty(Brick[] bricks) {
        int sizeTrue = 0;
        int holeTrue = 0;
        int shapeTrue = 0;
        int colorTrue = 0;

        for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
            Brick brick = bricks[i];
            if (bricks[i] == null)
                continue;
            if (brick.getColor() == Color.BLUE)
                colorTrue++;
            else
                colorTrue--;
            if (brick.getShape() == Shape.ROUND)
                shapeTrue++;
            else
                shapeTrue--;
            if (brick.getHole() == Hole.HAS_HOLE)
                holeTrue++;
            else
                holeTrue--;
            if (brick.getSize() == Size.LARGE)
                sizeTrue++;
            else
                sizeTrue--;
        }
        return sizeTrue + holeTrue + shapeTrue + colorTrue;
    }

    private static double calculate(List<Brick[]> possibleRows) {

        double count = 0;
        for (Brick[] row : possibleRows) {
            count += countSameProperty(row) / (Quarto.BOARD_SIZE * Quarto.BOARD_SIZE);
        }
        return count;
    }

    private static double getPositionValues(Quarto gameNode) {
        int[][] positionValues = { 	{ 15, 10, 10, 15 },
                { 10, 20, 20, 10 },
                { 10, 20, 20, 10 },
                { 15, 10, 10, 15 }
        };
        double value = 0.0;

        for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
            for (int j = 0; j < Quarto.BOARD_SIZE; j++) {
                if(gameNode.getBoard()[i][j] != null) {
                    value += positionValues[i][j];
                }
            }
        }
        return value;
    }

}
