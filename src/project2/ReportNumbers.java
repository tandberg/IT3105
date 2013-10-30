package project2;

import java.util.ArrayList;
import java.util.List;

public class ReportNumbers {

    public static List<Double> endEvals = new ArrayList<Double>();

    public ReportNumbers() {

        int iterations = 20;

        Algorithm algorithm = MinConflict.getInstance();

        List<Long> times = new ArrayList<Long>();

        for (int i = 1; i <= iterations; i++) {

            LocalStateManager manager = new FutoshikiStateManager(1);
            long start = System.currentTimeMillis();
            algorithm.solve(manager);
            long end = System.currentTimeMillis();

            times.add(end-start);

            System.out.println("\n\n\n\n GAME NUM: " + i +" \n\n\n\n");

        }

        System.out.println("END EVALS ("+endEvals.size()+"): "+ endEvals);
        System.out.println("STD: " + getSTD(endEvals));
        System.out.println("AVG: " + avg(endEvals));

    }

    public static double avg(List<Double> list) {

        double a = 0;

        for (Double aDouble : list) {
            a += aDouble;
        }

        return a / list.size();
    }

    public static double getSTD(List<Double> list) {

        double u = avg(list);
        double e2 = 0;
        for (Double aDouble : list) {
            e2 += aDouble * aDouble;
        }

        e2 /= list.size();

        return Math.sqrt(e2 - (u*u));
    }


    public static void main(String[] args) {
        new ReportNumbers();
    }
}
