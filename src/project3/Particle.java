package project3;

import java.util.Random;

public class Particle {

    private static double global_best_position = 0;
    private final static double c1 = 1;
    private final static double c2 = 1;

    private Random random;
    private double posX;
    private double velocity;
    private double local_best_position;
    private double prevVelocity;
    private double prevPosition;


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
    }
}
