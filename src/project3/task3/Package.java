package project3.task3;

public class Package {

    private double value, weight;

    public Package(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "value : " + value + " weight : " + weight + "\n";
    }
}
