package project2;

import java.util.List;
import java.util.Random;

public class SimulatedAnnealing implements Algorithm {

    private static final int NUMBER_OF_ITERATIONS = 100;
    private static SimulatedAnnealing sa = null;
    private static double T_MAX = 10000.0;
    private static double D_T = 0.97;
    private static double F_TARGET = 0.0;
    private Random random;

    private SimulatedAnnealing() {
        this.random = new Random();
    }

    public static SimulatedAnnealing getInstance() {
        if (sa == null)
            return new SimulatedAnnealing();
        else
            return sa;
    }

    public void solve(LocalStateManager manager) {
        double T = T_MAX;

        int iterations = 0;

        while (true) {
            //TODO remove
//            if (iterations % 100 == 0) {
//                System.out.println(iterations + " - T:" + T);
//            }

            double evaluation = manager.evaluate();
            //System.out.println(manager);
            List<State> neighbours = manager.generateSuccessorStates();

            State bestNeighbour = null;
            double bestNeighbourEvaluation = Double.MAX_VALUE;

            for (State neighbour : neighbours) {
                double neighbourEvaluation = neighbour.evaluate();

                if (neighbourEvaluation < bestNeighbourEvaluation) { // TODO: Større eller mindre? Sende inn som argument?
                    bestNeighbour = neighbour;
                    bestNeighbourEvaluation = neighbourEvaluation;
                }
            }
            System.out.println(bestNeighbour);
            double q = (bestNeighbourEvaluation - evaluation) / evaluation;
            double p = Math.min(1, Math.exp(-q / T));
            double x = this.random.nextDouble();


            if (x > p) {
                manager.modifyState(bestNeighbour);
                evaluation = bestNeighbour.evaluate();
            } else {

                State s = neighbours.get(this.random.nextInt(neighbours.size()));
                manager.modifyState(s);
                evaluation = s.evaluate();
            }

            T = T * D_T;
            iterations++;

            if (evaluation == F_TARGET) {
                break;
            }
        }

        System.out.println("SA Iterations: " + iterations);
    }

}
