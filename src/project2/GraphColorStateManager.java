package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphColorStateManager extends LocalStateManager {

    private State state;
    private Random random;


	public GraphColorStateManager(int difficulty) {
        this.random = new Random();
		this.initialize(difficulty);
	}

    @Override
    public void initialize(int difficulty) {

        boolean[][] matrix = {
                {true, true, false, true, false},
                {true, true, true, false, true},
                {false, true, true, false, false},
                {true, false, false, true, false},
                {false, true, false, false, true}
        };
        int colors = 3;

        this.state = new GraphColorState(matrix, colors);
        this.state.randomize();
    }

    @Override
	public void modifyState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyRandomState() {
        int node = this.random.nextInt(this.state.getNumberOfNodes());
        int color = this.random.nextInt(this.state.getNumberOfColors());

        this.state.setColor(node, color);
	}

	@Override
	public void modifyIntelligentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<State> generateSuccessorState() {

        List<State> states = new ArrayList<State>();

        for (int i = 0; i < NUMBER_OF_SUCCESSOR_STATES; i++) {
            State temporaryState = this.state.copyState();

            int node = this.random.nextInt(temporaryState.getNumberOfNodes());
            int color = this.random.nextInt(temporaryState.getNumberOfColors());

            temporaryState.setColor(node, color);
            states.add(temporaryState);
        }

        return states;


	}

	@Override
	public double evaluate(State state) {
		return state.evaluate();
	}

	@Override
	public void displayState(State state) {
        System.out.println(state);
        System.out.println("Number of collisions in state: " + state.evaluate());
    }
}
