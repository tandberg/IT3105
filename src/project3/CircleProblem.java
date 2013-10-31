package project3;

import java.util.ArrayList;
import java.util.List;

public class CircleProblem {

    public static final int NUM_PARTICLES = 100;
    public static final int NUM_DIMENSIONS = 1;
    private List<Particle> particles;
    private double globalBest = Double.MAX_VALUE;

    public CircleProblem() {

        particles = new ArrayList<Particle>();
        initializeParticles();
        System.out.println(particles);
    }

    public static void main(String[] args) {
        new CircleProblem();
    }

    private void initializeParticles() {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new Particle(NUM_DIMENSIONS));
        }
    }

    private double f(double... u) {
        double sum = 0;
        for (int i = 0; i < u.length; i++) {
            sum += Math.pow(u[i], 2);
        }
        return sum;
    }

    public double fitnessFunction(List<Double> us) {
        double value = 0;
        for (Double u : us) {
            value += (u * u);
        }
        return value;
    }

    public void solve() {
        
    }
}
