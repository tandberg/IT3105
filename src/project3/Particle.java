package project3;

import java.util.Random;

public class Particle {


    public static double[] globalBestPositions = new double[CircleProblem.NUM_DIMENSIONS];
    private final static double c1 = 1;
    private final static double c2 = 1;
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

        for (int i = 0; i < globalBestPositions.length; i++) {
            globalBestPositions[i] = Double.MAX_VALUE;
        }
    }

    private void updateBestLocalPositions() {
        for (int i = 0; i < bestLocalPositions.length; i++) {
            bestLocalPositions[i] = Math.min(bestLocalPositions[i], positions[i]);
        }
    }

    private void fillRandomVelocities() {
        for (int i = 0; i < velocities.length; i++) {
            velocities[i] = 0; // Start speed at zero in any dimension
        }
    }

    private void fillRandomPositions() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = random.nextDouble() * 100;
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
        for (int i = 0; i < positions.length; i++) {
            globalBestPositions[i] = Math.min(globalBestPositions[i], positions[i]);
        }
    }

    private void updateVelocity() {
        for (int i = 0; i < velocities.length; i++) {
            double inertia = velocities[i];
            double memory = c1 * random.nextDouble() * (bestLocalPositions[i] - velocities[i]);
            double influence = c2 * random.nextDouble() * (globalBestPositions[i] - velocities[i]);

            velocities[i] = inertia + memory + influence;
        }
    }

    private void updatePosition() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = positions[i] + velocities[i];
        }
    }

}
