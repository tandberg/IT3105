package project3.task3;

import java.util.*;

public class Particle {


    private final static double c1 = 0.7;
    private final static double c2 = 1.4;
    private final static double sigmoidClamping = 5.25;
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
    private double sigmoidOffset;

    private int id;


    public Particle(Package[] packages) {
        int n = packages.length;
        random = new Random();
        positions = new int[n];
        velocities = new double[n];
        bestLocalPositions = new int[n];
        sigmoidOffset = 0;
        this.packages = packages;

        this.id = idCounter++;

        fillRandomVelocities();
        updateGlobals();
    }

    private int mapVelocity(double velocity) {

        double v = velocity;
        double d = (1 / (1 + Math.exp(-v)));

        double rnd = random.nextDouble();
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

        if (totalWeight > KnapsackProblem.LIMIT && updateOffset) {
            // Forkast løsning, siden den er for stor. Finner på en ny 'løsning'
            fillRandomVelocities();
            updateGlobals();
        }

        if (totalWeight > KnapsackProblem.LIMIT) {

            fillRandomVelocities();
            updateGlobals();
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
            velocities[i] = random.nextDouble() - (sigmoidClamping - sigmoidOffset);
        }

        List<Ratio> ratios = new ArrayList<Ratio>();
        for (int i = 0; i < positions.length; i++) {
            if(mapVelocity(velocities[i]) == 1)
                ratios.add(new Ratio(i, packages[i].getValue() / packages[i].getWeight()));
        }

        Collections.sort(ratios);

        double lim = 0;
        for (int i = 0; i < positions.length; i++) {
            positions[i] = 0;
        }

        for (int i = 0; i < ratios.size(); i++) {

            lim += packages[ratios.get(i).index].getWeight();
            positions[ratios.get(i).index] = 1;

            if(lim >= KnapsackProblem.LIMIT) {
                positions[ratios.get(i).index] = 0;
                break;
            }
        }

        sigmoidOffset += 0.005;
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
