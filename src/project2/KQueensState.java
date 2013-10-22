package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sigurd
 * Date: 10/10/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class KQueensState extends State {

    public static boolean[][] debug = {
            {false, false, false, false},
            {false, false, false, true},
            {true, true, true, true},
            {false, false, true, false},
    };
    private int kSize;
    private Queen[][] board;
    private Queen[] queens;
    private Random random = new Random();
    private int[][] collisionMatrix;

    public KQueensState(int k) {
        board = new Queen[k][k];
        kSize = board.length;
        queens = new Queen[kSize];
        buildCollisionMatrix();
    }

    public KQueensState(Queen[][] board, Queen[] queens, Random random) {
        this.board = board;
        kSize = board.length;
        this.queens = queens;
    }

    public static void main(String[] args) {
        boolean[][] board = {
                {false, false, true, false},
                {true, false, false, false},
                {true, false, false, false},
                {false, false, false, true},
        };
        KQueensState state = new KQueensState(4);
        System.out.println(state);
        System.out.println(state.getCollisions());

    }

    public void moveRandom() {


        for (int row = 0; row < kSize; row++) {
            for (int col = 0; col < kSize; col++) {
                if (board[row][col] != null) {
                    moveQueen(row, random.nextInt(kSize), row, col);
                }
            }
        }
    }

    @Override
    public void moveIntelligent() {
//        System.out.println(this);
        Queen queen = pickRandomQueen();
//        System.out.println("Queen, rad: " + queen.row + ", col: " + queen.col);
        intelligentMove(queen);
    }

    public Queen pickRandomQueen() {
        while (true) {
            Queen queen = queens[random.nextInt(kSize)];
            if (getCollisionsForCoordinate(queen.row, queen.col) > 1)
                return queen;
        }
    }

    public void intelligentMove(Queen queen) {
        int row = queen.row;
        int lowest = getCollisionsForCoordinate(row, queen.col);
//        System.out.println("Collisions for queen: " + lowest);
//        System.out.println("Collisions: " + lowest);
//        System.out.println(this);
//        System.out.println("Ser på alternativ plass til queen rad: " + queen.row + ", col: " + queen.col);
        List<Integer> bestCols = new ArrayList<Integer>();
        bestCols.add(queen.col);
        for (int col = 0; col < kSize; col++) {
            if (col == queen.col) {
                continue;
            }
            int collisionsForCoordinate = getCollisionsForCoordinate(row, col);
//            System.out.println("Alternativ kollone: " + col + ", " + collisionsForCoordinate + " kollisjoner. " + getCollisionsForCoordinateInColumn(col) + " in column, and " + getCollisionsForCoordinateInDiagonal(row, col) + " in diagonals");
            if (collisionsForCoordinate < lowest) {
                bestCols.removeAll(bestCols);
                lowest = collisionsForCoordinate;
            }
            if (collisionsForCoordinate <= lowest) {
                bestCols.add(col);
            }
        }

        for (int i = 0; i < bestCols.size(); i++) {
            Integer integer = bestCols.get(i);
//            System.out.println("Alternativ kollone: " + integer + ", " + getCollisionsForCoordinate(row, integer) + " kollisjoner.");
        }


        int col = bestCols.get(random.nextInt(bestCols.size()));
        moveQueen(row, col, row, queen.col);
    }

    public void moveQueen(int row, int col, int oldRow, int oldCol) {
        if (row == oldRow && col == oldCol)
            return;

        Queen q = board[oldRow][oldCol];
        q.row = row;
        q.col = col;
        q.collisions = q.getCollisionsForQueen();
        board[row][col] = q;
        board[oldRow][oldCol] = null;
    }

    public void randomize() {
        Random random = new Random();

        System.out.println("randomizing..");

        for (int row = 0; row < kSize; row++) {
            int col = random.nextInt(kSize);
            queens[row] = new Queen(row, col, true);
            board[row][col] = queens[row];
        }
    }

    public int[][] getCollisionMatrix() {
        return collisionMatrix;
    }

    private void buildCollisionMatrix() {
//        collisionMatrix = new int[kSize][kSize];
//
//        for (int i = 0; i < kSize; i++) {
//            setCollisionsForCoordinateInColumn(i);
//            setCollisionsForCoordinateInRow(i);
//            for (int j = 0; j < kSize; j++) {
//                collisionMatrix[j][i] += countCollisionsForCoordinate(i, j);
//            }
//
//        }

    }

    private int getCollisionsForCoordinate(int row, int col) {
        int collisions = 0;
        collisions += getCollisionsForCoordinateInColumn(col);
        collisions += getCollisionsForCoordinateInDiagonal(row, col);
        return collisions;
    }

    private int getCollisionsForCoordinateInColumn(int col) {
        int collisions = 0;
        for (int row = 0; row < kSize; row++) {
            if (board[row][col] != null) {
                collisions++;
            }
        }
        return collisions;
    }

    private int getCollisionsForCoordinateInDiagonal(int row, int col) {
        int collisions = 0;

        for (int r = row + 1, c = col - 1; r < kSize && c >= 0; r++, c--) {
            if (board[r][c] != null)
                collisions++;
        }

        for (int r = row + 1, c = col + 1; r < kSize && c < kSize; r++, c++) {
            if (board[r][c] != null)
                collisions++;
        }

        for (int r = row - 1, c = col + 1; r >= 0 && c < kSize; r--, c++) {
            if (board[r][c] != null)
                collisions++;
        }

        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] != null)
                collisions++;
        }
        return collisions;
    }

    private int getCollisions() {
        int collisions = 0;
        collisions += getCollisionsInColumn();
//        System.out.println("column: " + getCollisionsInColumn());
        collisions += getCollisionsInDiagonal();
//        System.out.println("diagonal: " + getCollisionsInDiagonal());
//        System.out.println("collisions: " + collisions);
        return collisions;
    }

    private int sumToOne(int n) {
        return n * (n + 1) / 2;
    }

    private int getCollisionsInColumn() {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            int c = 0;
            for (int j = 0; j < kSize; j++) {
                if (board[j][i] != null) {
                    c++;
                }
            }
            if (c > 0)
                collisions += sumToOne(c - 1);
        }
        return collisions;
    }

    private int getCollisionsInDiagonal() {
        int collisions = 0;

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = kSize - 1 - i;
            int y = kSize - 1;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y] != null) {
                    c++;
                }
                y--;
                x--;
            }
            if (c > 0)
                collisions += sumToOne(c - 1);
        }

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = kSize - 1;
            int y = kSize - 2 - i;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y] != null) {
                    c++;
                }
                y--;
                x--;
            }
            if (c > 0)
                collisions += sumToOne(c - 1);
        }

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = 0;
            int y = kSize - 1 - i;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y] != null) {
                    c++;
                }
                y--;
                x++;
            }

            if (c > 0)
                collisions += sumToOne(c - 1);
        }

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = i + 1;
            int y = kSize - 1;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y] != null) {
                    c++;
                }
                y--;
                x++;
            }
            if (c > 0)
                collisions += sumToOne(c - 1);
        }
//        System.out.println("diagonal4: " + collisions);

        return collisions;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                if (board[i][j] != null) {
                    out += "@\t";
                } else
                    out += ".\t";
            }
            out += "\n";
        }
        return out;
    }

    @Override
    public double evaluate() {
        return getCollisions();
    }

    @Override
    public State copyState() {
        return new KQueensState(board, queens, random);
    }

    class Queen {
        int row, col;
        boolean onBoard;
        int collisions;

        public Queen(int row, int col, boolean onBoard) {
            this.row = row;
            this.col = col;
            this.onBoard = onBoard;
//            this.collisions = getCollisionsForQueen();
        }

        public int getCollisionsForQueen() {
            return 0;
        }
    }

}
