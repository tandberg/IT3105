package project3.task4;

import java.util.Arrays;
import java.util.Random;

public class Particle {


    private final static double c1 = 0.7;
    private final static double c2 = 1.4;
    public static int[] globalBestPositions = new int[KnapsackProblem.NUM_DIMENSIONS];
    private static Package[] packages;
    private Random random;
    private int[] positions;
    private double[] velocities;
    private int[] bestLocalPositions;
    private double localBestValue = 0;
    private double globalBestValue = 0;
    private double globalWeight = 0;
    private double globalVolume = 0;
    private double w = 1;


    public Particle(Package[] packages) {
        int n = packages.length;
        random = new Random();
        positions = new int[n];
        velocities = new double[n];
        bestLocalPositions = new int[n];
        this.packages = packages;

        fillRandomVelocities();

        for (int i = 0; i < globalBestPositions.length; i++) {
            globalBestPositions[i] = 0;
        }
        double tmpValue = calculateValue(positions);
        updateBestLocalPositions(tmpValue);
        updateGlobals(tmpValue);
    }

    private int mapVelocity(double velocity) {

        double v = velocity;
        double d = (1 / (1 + Math.exp(-v)));
        double rnd = random.nextDouble();
        if (rnd < d) {
            return 1;
        }
        return 0;
    }

    private double calculateValue(int[] positions) {

        double totalValue = 0;
        double totalWeight = 0;
        double totalVolume = 0;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                totalValue += packages[i].getValue();
                totalWeight += packages[i].getWeight();
                totalVolume += packages[i].getVolume();
            }
        }
        if (totalWeight > KnapsackProblem.LIMIT || totalVolume > KnapsackProblem.LIMIT) {
            fillRandomVelocities();
            return 0;
        } else {
            if (totalValue > globalBestValue) {
                globalVolume = totalVolume;
                globalWeight = totalWeight;
            }
            return totalValue;
        }
    }

    private void updateBestLocalPositions(double tmpValue) {
        if (tmpValue > localBestValue) {
            // This Particle has better a better position than seen before.
            for (int i = 0; i < packages.length; i++) {
                bestLocalPositions[i] = positions[i];
                localBestValue = tmpValue;
            }
        }

    }

    private void fillRandomVelocities() {
        for (int i = 0; i < velocities.length; i++) {
            velocities[i] = random.nextDouble() - 5; // Takes small percentage of the packages
        }
    }

    public void update() {
        updatePosition();
        updateVelocity();

        double tmpValue = calculateValue(positions);
        updateBestLocalPositions(tmpValue);
        updateGlobals(tmpValue);
        w = Math.max(w * 0.991, 0.4);
    }

    private void updateGlobals(double tmpValue) {
        if (tmpValue > globalBestValue) {
            // This Particle has better a better position than all have seen before.
            for (int i = 0; i < positions.length; i++) {
                globalBestPositions[i] = positions[i];
                globalBestValue = tmpValue;
            }
        }
    }

    private void updateVelocity() {
        for (int i = 0; i < velocities.length; i++) {

            double inertia = w * velocities[i];
            double memory = c1 * random.nextDouble() * (velocities[i] - bestLocalPositions[i]);
            double influence = c2 * random.nextDouble() * (velocities[i] - globalBestPositions[i]);

            velocities[i] = (inertia + memory + influence);
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

    public double getBestValue() {
        return calculateValue(bestLocalPositions);
    }

    public double getGlobalWeight() {
        return globalWeight;
    }

    public double getGlobalVolume() {
        return globalVolume;
    }
}
