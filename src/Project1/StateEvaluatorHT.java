package Project1;


public class StateEvaluatorHT {

    public static double evaluate (Quarto state, int depth) {
        double score = 0;
        Brick[][] board = state.getBoard();

        if (state.isComplete() == Quarto.WINNER) {
            System.out.println("Current state wins game");
            score = Double.MAX_VALUE;
        } else if (state.isComplete() == Quarto.DRAW) {
            System.out.println("Current state draws game");
            score = Double.MIN_VALUE;
        } else {

            if (completesLine(state)) {
                System.out.println("Current state completes line");
                score = 1000;
            }

            if (canPlaceSecondPiece(state)) {
                System.out.println("Current state adds piece number two to line");
                score = 500;
            }
        }

        // Take depth into account when evaluating a move. We want to
        // win sooner, rather than later.
        return score*-1;
    }


    private static boolean canPlaceSecondPiece(Quarto state) {
        Brick[][] board = state.getBoard();
        int x = state.getX();
        int y = state.getY();

        int availablePositionsInLine = 0;
        for (int row = 0; row < Quarto.BOARD_SIZE; row++) {
            if (board[row][y] == null) {
                availablePositionsInLine++;
            }
        }

        if (availablePositionsInLine == 2) { return true; }

        availablePositionsInLine = 0;

        for (int column = 0; column < Quarto.BOARD_SIZE; column++) {
            if (board[x][column] == null) {
                return false;
            }
        }
        if (availablePositionsInLine == 2) { return true; }
        return false;
    }


    private static boolean completesLine(Quarto state) {
        Brick[][] board = state.getBoard();
        int y = state.getY();
        int x = state.getX();

        for (int row = 0; row < Quarto.BOARD_SIZE; row++) {
            if (board[row][y] == null) {
                return false;
            }
        }

        for (int column = 0; column < Quarto.BOARD_SIZE; column++) {
            if (board[x][column] == null) {
                return false;
            }
        }

        return true;
    }
}