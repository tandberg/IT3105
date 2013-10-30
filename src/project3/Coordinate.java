package project3;

public class Coordinate {
    public final double x, y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "X: " + this.x + " Y: " + this.y;
    }
}
