package project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphColorStateManager extends LocalStateManager {

    private State state;
    private Random random;


	public GraphColorStateManager() {
        this.random = new Random();
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyState() {
		// TODO Auto-generated method stub
		
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

        List<State> states = new ArrayList<State>();

        for (int i = 0; i < NUMBER_OF_SUCCESSOR_STATES; i++) {
            State temporaryState = this.state.copyState();

            int node = this.random.nextInt(temporaryState.getNumberOfNodes());
            int color = this.random.nextInt(temporaryState.getNumberOfColors());

            temporaryState.setColor(node, color);
            states.add(temporaryState);
        }

        return states;

        /*       simulated annealing
        double tMax = 100.0;

        double fTarget = 0.0;

        double T = tMax;
        double evaluation = this.state.evaluate();

        boolean complete = false;

        while(! complete) {

            State bestState = null; // Pmax in the algorithm
            int bestScore = Integer.MAX_VALUE; // Previous node as max?

            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {

                State temporaryState = this.state.copyState();

                int node = this.random.nextInt(temporaryState.getNumberOfNodes());
                int color = this.random.nextInt(temporaryState.getNumberOfColors());
                temporaryState.setColor(node, color);

                if(temporaryState.evaluate() < bestScore) {
                    bestState = temporaryState;
                }
            }

            double q = (bestScore - evaluation) / evaluation;
            double p = Math.min(1.0, Math.exp( (-q)/T ));

            double x = this.random.nextDouble();

            if(x > p) {
                this.state = bestState;
            } else {
                // Set til en random
            }

            T = T - 21.2; // pretty number huh? :D


            complete = evaluation > fTarget;
        }

		return null; */
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
