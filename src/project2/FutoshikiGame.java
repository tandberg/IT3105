package project2;

import java.util.Map;
import java.util.Set;

public class FutoshikiGame {

    public static void initializeEasyBoard(Map<String, String> lessThan, Map<String, String> greaterThan, Set<String> constraints) {

        constraints.add("0.0>1.0");
        constraints.add("1.1>1.2");
        constraints.add("2.1<2.2");
        constraints.add("3.1<3.2");
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

    private String getConstraintString(int rowA, int colA, int rowB, int colB, boolean greater) {
        return rowA + "." + colA + ">" + rowB + "." + colB;
    }


}