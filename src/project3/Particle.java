package project3;

public class Particle {

    private double posX;
    private double velocity;

    public Particle(double posX, double velocity) {
        this.posX = posX;
        this.velocity = velocity;
    }

    public double velocity(int t) {
        double inertia = velocity(t-1);
        double memory = c1 * r1 * (myBest(t-1) - position(t-1));
        double influence = c2 * r2 * (globalBest(t-1) - position(t-1));

        return inertia + memory + influence;
    }

    public double myBest(int t) {
        return 0;
    }

    public double globalBest(int t) {
        return 0;
    }

    public double position(int t) {
        return this.posX + velocity(t);
    }
}
