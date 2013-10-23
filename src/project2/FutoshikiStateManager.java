package project2;

import java.util.ArrayList;
import java.util.List;

public class FutoshikiStateManager extends LocalStateManager {

    private final static int NEW_STATES = 100;


    public FutoshikiStateManager(int difficulty) {
        initialize(difficulty);
    }

    @Override
    public void initialize(int difficulty) {
        int size;
        switch (difficulty) {
            case 1:
                size = 4;
                break;
            case 2:
                size = 8;
                break;
            case 3:
                size = 1000;
                break;
            default:
                size = 4;
        }

        this.state = new FutoshikiState(size);
        this.state.randomize();
        System.out.println(this.state);
    }

    @Override
    public void modifyRandomState() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void modifyIntelligentState() {
        //To change body of implemented methods use File | Settings | File Templates.
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
