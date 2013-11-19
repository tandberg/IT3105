package project3.task2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNearestNeighbour {

    public static List<Particle> algorithm(List<Particle> all, Particle current, int k) {
        double[] currentPosition = current.getPositions();
        List<Particle> result = new ArrayList<Particle>();
        List<ParticleDistance> neighbourDistances = new ArrayList<ParticleDistance>();

        for (Particle particle : all) {
            double distance = euclidianDistance(currentPosition, particle.getPositions());
            neighbourDistances.add(new ParticleDistance(particle, distance));
        }
        Collections.sort(neighbourDistances);

        for (int i = 0; i < k; i++) {
            result.add(neighbourDistances.get(i).getParticle());
        }

        return result;
    }

    public static double euclidianDistance(double[] a, double[] b) {

        double aSum = 0;
        double bSum = 0;

        for (int i = 0; i < a.length; i++) {
            aSum += Math.pow(a[i], 2);
            bSum += Math.pow(b[i], 2);
        }

        return Math.abs(Math.sqrt(aSum) - Math.sqrt(bSum));
    }
}
