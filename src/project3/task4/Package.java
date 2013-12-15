package project3.task4;

public class Package {

    private double value, weight, volume;

    public Package(double value, double weight) {
        this.value = value;
        this.weight = weight;
        this.volume = (Math.random() * 100) + 1;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    public String toString() {
        return "value : " + value + " weight : " + weight + "\n";
    }
}
