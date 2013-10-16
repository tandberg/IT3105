package project2;

import java.util.List;
import java.util.Random;

public class SimulatedAnnealing implements Algorithm {

    private static final int NUMBER_OF_ITERATIONS = 100;
    private static SimulatedAnnealing sa = null;
    private static double T_MAX = 100.0;
    private static double D_T = 0.2;
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
        boolean complete = false;

        while (!complete) {
            System.out.println(manager);
            double evaluation = manager.evaluate();
            System.out.println(evaluation + " - " + F_TARGET);
            List<State> neighbours = manager.generateSuccessorStates();

            State bestNeighbour = null;
            double bestNeighbourEvaluation = Double.MAX_VALUE;

            for (State neighbour : neighbours) {
                double neighbourEvaluation = neighbour.evaluate();
                if (neighbourEvaluation < bestNeighbourEvaluation) {
                    bestNeighbour = neighbour;
                    bestNeighbourEvaluation = neighbourEvaluation;
                }
            }

            double q = (bestNeighbourEvaluation - evaluation) / evaluation;
            double p = Math.min(1, Math.exp(-q / T));
            double x = this.random.nextDouble();

            if (x > p) {
                manager.modifyState(bestNeighbour);
            } else {
                manager.modifyState(neighbours.get(this.random.nextInt(neighbours.size())));
            }

            T = T * D_T;

            complete = evaluation == F_TARGET;
        }
    }

}
