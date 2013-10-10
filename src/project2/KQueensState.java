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


    public KQueensState(boolean[][] board) {
        this.board = board;
        kSize = board[0].length;
        queensOnBoard = 0;
    }


    public int getCollisions() {
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
                }
                else
                    out += "X\t";
            }
            out += "\n";
        }
        return out;
    }

    public static void main(String[] args) {
        boolean[][] board = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, true, false},
                {false, true, false, false},
        };
        KQueensState state = new KQueensState(board);
        System.out.println(state);
        System.out.println(state.getCollisions());

    }


}
