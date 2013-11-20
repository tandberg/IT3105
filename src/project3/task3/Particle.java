package project3.task3;

import java.util.Arrays;
import java.util.Random;

public class Particle {


    private final static double c1 = 0.5;
    private final static double c2 = 0.5;
    public static int[] globalBestPositions = new int[KnapsackProblem.NUM_DIMENSIONS];
    private static Package[] packages;
    private Random random;
    private int[] positions;
    private double[] velocities;
    private int[] bestLocalPositions;

    public Particle(Package[] packages) {
        int n = packages.length;
        random = new Random();
        positions = new int[n];
        velocities = new double[n];
        bestLocalPositions = new int[n];
        this.packages = packages;

        fillRandomPositions();
        fillRandomVelocities();
        updateBestLocalPositions();

        for (int i = 0; i < bestLocalPositions.length; i++) {
            bestLocalPositions[i] = 0;
        }

        for (int i = 0; i < globalBestPositions.length; i++) {
            globalBestPositions[i] = 0;
        }
    }

    private static int mapVelocity(double velocity) {
        double d = (1 / (1 + Math.exp(-velocity)));
        return (int) Math.round(d);
    }

    private double calculateValue(int[] positions) {
        double totalValue = 0;
        double totalWeight = 0;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                totalValue += packages[i].getValue();
                totalWeight += packages[i].getWeight();
            }
        }
        if (totalWeight > KnapsackProblem.LIMIT)
            return 0;
        return totalValue;
    }

    private void updateBestLocalPositions() {
        double bestValue = calculateValue(bestLocalPositions);
        double tmpDistance = calculateValue(positions);

        if (Math.sqrt(tmpDistance) > Math.sqrt(bestValue)) {
            // This Particle has better a better position than seen before.
            for (int i = 0; i < packages.length; i++) {
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
        for (int i = 0; i < packages.length; i++) {
            // Initial positions between -100 and 100
            positions[i] = mapVelocity((random.nextDouble() * KnapsackProblem.LIMIT * 2) - KnapsackProblem.LIMIT);
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
        double bestValue = 0;
        double tmpValue = 0;

        for (int i = 0; i < bestLocalPositions.length; i++) {
            if (bestLocalPositions[i] == 1) {
                tmpValue += packages[i].getValue();
                bestValue += packages[i].getWeight();

            }
        }

        if (tmpValue > bestValue) {
            // This Particle has better a better position than all have seen before.
            for (int i = 0; i < bestLocalPositions.length; i++) {
                globalBestPositions[i] = bestLocalPositions[i];
            }
        }
    }

    private void updateVelocity() {
        for (int i = 0; i < velocities.length; i++) {
//            System.out.println("bestlocal: " + bestLocalPositions[i]);
//            System.out.println("bestglobal: " + globalBestPositions[i]);

            double inertia = velocities[i];
            double memory = c1 * random.nextDouble() * (bestLocalPositions[i] - velocities[i]);
            double influence = c2 * random.nextDouble() * (globalBestPositions[i] - velocities[i]);

//            System.out.println("prev speed: "+  inertia + "\nmemory: " + memory + "\ninfluence: " + influence + "\t\t position: " + positions[i] + " \n----------------------------");

            velocities[i] = (inertia + memory + influence) * -1;

            if (velocities[i] > 4.25) {
                velocities[i] = 4.25;
            } else if (velocities[i] < -4.25) {
                velocities[i] = -4.25;
            }
        }
    }

    private void updatePosition() {
        for (int i = 0; i < packages.length; i++) {
            positions[i] = mapVelocity(velocities[i]);
        }
    }

    public String toString() {
        return "Position: " + Arrays.toString(positions) + " Velocity: " + Arrays.toString(velocities);
    }

    public int[] getPositions() {
        return positions;
    }

    public double getBestValue() {
        return calculateValue(bestLocalPositions);
    }

    public String toJSON() {

        double print1 = (positions[0] < 0.001 && positions[0] > 0) || (positions[0] > -0.001 && positions[0] < 0) ? 0 : positions[0];
        //double print2 = (positions[1] < 0.001 && positions[1] > 0) || (positions[1] > -0.001 && positions[1] < 0) ? 0 : positions[1];

        return "[" + print1 + ",0]"; // tmp
    }

}
