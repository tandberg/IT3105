package project2;

public class SimulatedAnnealing {

    private static final int NUMBER_OF_ITERATIONS = 100;



    public static void algorithm(State state) {
/*
        Random r = new Random();

        double tMax = 100.0;

        double fTarget = 0.0;

        double T = tMax;
        double evaluation = state.evaluate();

        boolean complete = false;

        while(! complete) {

            State bestState = null; // Pmax in the algorithms
            int bestScore = Integer.MAX_VALUE; // Previous node as max?

            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) { // n ?

                State temporaryState = state.copyState();

                int node = r.nextInt(temporaryState.getNumberOfNodes());
                int color = r.nextInt(temporaryState.getNumberOfColors());
                temporaryState.setColor(node, color);

                if(temporaryState.evaluate() < bestScore) {
                    bestState = temporaryState;
                }
            }

            double q = (bestScore - evaluation) / evaluation;
            double p = Math.min(1.0, Math.exp( (-q)/T ));

            double x = r.nextDouble();

            if(x > p) {
                state = bestState; // ?????
            } else {
                // Set til en random
            }

            T = T - 21.2; // En eller annen dT => exprementer


            complete = evaluation > fTarget;
        }*/
    }

}
