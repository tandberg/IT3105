package project2;

import java.util.List;

public abstract class LocalStateManager {

    public final static int NUMBER_OF_SUCCESSOR_STATES = 10000;

	// 1.
	public abstract void initialize(int size);
	
	// 2.
	public abstract void modifyState();
	public abstract void modifyRandomState();
	public abstract void modifyIntelligentState();
	
	// 3.
	public abstract List<State> generateSuccessorState();
	
	// 4.
	public abstract double evaluate(State state);
	
	// 5.
	public abstract void displayState(State state);
	
}
