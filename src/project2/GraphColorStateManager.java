package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphColorStateManager extends LocalStateManager {

    private static int NEW_STATES = 100;
    private Random random;


    public GraphColorStateManager(int difficulty) {
        this.random = new Random();
        this.initialize(difficulty);
    }

    @Override
    public void initialize(int difficulty) {

        Puzzle puzzle;
        switch (difficulty) {
            case 1:
                puzzle = PredefinedGraphColorStates.getFileEasyGraphColorPuzzle();
                this.state = new GraphColorState(puzzle.matrix, puzzle.colors);
                break;

            case 2:
                puzzle = PredefinedGraphColorStates.getFileMediumGraphColorPuzzle();
                this.state = new GraphColorState(puzzle.matrix, puzzle.colors);
                break;

            case 3:
                puzzle = PredefinedGraphColorStates.getFileHardGraphColorPuzzle();
                this.state = new GraphColorState(puzzle.matrix, puzzle.colors);
                break;
        }

        this.state.randomize();
    }

    @Override
    public void modifyRandomState() {
    }

    @Override
    public void modifyIntelligentState() {
        state.moveIntelligent();
    }

    @Override
    public List<State> generateSuccessorStates() {

        List<State> states = new ArrayList<State>();

        for (int i = 0; i < NEW_STATES; i++) {
            State temporaryState = this.state.copyState();
            temporaryState.moveRandom();
            states.add(temporaryState);
        }

        return states;

    }

    @Override
    public double evaluate() {
        return this.state.evaluate();
    }

    @Override
    public void displayState() {
        System.out.println(this.state);
        System.out.println("Number of collisions in state: " + this.state.evaluate());
    }
}
