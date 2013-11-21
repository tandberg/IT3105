package project3.task2;

public class IterationUpdate {

    private double update;
    private int iteration;

    public IterationUpdate(double update, int iteration) {
        this.update = update;
        this.iteration = iteration;
    }

    public String toString() {
        return "["+iteration+","+update+"]";
    }
}
