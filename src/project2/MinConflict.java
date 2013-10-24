package project2;

public class MinConflict implements Algorithm {

    public final static int MAX_ITERATIONS = 1000000;
    private static MinConflict ma = null;

    private MinConflict() {
    }

    public static MinConflict getInstance() {
        if (ma == null)
            return new MinConflict();
        return ma;
    }

    @Override
    public void solve(LocalStateManager manager) {
        int i = 0;

        while (i < MAX_ITERATIONS) {
            double evaluation = manager.evaluate();
            if (evaluation == 0) {
                break;
            }
            manager.modifyIntelligentState();
            i++;

            if (i % 10000 == 0) {
                System.out.println("Iteration: " + i + ", Conflicts: " + evaluation);
            }
        }
        ReportNumbers.endEvals.add(manager.evaluate());
        System.out.println("MC Iterations: " + i);
    }
}
