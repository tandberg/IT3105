package project3.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CircleProblem {

    public static final int NUM_PARTICLES = 100;
    public static final int NUM_DIMENSIONS = 2;
    public static final int MAX_ITERATIONS = 100;
    public static final int LIMIT = 100;
    public static final double GOAL = 0.001;
    public static final int K = 3;
    private List<Particle> particles;
    private double globalBest = Double.MAX_VALUE;

    public CircleProblem() {

        particles = new ArrayList<Particle>();
        initializeParticles();
        System.out.println(particles);
        solve();
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

    public double fitnessFunction(double[] positions) {
        double value = 0;

        for (int i = 0; i < positions.length; i++) {
            value += Math.pow(positions[i], 2);
        }

        return value;
    }

    public void solve() {
        int iterations = 0;
        while (iterations < MAX_ITERATIONS && globalBest > GOAL) {

            System.out.println("Iteration: " + iterations + " globalbest: " + globalBest);


            double temp = fitnessFunction(Particle.globalBestPositions);

//            System.out.println("avg: " + temp);

            if (temp < globalBest) {
                globalBest = temp;
            }


            for (Particle particle : particles) {
                particle.update();
            }
//            System.out.println(globalBest);
            iterations++;
        }

        System.out.println("Number of iterations: " + iterations);
        System.out.println("Global best: " + globalBest);


        System.out.print("JSON:\n[");
        for (Particle p : particles) {
            System.out.print(p.toJSON() + ",");
        }
        System.out.println("];");

        System.out.println("globals" + Arrays.toString(Particle.globalBestPositions));

        System.out.println(particles);

    }
}