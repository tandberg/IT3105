package project2;

/**
 * Created with IntelliJ IDEA.
 * User: sigurd
 * Date: 10/10/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class KQueensState extends State {

    private int kSize;
    private boolean[][] board;
    private int queensOnBoard;
    private int[][] collisionMatrix;


    public KQueensState(boolean[][] board) {
        this.board = board;
        kSize = board.length;
        queensOnBoard = countQueensOnBoard();
    }

    public static void main(String[] args) {
        boolean[][] board = {
                {false, true, false, false},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, true, false},
        };
        KQueensState state = new KQueensState(board);
        System.out.println(state);
        System.out.println(state.getCollisions());

    }

    private void buildCollisionMatrix() {
        collisionMatrix = new int[kSize][kSize];
        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {
                countCollisionsForCoordinate(i, j);
            }

        }

    }

    private int countCollisionsForCoordinate(int x, int y) {
        int collisions = 0;


        return collisions;
    }

    private int getCollisionsForCoordinateInRow(int y) {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            int c = 0;
            if (board[y][i])
                c++;
            if (c > 0)
                collisions += (c - 1);
        }
        return collisions;
    }

    private int getCollisionsForCoordinateInColumn(int x) {
        int collisions = 0;
        for (int i = 0; i < kSize; i++) {
            int c = 0;
            if (board[i][x]) {
                c++;
            }
            if (c > 0)
                collisions += c - 1;
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
        collisions += getCollisionsInColumn();
        collisions += getCollisionsInDiagonal();
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
            int y = kSize - 1 - i;
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
            int y = kSize - 1 - 1;
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
            int x = 0 + i;
            int y = kSize - 1;
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
                    out += "O\t";
                } else
                    out += "X\t";
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
        return new KQueensState(this.board);
    }

    @Override
    public int getColor(int node) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setColor(int node, int color) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getNumberOfNodes() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getNumberOfColors() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
