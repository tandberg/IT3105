package project3.task1;

import java.util.Arrays;
import java.util.Random;

public class Particle {


    public static double[] globalBestPositions = new double[CircleProblem.NUM_DIMENSIONS];
    private final static double c1 = 0.5;
    private final static double c2 = 0.5;
    private Random random;

    private int dimensions;
    private double[] positions;
    private double[] velocities;

    private double[] bestLocalPositions;

    public Particle(int n) {
        random = new Random();
        this.dimensions = n;
        positions = new double[n];
        velocities = new double[n];
        bestLocalPositions = new double[n];

        fillRandomPositions();
        fillRandomVelocities();
        updateBestLocalPositions();

        for(int i = 0; i < bestLocalPositions.length; i++) {
            bestLocalPositions[i] = CircleProblem.LIMIT;
        }

        for (int i = 0; i < globalBestPositions.length; i++) {
            globalBestPositions[i] = CircleProblem.LIMIT;
        }
    }

    private void updateBestLocalPositions() {
        double bestDistance = 0;
        double tmpDistance = 0;

        for (int i = 0; i < positions.length; i++) {
            tmpDistance += Math.pow(positions[i], 2);
            bestDistance += Math.pow(bestLocalPositions[i], 2);
        }

        if(Math.sqrt(tmpDistance) < Math.sqrt(bestDistance)) {
            // This Particle has better a better position than seen before.
            for (int i = 0; i < positions.length; i++) {
                bestLocalPositions[i] = positions[i];
            }
        }
    }

    private void fillRandomVelocities() {
        for (int i = 0; i < velocities.length; i++) {
            velocities[i] = (random.nextDouble() * 2) - 1; // Starts with random speed in any dimensions
        }
    }

    private void fillRandomPositions() {
        for (int i = 0; i < positions.length; i++) {
            // Initial positions between -100 and 100
            positions[i] = (random.nextDouble() * CircleProblem.LIMIT*2) - CircleProblem.LIMIT;
        }
        updateBestLocalPositions();
    }

    public void update() {
        updatePosition();
        updateVelocity();

        updateBestLocalPositions();
        updateGlobals();
    }

    private void updateGlobals() {
        double bestDistance = 0;
        double tmpDistance = 0;

        for (int i = 0; i < bestLocalPositions.length; i++) {
            tmpDistance += Math.pow(bestLocalPositions[i], 2);
            bestDistance += Math.pow(globalBestPositions[i], 2);
        }

        if(Math.sqrt(tmpDistance) < Math.sqrt(bestDistance)) {
            // This Particle has better a better position than all have seen before.
            for (int i = 0; i < bestLocalPositions.length; i++) {
                globalBestPositions[i] = bestLocalPositions[i];
            }
        }
    }

    private void updateVelocity() {
        for (int i = 0; i < velocities.length; i++) {

            double inertia = velocities[i];
            double memory = c1 * random.nextDouble() * (bestLocalPositions[i] - positions[i]);
            double influence = c2 * random.nextDouble() * (globalBestPositions[i] - positions[i]);

            velocities[i] = inertia + memory + influence;

            if(velocities[i] > 1) {
                velocities[i] = 1;
            } else if(velocities[i] < -1) {
                velocities[i] = -1;
            }
        }
    }

    private void updatePosition() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = positions[i] + velocities[i];
        }
    }

    public String toString() {
        return "Position: " + Arrays.toString(positions) + " Velocity: " + Arrays.toString(velocities);
    }

    public String toJSON() {

        double print1 = (positions[0] < 0.001 && positions[0] > 0) || (positions[0] > -0.001 && positions[0] < 0) ? 0 : positions[0];
        if(positions.length == 2) {
            double print2 = (positions[1] < 0.001 && positions[1] > 0) || (positions[1] > -0.001 && positions[1] < 0) ? 0 : positions[1];
            return "[" + print1 +", " + print2 + "]"; // tmp
        } else {
            return "[" + print1 +",0]";
        }
    }

}
