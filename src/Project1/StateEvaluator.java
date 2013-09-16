package Project1;

import java.util.ArrayList;
import java.util.List;

public class StateEvaluator {

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
