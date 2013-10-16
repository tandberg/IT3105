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

    public static Puzzle getMediumColorPuzzle() { // TODO: Make more puzzles
        int colors = 4;
        boolean[][] matrix = {
                {true, true, false, true, false},
                {true, true, true, false, true},
                {false, true, true, false, false},
                {true, false, false, true, false},
                {false, true, false, false, true}
        };

        return new Puzzle(matrix, colors);
    }


}
