package project2;

public abstract class State {

    public abstract double evaluate();
    public abstract State copyState();
    public abstract void randomize();

    public abstract int getColor(int node);
    public abstract void setColor(int node, int color);
    public abstract int getNumberOfNodes();
    public abstract int getNumberOfColors();
}
