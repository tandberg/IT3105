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

    public static Puzzle getMediumGraphColorPuzzle() {
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

    public static Puzzle getHardGraphColorPuzzle() { // TODO: Make more puzzles
        int colors = 4;
        boolean t = true;
        boolean f = false;

        boolean[][] matrix = {

              // a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t
                {t, t, t, f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f}, // a
                {t, t, f, t, t, f, f, f, f, f, f, f, f, f, f, f, f, f, f, f}, // b
                {t, f, t, f, f, f, t, f, f, f, f, f, f, f, f, f, f, f, t, f}, // c
                {f, t, f, t, f, f, t, t, f, f, f, f, f, f, f, f, f, f, f, f}, // d
                {f, t, f, f, t, f, f, f, t, t, f, f, f, f, f, f, f, f, f, f}, // e
                {t, f, f, f, f, t, f, f, f, t, f, f, f, f, f, f, f, f, f, t}, // f
                {f, f, t, t, f, f, t, f, f, f, t, f, f, f, f, f, f, f, f, f}, // g
                {f, f, f, t, f, f, f, t, t, f, f, t, f, f, f, f, f, f, f, f}, // h
                {f, f, f, f, t, f, f, t, t, f, f, f, t, f, f, f, f, f, f, f}, // i
                {f, f, f, f, t, t, f, f, f, t, f, f, f, t, f, f, f, f, f, f}, // j
                {f, f, f, f, f, f, t, f, f, f, t, t, f, f, f, f, t, f, f, f}, // k
                {f, f, f, f, f, f, f, t, f, f, t, t, f, f, t, f, f, f, f, f}, // l
                {f, f, f, f, f, f, f, f, t, f, f, f, t, t, t, f, f, f, f, f}, // m
                {f, f, f, f, f, f, f, f, f, t, f, f, t, t, f, f, t, f, f, f}, // n
                {f, f, f, f, f, f, f, f, f, f, f, t, t, f, t, t, f, f, f, f}, // o
                {f, f, f, f, f, f, f, f, f, f, f, f, f, f, t, t, t, t, f, f}, // p
                {f, f, f, f, f, f, f, f, f, f, t, f, f, f, f, t, t, f, t, f}, // q
                {f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, f, t, f, t}, // r
                {f, f, t, f, f, f, f, f, f, f, f, f, f, f, f, f, t, f, t, t}, // s
                {f, f, f, f, f, t, f, f, f, f, f, f, f, f, f, f, f, t, t, t}, // t

        };

        return new Puzzle(matrix, colors);
    }
}
