package project3.task3;

import java.util.Arrays;
import java.util.Random;

public class Particle {

    private final static double c1 = 0.7;
    private final static double c2 = 1.4;
    private final static double velocityClamping = 4.25;
    public static int[] globalBestPositions = new int[KnapsackProblem.NUM_DIMENSIONS];
    private static Package[] packages;
    private double w = 1;
    private Random random;
    private int[] positions;
    private double[] velocities;
    private int[] bestLocalPositions;
    private double localBestValue = 0;
    private double globalBestValue = 0;
    private double globalWeight = 0;


    public Particle(Package[] packages) {
        int n = packages.length;
        random = new Random();
        positions = new int[n];
        velocities = new double[n];
        bestLocalPositions = new int[n];
        this.packages = packages;

        fillRandomVelocities();
        updateGlobals(calculateValue(positions));
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
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                totalValue += packages[i].getValue();
                totalWeight += packages[i].getWeight();
            }
        }

        if (totalWeight > KnapsackProblem.LIMIT) {
            // Forkast løsning, siden den er for stor. Finner på en ny 'løsning'
            fillRandomVelocities();
            return 0;
        } else {
            if (totalValue > globalBestValue) {
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
            velocities[i] = random.nextDouble() - 5.25;
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
            double memory = c1 * random.nextDouble() * (bestLocalPositions[i] - positions[i]);
            double influence = c2 * random.nextDouble() * (globalBestPositions[i] - positions[i]);

            velocities[i] = (inertia + memory + influence);

            if (velocities[i] > velocityClamping) {
                velocities[i] = velocityClamping;
            } else if (velocities[i] < -velocityClamping) {
                velocities[i] = -velocityClamping;
            }
        }
    }

    private void updatePosition() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = mapVelocity(velocities[i]);
        }
    }

    public String toString() {
        return "Position: " + Arrays.toString(positions) + " Velocity: " + Arrays.toString(velocities);
    }

    public double getGlobalWeight() {
        return globalWeight;
    }

    public double getBestValue() {
        return calculateValue(bestLocalPositions);
    }
}
