package project2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FutoshikiState extends State {

    public int[][] matrix;
    public Set<String> constraints2;

    public FutoshikiState(int k) {
        this.matrix = new int[k][k];
        //this.constraints = new ArrayList<Constraint>();
        this.constraints2 = new HashSet<String>();

    }

    public void addConstraint(int ax, int ay, int bx, int by) {
        //this.constraints.add(new Constraint(new Field(ax, ay), new Field(bx, by)));

        constraints2.add(ax+"."+ay+">"+bx+"."+by);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {

                sb.append("" + matrix[i][j] + " . ");

            }

            sb.deleteCharAt(sb.length()-2);
            sb.append("\n");

            for (int j = 0; j < matrix.length; j++) {
                System.out.println(""+i+"."+j+">"+(i-1)+"."+j);
                if(constraints2.contains(""+i+"."+j+">"+(i+1)+"."+j)) {
                    sb.append("V");
                }
                else if(constraints2.contains(""+i+"."+j+">"+(i)+"."+(j-1))) {
                    sb.append("^");
                }

                else {
                    sb.append(".");
                }

                sb.append(" . ");

            }

            sb.deleteCharAt(sb.length()-2);
            sb.append("\n");
        }
        System.out.println(constraints2);
        return sb.toString();
    }

    public static void main(String[] args) {
        FutoshikiState state = new FutoshikiState(5);

        state.addConstraint(0, 0, 1, 0);
        state.addConstraint(2, 0, 1, 2);

        // '0.2>0.3' set som holder på dessa

        System.out.println(state);
    }

    @Override
    public double evaluate() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public State copyState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void randomize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void moveRandom() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
