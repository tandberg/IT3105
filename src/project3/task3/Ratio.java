package project3.task3;

public class Ratio implements Comparable<Ratio> {

    public int index;
    public double ratio;

    public Ratio(int index, double ratio) {
        this.index = index;
        this.ratio = ratio;
    }


    @Override
    public int compareTo(Ratio tuple) {

        if(this.ratio < tuple.ratio)
            return 1;
        return 0;
    }

    public String toString() {
        return ratio+"";
    }
}
