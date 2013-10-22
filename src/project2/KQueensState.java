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
//        randomize();


//        board = debug;
//        queensOnBoard = kSize;

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

//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board.length; j++) {
//                System.out.print(state.collisionMatrix[i][j] + "\t");
//            }
//            System.out.print("\n");
//
//        }

    }

    public void moveRandom() {


        for (int row = 0; row < kSize; row++) {
            for (int col = 0; col < kSize; col++) {
                if (board[row][col] != null) {
                    moveQueen(row, random.nextInt(kSize), row, col);
//                    board[i][j] = false;
//                    board[i][random.nextInt(kSize)] = true;
                }
            }
        }
//        List<Coordinate> positions = getBrickPositions();
//
//        Coordinate chosenBrick = positions.get(random.nextInt(positions.size()));
//
//        while (true) {
//            int x = random.nextInt(kSize);
//            int y = random.nextInt(kSize);
//
//            if (!board[y][x]) {
//                board[chosenBrick.y][chosenBrick.x] = false;
//                board[y][x] = true;
//                break;
//            }
//
//
//        }


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
            if (getCollisionsForCoordinate(queen.row, queen.col) > 0)
                return queen;
        }
    }

    public void intelligentMove(Queen queen) {
        int row = queen.row;
        int lowest = getCollisionsForCoordinate(row, queen.col);
//        System.out.println("Collisions: " + lowest);
        List<Integer> bestCols = new ArrayList<Integer>();
        bestCols.add(queen.col);
        for (int col = 0; col < kSize; col++) {
            if (col == queen.col) {
                continue;
            }
            int collisionsForCoordinate = getCollisionsForCoordinate(row, col);
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

    /*
    public List<Coordinate> getBrickPositions() {
        List<Coordinate> positions = new ArrayList<Coordinate>();

        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                if (board[i][j])
                    positions.add(new Coordinate(j, i));
            }
        }
        return positions;
    }*/

    public void randomize() {
        Random random = new Random();

        System.out.println("randomizing..");

        for (int row = 0; row < kSize; row++) {
            int col = random.nextInt(kSize);
            queens[row] = new Queen(row, col, true);
            board[row][col] = queens[row];


//            board[i][random.nextInt(kSize)] = true;
        }

//        System.out.println("init:");
//        System.out.println(this);

//        while (queensOnBoard < kSize) {
//            int x = random.nextInt(kSize);
//            int y = random.nextInt(kSize);
//            if (!board[y][x]) {
//                board[y][x] = true;
//                queensOnBoard++;
//            }
//        }
//        buildCollisionMatrix();

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

    private int getCollisionsForCoordinateInDiagonal(int x, int y) {
        int collisions = 0;

        for (int i = x + 1, j = y - 1; i < kSize && j >= 0; i++, j--) {
            if (board[j][i] != null)
                collisions++;
        }

        for (int i = x + 1, j = y + 1; i < kSize && j < kSize; i++, j++) {
            if (board[j][i] != null)
                collisions++;
        }

        for (int i = x - 1, j = y + 1; i >= 0 && j < kSize; i--, j++) {
            if (board[j][i] != null)
                collisions++;
        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[j][i] != null)
                collisions++;
        }
        return collisions;
    }

    private int getCollisions() {
        int collisions = 0;
//        collisions += getCollisionsInRow();
//        System.out.println("row: " + getCollisionsInRow());
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

        //System.out.println("diagonal1: " + collisions);

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

//        System.out.println("diagonal2: " + collisions);

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

//        System.out.println("diagonal3: " + collisions);

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
        return new KQueensState(kSize);
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
