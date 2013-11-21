package project3.task4;

import java.util.Arrays;
import java.util.Random;

public class Particle {


    private final static double c1 = 0.3;
    private final static double c2 = 0.4;
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
    private double globalVolume = 0;
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
        updateBestLocalPositions();


        for (int i = 0; i < globalBestPositions.length; i++) {
            globalBestPositions[i] = 0;
        }

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
        double totalVolume = 0;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                totalValue += packages[i].getValue();
                totalWeight += packages[i].getWeight();
                totalVolume += packages[i].getVolume();
            }
        }


//        System.out.println("value: " + totalValue + " weight: " + totalWeight);

        int numpacks = 0;
        for (int i = 0; i < positions.length; i++) {
            numpacks += positions[i];
        }

//        System.out.println("#" +this.id+": Antall pakker med i partikkelen: " + numpacks + " offset: " + offset + " GLOBALBEST: " + KnapsackProblem.globalBest);


        if ((totalWeight > KnapsackProblem.LIMIT || totalVolume > KnapsackProblem.LIMIT) && updateOffset) {
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

        if (totalWeight > KnapsackProblem.LIMIT || totalVolume > KnapsackProblem.LIMIT) {
            return 0;
        } else {
            if (totalValue > globalBestValue) {
                globalVolume = totalVolume;
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
            velocities[i] = random.nextDouble() - 5; // Starts with random speed in any dimensions
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
            double memory = c1 * random.nextDouble() * (velocities[i] - bestLocalPositions[i]);
            double influence = c2 * random.nextDouble() * (velocities[i] - globalBestPositions[i]);


            velocities[i] = (inertia + memory + influence);
//            System.out.println("prev speed: "+  inertia + "\nmemory: " + memory + "\ninfluence: " + influence + "\nNewspeed: "+velocities[i]+"\t\t position: " + positions[i] + " \n----------------------------");


            if (velocities[i] > 4.25) {
                velocities[i] = 4.25;
            } else if (velocities[i] < -4.25) {
                velocities[i] = -4.25;
            }
        }
    }

    private void updatePosition() {
        for (int i = 0; i < packages.length; i++) {
            positions[i] = mapVelocity(velocities[i]);
        }
    }

    public String toString() {
        return "Position: " + Arrays.toString(positions) + " Velocity: " + Arrays.toString(velocities);
    }

    public int[] getPositions() {
        return positions;
    }

    public double getBestValue() {
        return calculateValue(bestLocalPositions, true);
    }

    public void printVelocitys() {
//        System.out.println(Arrays.toString(velocities));
    }

    public double getGlobalWeight() {
        return globalWeight;
    }

    public double getGlobalVolume() {
        return globalVolume;
    }

    public String toJSON() {

        double print1 = (positions[0] < 0.001 && positions[0] > 0) || (positions[0] > -0.001 && positions[0] < 0) ? 0 : positions[0];
        //double print2 = (positions[1] < 0.001 && positions[1] > 0) || (positions[1] > -0.001 && positions[1] < 0) ? 0 : positions[1];

        return "[" + print1 + ",0]"; // tmp
    }

}
