package project3.task3;

public class Update {

    private double globalBest;
    private int iteration;

    public Update(double globalBest, int iteration) {
        this.globalBest = globalBest;
        this.iteration = iteration;
    }

    public String toString() {
        return "["+iteration+","+globalBest+"]";
    }
}
