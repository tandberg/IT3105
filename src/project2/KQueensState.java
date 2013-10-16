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
            {false, false, true, false},
            {true, false, false, false},
            {true, false, false, false},
            {false, false, false, true},
    };
    private int kSize;
    private boolean[][] board;
    private int queensOnBoard;
    private int[][] collisionMatrix;

    public KQueensState(int k) {
        board = new boolean[k][k];
        kSize = board.length;
        queensOnBoard = countQueensOnBoard();
        buildCollisionMatrix();
        randomize();


        board = debug;
        queensOnBoard = kSize;

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

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(state.collisionMatrix[i][j] + "\t");
            }
            System.out.print("\n");

        }

    }

    public void moveRandom() {
        Random random = new Random();

        List<Coordinate> positions = getBrickPositions();

        Coordinate chosenBrick = positions.get(random.nextInt(positions.size()));

        while (true) {
            int x = random.nextInt(kSize);
            int y = random.nextInt(kSize);

            if (!board[y][x]) {
                board[chosenBrick.y][chosenBrick.x] = false;
                board[y][x] = true;
                break;
            }


        }


    }

    public List<Coordinate> getBrickPositions() {
        List<Coordinate> positions = new ArrayList<Coordinate>();

        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                if (board[i][j])
                    positions.add(new Coordinate(j, i));
            }
        }
        return positions;
    }

    public void randomize() {
        Random random = new Random();


        while (queensOnBoard < kSize) {
            int x = random.nextInt(kSize);
            int y = random.nextInt(kSize);
            if (!board[y][x]) {
                board[y][x] = true;
                queensOnBoard++;
            }
        }
        buildCollisionMatrix();

    }

    public int[][] getCollisionMatrix() {
        return collisionMatrix;
    }

    private void buildCollisionMatrix() {
        collisionMatrix = new int[kSize][kSize];

        for (int i = 0; i < kSize; i++) {
            setCollisionsForCoordinateInColumn(i);
            setCollisionsForCoordinateInRow(i);
            for (int j = 0; j < kSize; j++) {
                collisionMatrix[j][i] += countCollisionsForCoordinate(i, j);
            }

        }

    }

    private int countCollisionsForCoordinate(int x, int y) {
        int collisions = 0;

        if (board[y][x])
            collisions++;
        collisions += getCollisionsForCoordinateInDiagonal(x, y);
        return collisions;
    }

    private void setCollisionsForCoordinateInRow(int y) {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            if (board[y][i])
                collisions++;
        }
        for (int i = 0; i < kSize; i++) {
            collisionMatrix[y][i] += collisions;
        }
    }

    private void setCollisionsForCoordinateInColumn(int x) {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            if (board[i][x]) {
                collisions++;
            }
        }
        for (int i = 0; i < kSize; i++) {
            collisionMatrix[i][x] += collisions;
        }
    }

    private int getCollisionsForCoordinateInDiagonal(int x, int y) {
        int collisions = 0;

        for (int i = x + 1, j = y - 1; i < kSize && j >= 0; i++, j--) {
            if (board[j][i])
                collisions++;
        }

        for (int i = x + 1, j = y + 1; i < kSize && j < kSize; i++, j++) {
            if (board[j][i])
                collisions++;
        }

        for (int i = x - 1, j = y + 1; i >= 0 && j < kSize; i--, j++) {
            if (board[j][i])
                collisions++;
        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[j][i])
                collisions++;
        }
        return collisions;
    }

    private int countQueensOnBoard() {
        int queens = 0;
        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                if (board[i][j])
                    queens++;
            }
        }
        return queens;
    }

    private int getCollisions() {
        int collisions = 0;
        collisions += getCollisionsInRow();
        System.out.println("row: " + getCollisionsInRow());
        collisions += getCollisionsInColumn();
        System.out.println("column: " + getCollisionsInColumn());
        collisions += getCollisionsInDiagonal();
        System.out.println("diagonal: " + getCollisionsInDiagonal());
        System.out.println("collisions: " + collisions);
        return collisions;
    }

    private int getCollisionsInRow() {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            int c = 0;
            for (int j = 0; j < kSize; j++) {
                if (board[i][j])
                    c++;
            }
            if (c > 0)
                collisions += (c - 1);
        }
        return collisions;
    }

    private int getCollisionsInColumn() {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            int c = 0;
            for (int j = 0; j < kSize; j++) {
                if (board[j][i]) {
                    c++;
                }
            }
            if (c > 0)
                collisions += c - 1;
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
                if (board[x][y]) {
                    c++;
                }
                y--;
                x--;
            }
            if (c > 0)
                collisions += c - 1;
        }

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = kSize - 1;
            int y = kSize - 2 - i;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y]) {
                    c++;
                }
                y--;
                x--;
            }
            if (c > 0)
                collisions += c - 1;
        }

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = 0;
            int y = kSize - 1 - i;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y]) {
                    c++;
                }
                y--;
                x++;
            }
            if (c > 0)
                collisions += c - 1;
        }

        for (int i = 0, j = 0; i < kSize && j < kSize; i++, j--) {
            int c = 0;
            int x = i;
            int y = kSize - 2;
            for (int k = 0; k < kSize; k++) {
                if (x >= kSize || x < 0 || y >= kSize || y < 0)
                    break;
                if (board[x][y]) {
                    c++;
                }
                y--;
                x++;
            }
            if (c > 0)
                collisions += c - 1;
        }
        return collisions;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                if (board[i][j]) {
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

    class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
