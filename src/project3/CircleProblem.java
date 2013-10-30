package project3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleProblem {

    public static final int NUM_PARTICLES = 100;
    private List<Coordinate> particles;
    private Random random;

    public CircleProblem() {

        particles = new ArrayList<Coordinate>();
        random = new Random();

        initializeParticles();
        System.out.println(particles);
    }

    private void initializeParticles() {
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new Coordinate(random.nextDouble(), random.nextDouble()));
        }
    }

    private double f(double... u) {
        double sum = 0;
        for (int i = 0; i < u.length; i++) {
            sum += Math.pow(u[i], 2);
        }
        return sum;
    }


    public static void main(String[] args) {
        new CircleProblem();
    }
}
