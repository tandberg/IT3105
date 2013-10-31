package project3;

import java.util.Random;

public class Particle {


    private static double[] globalBestPositions = new double[CircleProblem.NUM_DIMENSIONS];
    private final static double c1 = 1;
    private final static double c2 = 1;
    private Random random;
/*
    private double posX;
    private double velocity;
    private double local_best_position;
    private double prevVelocity;
    private double prevPosition;
    */

    private int dimensions;
    private double[] positions;
    private double[] velocitys;

    private double[] bestLocalPositions;

    public Particle(int n) {
        this.dimensions = n;
        positions = new double[n];
        velocitys = new double[n];
        bestLocalPositions = new double[n];

        random = new Random();
    }

    public void update() {
        updatePosition();
        updateVelocity();
    }

    private void updateVelocity() {
        for (int i = 0; i < velocitys.length; i++) {
            double inertia = velocitys[i];
            double memory = c1 * random.nextDouble() * (bestLocalPositions[i] - velocitys[i]);
            double influence = c2 * random.nextDouble() * (globalBestPositions[i] - velocitys[i]);

            velocitys[i] = inertia + memory + influence;
        }
    }

    private void updatePosition() {
        for (int i = 0; i < positions.length; i++) {
            positions[i] = positions[i] + velocitys[i];
        }
    }

    /*
    public Particle(double posX, double velocity) {
        this.random = new Random();
        this.posX = posX;
        this.velocity = velocity;

        this.local_best_position = 0;

        this.prevVelocity = this.random.nextDouble();
        this.prevPosition = this.random.nextDouble();
    }

    public void updateVelocity() {
        double inertia = this.velocity;
        double memory = c1 * this.random.nextDouble() * (this.local_best_position - this.velocity);
        double influence = c2 * this.random.nextDouble() * (global_best_position - this.velocity);

        this.velocity = inertia + memory + influence;
    }

    public void updatePosition() {
        this.posX = this.prevPosition + this.velocity;
    }

    public void update() {
        updatePosition();
        updateVelocity();
    }

    public String toString() {
        return "pos: " + this.posX + " velocity: " + this.velocity;
    }*/
}
