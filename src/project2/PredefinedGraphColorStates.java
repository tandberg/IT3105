package project2;

public class PredefinedGraphColorStates {

    public static Puzzle getEasyGraphColorPuzzle() {
        int colors = 2;
        boolean[][] matrix = {
                {true, true, false, true, false},
                {true, true, true, false, true},
                {false, true, true, false, false},
                {true, false, false, true, false},
                {false, true, false, false, true}
        };

        return new Puzzle(matrix, colors);
    }

    public static Puzzle getTriforceGraphColorPuzzle() {
        int colors = 3;
        boolean[][] matrix = {
                {true, true, true, false, false, false},
                {true, true, true, true, true, false},
                {true, true, true, false, true, true},
                {false, true, false, true, true, false},
                {false, true, true, true, true, true},
                {false, false, true, false, true, true}
        };

        return new Puzzle(matrix, colors);
    }

    public static Puzzle getMediumGraphColorPuzzle() { // TODO: Make more puzzles
        int colors = 3;
        boolean[][] matrix = {
                {true, true, true, true, false, true, false, true},
                {true, true, true, false, true, true, true, false},
                {true, true, true, true, true, false, true, true},
                {true, false, true, true, true, true, true, false},
                {false, true, true, true, true, true, false, true},
                {true, true, false, true, true, true, true, true},
                {false, true, true, true, false, true, true, true},
                {true, false, true, false, true, true, true, true}
        };

        return new Puzzle(matrix, colors);
    }


}
