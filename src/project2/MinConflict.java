package project2;

public class MinConflict implements Algorithm {

    public final static int MAX_ITERATIONS = 100000000;
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

            if (i % 10000 == 0 || true) {
                System.out.println("Iteration: " + i + ", Conflicts: " + evaluation);

            }
        }

        System.out.println("MC Iterations: " + i);
    }
}
