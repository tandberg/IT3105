package project2;

import java.util.*;

public class FutoshikiState extends State {


//    public int[][] matrix;
//    public Set<String> constraints2;

    private int[][] board;
    private boolean[][] locked;
    private Map<String, String> lessThan;
    private Map<String, String> greaterThan;
    private Set<String> constraints;
    private Random random;


    public FutoshikiState(int k) {
        lessThan = new HashMap<String, String>();
        greaterThan = new HashMap<String, String>();
        constraints = new HashSet<String>();
        random = new Random();
//        this.matrix = new int[k][k];
        //this.constraints = new ArrayList<Constraint>();
//        this.constraints2 = new HashSet<String>();
        board = FutoshikiGame.getEasyBoard();
        FutoshikiGame.initializeEasyBoard(lessThan, greaterThan, constraints);
        buildLocked();
    }

    public FutoshikiState(int[][] board, boolean[][] locked, Set<String> constraints, Random random) {
        this.board = board;
        this.locked = locked;
        this.constraints = constraints;
        this.random = random;
    }

    public static void main(String[] args) {
        FutoshikiState state = new FutoshikiState(4);
        System.out.println(state);
        state.randomize();
        System.out.println(state);

//        state.addConstraint(0, 0, 1, 0);
//        state.addConstraint(2, 0, 1, 2);

        // '0.2>0.3' set som holder på dessa

    }

    private void buildLocked() {
        locked = new boolean[board.length][board.length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row][col] != 0)
                    locked[row][col] = true;
            }
        }
    }

//    public void addConstraint(int ax, int ay, int bx, int by) {
//        //this.constraints.add(new Constraint(new Field(ax, ay), new Field(bx, by)));
//
//        constraints2.add(ax + "." + ay + ">" + bx + "." + by);
//
//    }

    private int getCollisionsForCoordinate(int row, int col) {
        int collisions = 0;
        if (constraints.contains(buildConstraintString(row, col, row, col + 1, "<"))) {
            if (!(board[row][col] < board[row][col + 1]))
                collisions++;
        }
        if (constraints.contains(buildConstraintString(row, col, row, col + 1, ">"))) {
            if (!(board[row][col] > board[row][col + 1]))
                collisions++;
        }

        if (constraints.contains(buildConstraintString(row, col, row, col - 1, "<"))) {
            if (!(board[row][col] < board[row][col - 1]))
                collisions++;
        }
        if (constraints.contains(buildConstraintString(row, col, row, col - 1, ">"))) {
            if (!(board[row][col] > board[row][col - 1]))
                collisions++;
        }


        if (constraints.contains(buildConstraintString(row, col, row + 1, col, "<"))) {
            if (!(board[row][col] < board[row + 1][col]))
                collisions++;
        }
        if (constraints.contains(buildConstraintString(row, col, row + 1, col, ">"))) {
            if (!(board[row][col] > board[row + 1][col]))
                collisions++;
        }

        if (constraints.contains(buildConstraintString(row, col, row - 1, col, "<"))) {
            if (!(board[row][col] < board[row - 1][col]))
                collisions++;
        }
        if (constraints.contains(buildConstraintString(row, col, row, col - 1, ">"))) {
            if (!(board[row][col] > board[row - 1][col]))
                collisions++;
        }

        return collisions;
    }

    private String buildConstraintString(int rowA, int colA, int rowB, int colB, String constraint) {
        return rowA + "." + colA + constraint + rowB + "." + colB;
    }

    private String getConstraint(int rowA, int colA, int rowB, int colB) {
        if (constraints.contains(rowA + "." + colA + ">" + rowB + "." + colB)) {
            return ">";
        } else if (constraints.contains(rowA + "." + colA + "<" + rowB + "." + colB)) {
            return "<";
        } else
            return ".";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (j < board.length - 1) {
                    sb.append("" + board[i][j] + " " + getConstraint(i, j, i, j + 1) + " ");
                } else
                    sb.append("" + board[i][j]);


            }
            sb.append("\n");

            for (int j = 0; j < board.length; j++) {
                if (i < board.length - 1) {
                    String c = getConstraint(i, j, i + 1, j);
                    if (c.equals(">"))
                        sb.append("v   ");
                    else if (c.equals("<"))
                        sb.append("^   ");
                    else
                        sb.append(".   ");

                }
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public double evaluate() {
        int collisions = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                collisions += getCollisionsForCoordinate(row, col);
            }
        }

        Set<Integer> used;
        for (int col = 0; col < board.length; col++) {
            used = new HashSet<Integer>();
            for (int row = 0; row < board.length; row++) {
                if (used.contains(board[row][col])) {
                    collisions++;
                }
                used.add(board[row][col]);
            }
        }

        return collisions;
    }

    @Override
    public State copyState() {
        return new FutoshikiState(board, locked, constraints, random);
    }

    @Override
    public void randomize() {
        Set<Integer> used;
        for (int row = 0; row < board.length; row++) {
            used = new HashSet<Integer>();

            for (int col = 0; col < board.length; col++) {
                if (locked[row][col]) {
                    used.add(board[row][col]);
                }
            }

            for (int col = 0; col < board.length; col++) {
                if (locked[row][col]) {
                    continue;
                }
                int temp;
                while (true) {
                    temp = random.nextInt(board.length) + 1;
                    if (used.contains(temp))
                        continue;
                    else {
                        used.add(temp);
                        break;
                    }
                }
                board[row][col] = temp;
            }
        }

    }

    @Override
    public void moveRandom() {
        int row = random.nextInt(board.length);
        int col1 = getLegalCol(row);
        int col2 = getLegalCol(row);
        int col1Value = board[row][col1];

        board[row][col1] = board[row][col2];
        board[row][col2] = col1Value;
    }

    private int getLegalCol(int row) {
        int col;
        while (true) {
            col = random.nextInt(board.length);
            if (locked[row][col])
                continue;
            else
                break;
        }
        return col;
    }

    @Override
    public void moveIntelligent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}

class Coordinate {
    int row;
    int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
