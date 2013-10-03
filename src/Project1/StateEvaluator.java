package Project1;

import java.util.ArrayList;
import java.util.List;

public class StateEvaluator {


    public final static int TRIPLET_VALUE = 100;


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

        heuri += calculate(possibleRow, gameNode);
        heuri += getPositionValues(gameNode);


        // System.out.println("heuri: "+ heuri);


        //return (Math.random() - 50) * 100;
        //return player ? heuri : heuri * -1;
        return 0;
    }


    public static int countSameProperty(Brick[] bricks, Quarto game) {
        if (bricks[0] == null && bricks[1] == null)
            return 0;

        Size size;
        Hole hole;
        Shape shape;
        Color color;
        if (bricks[0] != null){
            size = bricks[0].getSize();
            hole = bricks[0].getHole();
            shape = bricks[0].getShape();
            color = bricks[0].getColor();
        }
        else {
            size = bricks[1].getSize();
            hole = bricks[1].getHole();
            shape = bricks[1].getShape();
            color = bricks[1].getColor();
        }
        int sameSize = 0;
        int sameHole = 0;
        int sameShape = 0;
        int sameColor = 0;

        for (int i = 0; i < Quarto.BOARD_SIZE; i++) {
            Brick brick = bricks[i];
            if (brick == null)
                continue;
            if (brick.getColor() == color)
                sameColor++;
            else
                sameColor--;
            if (brick.getShape() == shape)
                sameShape++;
            else
                sameShape--;
            if (brick.getHole() == hole)
                sameHole++;
            else
                sameHole--;
            if (brick.getSize() == size)
                sameSize++;
            else
                sameSize--;
        }

        int heuri = 0;
        if (sameColor == 3 && game.brickWithColorIsLeft(color))
            heuri += TRIPLET_VALUE;
        if (sameShape == 3 && game.brickWithShapeIsLeft(shape))
            heuri += TRIPLET_VALUE;
        if (sameSize == 3 && game.brickWithSizeIsLeft(size))
            heuri += TRIPLET_VALUE;
        if (sameHole == 3 && game.brickWithHoleIsLeft(hole))
            heuri += TRIPLET_VALUE;
        return heuri;
    }

    private static double calculate(List<Brick[]> possibleRows, Quarto game) {

        double count = 0;
        for (Brick[] row : possibleRows) {
            count += countSameProperty(row, game);
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
