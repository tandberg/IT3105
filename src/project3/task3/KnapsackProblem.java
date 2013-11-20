package project3.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapsackProblem {

    public static final int NUM_DIMENSIONS = 2001;
    public static final int MAX_ITERATIONS = 100;
    public static final int NUM_PARTICLES = 4001;
    public static final int LIMIT = 1000;
    private List<Particle> particles;
    public static double globalBest = 0;

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

//            System.out.println("Iteration: " + iterations + " globalbest: " + globalBest);


//            System.out.println("avg: " + temp);

            if(iterations % 10 == 0) {
                System.out.println("iteration: " + iterations);
            }


            double temp = 0;

            for (Particle particle : particles) {
                temp = fitnessFunction(particle);

                if (temp > globalBest) {
                    System.out.println("Iteration: " + iterations + " globalbest: " + globalBest);

                    globalBest = temp;
                }

                if(iterations > 50) {
                    particle.printVelocitys();
                }


                particle.update();
            }


            result.add(new Update(globalBest, iterations));
//            System.out.println(globalBest);
            iterations++;
        }

        System.out.println("Number of iterations: " + iterations);
        System.out.println("Global best: " + globalBest);

        System.out.println("RESULT\n" + result);


//        System.out.print("JSON:\n[");
//        for (Particle p : particles) {
//            System.out.print(p.toJSON() + ",");
//        }
//        System.out.println("];");

//        System.out.println("globals" + Arrays.toString(Particle.globalBestPositions));

//        System.out.println(particles);


//        System.out.println(mapVelocity(-0.1));

    }
}