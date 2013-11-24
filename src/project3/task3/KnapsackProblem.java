package project3.task3;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

    public static final int NUM_DIMENSIONS = 2001;
    public static final int MAX_ITERATIONS = 500;
    public static final int NUM_PARTICLES = 4001;
    public static final int LIMIT = 1000;
    public static double globalBest = 0;
    public static double globalWeight = 0;
    private List<Particle> particles;
    private List<Update> result;

    public KnapsackProblem() {

        result = new ArrayList<Update>();
        particles = new ArrayList<Particle>();
        System.out.println("Initializing packages...");

        initializeParticles();
        System.out.println("Done Initializing");

        solve();
    }

    public static void main(String[] args) {
        new KnapsackProblem();
    }

    private void initializeParticles() {

        List<Package> packs = ReadPackages.readFile();
        Package[] packages = new Package[packs.size()];


        for (int i = 0; i < packages.length; i++) {
            packages[i] = packs.get(i);
        }


        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles.add(new Particle(packages));
        }

    }

    public double fitnessFunction(Particle particle) {
        return particle.getBestValue();
    }

    public void solve() {
        int iterations = 0;
        while (iterations < MAX_ITERATIONS && globalBest < LIMIT) {
            if (iterations % 10 == 0) {
                System.out.println("iteration: " + iterations);
            }

            double temp = 0;
            for (Particle particle : particles) {
                temp = fitnessFunction(particle);

                if (temp > globalBest) {
                    globalBest = temp;
                    globalWeight = particle.getGlobalWeight();
                    System.out.println("Iteration: " + iterations + " globalbest: " + globalBest);
                    System.out.println("Weight: " + globalWeight + "kg");

                }
                particle.update();
            }

            result.add(new Update(globalBest, iterations));
            iterations++;
        }

        System.out.println("Number of iterations: " + iterations);
        System.out.println("Global best: " + globalBest);
        System.out.println("Weight: " + globalWeight + "kg");

        System.out.println("RESULT: [iteration, value]\n" + result);
    }
}
