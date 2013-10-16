package project2;

import java.util.ArrayList;
import java.util.List;

public class KQueensStateManager extends LocalStateManager {

    private final static int NEW_STATES = 10000;
    private int ksize;
    private boolean[][] board;
    private int queensOnBoard;

    public KQueensStateManager(int difficulty) {
        this.initialize(4);
    }

    @Override
    public void initialize(int size) {
        ksize = size;
        board = new boolean[ksize][ksize];
        queensOnBoard = 0;

        this.state = new KQueensState(size);
        this.state.randomize();
    }

    @Override
    public void modifyRandomState() {
        // TODO Auto-generated method stub

    }

    @Override
    public void modifyIntelligentState() {
        // TODO Auto-generated method stub

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

    private boolean checkColumns() {
        for (int i = 0; i < ksize; i++) {
            for (int j = 0; j < ksize; j++) {

            }

        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < ksize; i++) {

        }
        return false;
    }

    @Override
    public void displayState() {
        System.out.println(this.state.toString());
    }

}
