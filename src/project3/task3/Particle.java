package project3.task3;

import java.util.Arrays;
import java.util.Random;

public class Particle {


    private final static double c1 = 0.7;
    private final static double c2 = 1.4;
    public static int[] globalBestPositions = new int[KnapsackProblem.NUM_DIMENSIONS];
    private static Package[] packages;
    private static int idCounter = 0;
    private Random random;
    private int[] positions;
    private double[] velocities;
    private int[] bestLocalPositions;
    private double localBestValue = 0;
    private double globalBestValue = 0;
    private double globalWeight = 0;
    private int id;


    public Particle(Package[] packages) {
        int n = packages.length;
        random = new Random();
        positions = new int[n];
        velocities = new double[n];
        bestLocalPositions = new int[n];
        this.packages = packages;

        this.id = idCounter++;

        fillRandomVelocities();
        updateGlobals();
    }

    private int mapVelocity(double velocity) {

        double v = velocity;
        double d = (1 / (1 + Math.exp(-v)));

//        return (int) Math.round(d);

        double rnd = random.nextDouble();
//        System.out.println(rnd + " < " + d + " = " + (rnd < d));

        if (rnd < d) {
            return 1;
        }
        return 0;
    }

    private double calculateValue(int[] positions, boolean updateOffset) {

        double totalValue = 0;
        double totalWeight = 0;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                totalValue += packages[i].getValue();
                totalWeight += packages[i].getWeight();
            }
        }


//        System.out.println("value: " + totalValue + " weight: " + totalWeight);

        int numpacks = 0;
        for (int i = 0; i < positions.length; i++) {
            numpacks += positions[i];
        }

//        System.out.println("#" +this.id+": Antall pakker med i partikkelen: " + numpacks + " offset: " + offset + " GLOBALBEST: " + KnapsackProblem.globalBest);


        if (totalWeight > KnapsackProblem.LIMIT && updateOffset) {
            // Forkast løsning, siden den er for stor
//            for (int i = 0; i < velocities.length; i++) {
//                velocities[i] = random.nextDouble() - 5; // Starts with random speed in any dimensions
//
//            }
////
//            updatePosition();
//            updateBestLocalPositions();

            fillRandomVelocities();
        }

        if (totalWeight > KnapsackProblem.LIMIT) {
            return 0;
        } else {
            if (totalValue > globalBestValue) {
                globalWeight = totalWeight;
            }
            return totalValue;
        }
    }

    private void updateBestLocalPositions() {
        double tmpValue = calculateValue(positions, false);

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
            velocities[i] = random.nextDouble() - 5;
        }

        updatePosition();
        updateBestLocalPositions();
    }

    public void update() {
        updatePosition();
        updateVelocity();

        updateBestLocalPositions();
        updateGlobals();
    }

    private void updateGlobals() {
        double tmpValue = calculateValue(positions, false);

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
//            System.out.println("bestlocal: " + bestLocalPositions[i]);
//            System.out.println("bestglobal: " + globalBestPositions[i]);

            double inertia = velocities[i];

            //funker
//            double memory = c1 * random.nextDouble() * (velocities[i] - bestLocalPositions[i]);
//            double influence = c2 * random.nextDouble() * (velocities[i] - globalBestPositions[i]);

            //testing <- denna er riktig, men blir ikke veldig mye bedre enn 210
            double memory = c1 * random.nextDouble() * (bestLocalPositions[i] - positions[i]);
            double influence = c2 * random.nextDouble() * (globalBestPositions[i] - positions[i]);

//            System.out.println("prev speed: "+  inertia + "\nmemory: " + memory + "\ninfluence: " + influence + "\nNewspeed: "+velocities[i]+"\t\t position: " + positions[i] + " \n----------------------------");
            velocities[i] = (inertia + memory + influence);


            if (velocities[i] > 4.25) {
                velocities[i] = 4.25;
            } else if (velocities[i] < -4.25) {
                velocities[i] = -4.25;
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

    public int[] getPositions() {
        return positions;
    }

    public double getGlobalWeight() {
        return globalWeight;
    }

    public double getBestValue() {
        return calculateValue(bestLocalPositions, true);
    }

    public void printVelocitys() {
//        System.out.println(Arrays.toString(velocities));
    }

}
