package project2;

import java.util.List;

public class KQueensStateManager extends LocalStateManager {

    private int ksize;
    private boolean[][] board;
    private int queensOnBoard;
    private KQueensState state;

    public KQueensStateManager(int difficulty) {
        this.initialize(8);
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
    public void modifyState() {
        //To change body of implemented methods use File | Settings | File Templates.
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
    public List<State> generateSuccessorState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double evaluate(State state) {
        // TODO Auto-generated method stub
        return 0;
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
    public void displayState(State state) {
        // TODO Auto-generated method stub

    }

}
