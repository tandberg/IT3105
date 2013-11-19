package project3.task2;

public class ParticleDistance implements Comparable<ParticleDistance> {

    private Particle p;
    private double distance;

    public ParticleDistance(Particle p, double distance) {
        this.p = p;
        this.distance = distance;
    }

    public int compareTo(ParticleDistance other) {
        return this.distance > other.getDistance() ? 1 : -1;
    }

    public double getDistance() {
        return this.distance;
    }

    public Particle getParticle() {
        return p;
    }

    public String toString() {
        return distance + " <- \n";
    }
}
