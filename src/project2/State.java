package project2;

public abstract class State {

    public abstract double evaluate();

    public abstract State copyState();

    public abstract void randomize();

    public abstract void moveRandom();

    public abstract void moveIntelligent();

}
