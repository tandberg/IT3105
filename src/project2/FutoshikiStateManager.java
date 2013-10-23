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
        FutoshikiGame game;
        game = new FutoshikiGame(difficulty);

        this.state = new FutoshikiState(game);
        this.state.randomize();
        System.out.println(this.state);
    }

    @Override
    public void modifyIntelligentState() {
        this.state.moveIntelligent();
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

}
