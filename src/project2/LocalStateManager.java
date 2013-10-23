package project2;

import java.util.List;

public abstract class LocalStateManager {

    protected State state;

    public void modifyState(State state) {
        this.state = state;
    }

    public abstract void initialize(int size);

    public abstract void modifyIntelligentState();

    public abstract List<State> generateSuccessorStates();

    public abstract double evaluate();

    public String toString() {
        return state.toString();
    }

}
