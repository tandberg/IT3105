package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KQueensState extends State {

    private int kSize;
    private Queen[][] board;
    private Queen[] queens;
    private Random random;

    public KQueensState(int k) {
        random = new Random();
        board = new Queen[k][k];
        kSize = board.length;
        queens = new Queen[kSize];
    }

    public KQueensState(Queen[][] board, Queen[] queens, Random random) {
        this.random = random;
        this.board = board;
        kSize = board.length;
        this.queens = queens;
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
        Queen queen = pickRandomQueen();
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

        int col = bestCols.get(random.nextInt(bestCols.size()));
        moveQueen(row, col, row, queen.col);
    }

    public void moveQueen(int row, int col, int oldRow, int oldCol) {
        if (row == oldRow && col == oldCol)
            return;

        Queen q = board[oldRow][oldCol];
        q.row = row;
        q.col = col;

        board[row][col] = q;
        board[oldRow][oldCol] = null;
    }

    public void randomize() {
        Random random = new Random();
        for (int row = 0; row < kSize; row++) {
            int col = random.nextInt(kSize);
            queens[row] = new Queen(row, col, true);
            board[row][col] = queens[row];
        }
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
        collisions += getCollisionsInDiagonal();
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

        return collisions;
    }

    @Override
    public String toString() {
        String out = "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                if (board[i][j] != null) {
                    sb.append("@\t");
                } else {
                    sb.append(".\t");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public double evaluate() {
        return getCollisions();
    }

    @Override
    public State copyState() {

        Queen[][] b = new Queen[kSize][kSize];

        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                b[i][j] = board[i][j];
            }
        }

        Queen[] q = new Queen[kSize];
        for (int i = 0; i < kSize; i++) {
            q[i] = queens[i];
        }

        return new KQueensState(b, q, random);
    }

    class Queen {
        int row, col;
        boolean onBoard;

        public Queen(int row, int col, boolean onBoard) {
            this.row = row;
            this.col = col;
            this.onBoard = onBoard;
        }
    }

}
