package project2;

import java.util.HashSet;
import java.util.Set;

public class FutoshikiGame {

    public Set<String> constraints;
    public int[][] board;

    public FutoshikiGame(int difficulty) {
        constraints = new HashSet<String>();

        switch (difficulty) {
            case 1:
                initializeEasyBoard();
                board = getEasyBoard();
                break;
            case 2:
                initializeMediumBoard();
                board = getMediumBoard();
                break;
            case 3:
                initializeHardBoard();
                board = getHardBoard();
                break;
            default:

        }
    }

    public static int[][] getEasyBoard() {
        int[][] b = {
                {0, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 0, 0},
                {3, 0, 0, 0}
        };
        return b;
    }

    public static int[][] getMediumBoard() {
        int[][] b = {
                {5, 0, 0, 0, 0, 0},
                {0, 2, 0, 4, 0, 3},
                {0, 0, 2, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 5, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        return b;
    }


    public void initializeEasyBoard() {

        constraints.add("0.0>1.0");
        constraints.add("1.1>1.2");
        constraints.add("2.1<2.2");
        constraints.add("3.1<3.2");
    }

    public void initializeMediumBoard() {

        constraints.add("0.1>0.2");
        constraints.add("0.3>0.4");

        constraints.add("1.0>1.1");
        constraints.add("0.2<1.2");
        constraints.add("0.3<1.3");
        constraints.add("0.4>1.4");
        constraints.add("0.5<1.5");

        constraints.add("1.0>2.0");
        constraints.add("2.0<2.1");
        constraints.add("1.1<2.1");
        constraints.add("2.2>2.3");
        constraints.add("1.3>2.3");
        constraints.add("2.4<2.5");
        constraints.add("1.5<2.5");

        constraints.add("3.0<3.1");
        constraints.add("2.2>3.2");
        constraints.add("2.3<3.3");
        constraints.add("3.2<3.3");

        constraints.add("4.0<5.0");
        constraints.add("5.2<5.3");

    }

    private void initializeHardBoard() {
        /*
        constraints.add("0.2<0.3");

        constraints.add("1.0<1.1");
        constraints.add("1.1>1.2");
        constraints.add("1.2>1.3");
        constraints.add("1.4<1.5");

        constraints.add("2.3>2.4");
        constraints.add("2.4<2.5");

        constraints.add("3.0<3.1");
        constraints.add("3.3>3.4");

        constraints.add("4.5<5.5"); */

        constraints.add("0.0>1.0");
        constraints.add("0.6>0.7");
        constraints.add("0.6>1.6");
        constraints.add("0.8<1.8");
        constraints.add("1.2>2.2");
        constraints.add("1.5>1.6");
        constraints.add("1.6<2.5");
        constraints.add("1.6<1.7");
        constraints.add("2.1>3.1");
        constraints.add("2.3<2.4");
        constraints.add("2.4>3.4");
        constraints.add("2.5>3.5");
        constraints.add("2.7>2.8");
        constraints.add("3.0>4.0");
        constraints.add("3.4<4.4");
        constraints.add("3.5<3.6");
        constraints.add("3.6>3.7");
        constraints.add("3.7>3.8");
        constraints.add("4.0<5.0");
        constraints.add("4.1>5.1");
        constraints.add("4.2<5.2");
        constraints.add("4.6<5.6");
        constraints.add("4.8>5.8");
        constraints.add("5.1<5.2");
        constraints.add("5.1>6.1");
        constraints.add("5.4<5.5");
        constraints.add("5.4>6.4");
        constraints.add("5.6>5.7");
        constraints.add("5.7<6.7");
        constraints.add("6.0>6.1");
        constraints.add("6.1>7.1");
        constraints.add("6.2>6.3");
        constraints.add("6.4<6.5");
        constraints.add("6.6>6.7");
        constraints.add("6.8>7.8");
        constraints.add("7.2>8.2");
        constraints.add("7.4>7.5");
        constraints.add("7.5>7.6");
        constraints.add("7.5>8.5");
        constraints.add("8.1>8.2");
        constraints.add("8.7<8.8");

    }

    public static int[][] getHardBoard() {
        /*int[][] b = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 5},
                {0, 1, 0, 6, 2, 0},
                {0, 0, 4, 0, 0, 0},
                {0, 2, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0}
        }; */

        int[][] b = {
                {0, 0, 2, 1, 0, 0, 0, 0, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 9, 2, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 2, 1},
                {0, 2, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 0},
        };

        return b;
    }
}
