package project2;

import java.util.*;

public class GraphColorState extends State {

    private boolean[][] matrix;
    private int numberOfNodes;
    private int colors;
    private int[] coloring;
    private int notMoving;

    public GraphColorState(boolean[][] matrix, int colors) {
        this.matrix = matrix;
        this.numberOfNodes = matrix.length;

        this.colors = colors - 1;
        this.coloring = new int[numberOfNodes];

        notMoving = 0;

    }

    private GraphColorState(boolean[][] matrix, int colors, int[] coloring) {
        this(matrix, colors);
        this.coloring = coloring;
    }

    public void randomize() {
        Random r = new Random();
        for (int i = 0; i < this.coloring.length; i++) {
            coloring[i] = r.nextInt(this.colors + 1);
        }
    }

    @Override
    public void moveRandom() {
        Random random = new Random();
        int node = random.nextInt(this.getNumberOfNodes());
        int color = random.nextInt(this.getNumberOfColors());

        this.setColor(node, color);
    }

    @Override
    public void moveIntelligent() {
        Random random = new Random();
        int node = this.getRandomViolationNode();

        int oldColor = this.getColor(node);


        List<Integer> bestColors = new ArrayList<Integer>();
        double bestEvaluation = Double.MAX_VALUE;

        for (int i = 0; i < this.getNumberOfColors(); i++) {
            this.setColor(node, i);

            if (bestEvaluation > this.evaluate()) {
                bestEvaluation = this.evaluate();
                bestColors.clear();
            }

            if (bestEvaluation == this.evaluate()) {
                bestColors.add(i);
            }
        }

        int newColor = bestColors.get(random.nextInt(bestColors.size()));

        if (oldColor == newColor)
            notMoving++;

        if (notMoving == 10000) {
            notMoving = 0;
            moveRandom();

        }
        this.setColor(node, newColor);
    }

    private int getRandomViolationNode() {
        Random r = new Random();

        while (true) {

            int n = r.nextInt(this.numberOfNodes);

            for (int i = 0; i < this.numberOfNodes; i++) {
                for (Integer integer : this.getNeighbours(n)) {
                    if (getColor(i) == getColor(integer) && i == integer)
                        return i;
                }

            }

        }

    }

    public double evaluate() {
        double eval = 0.0;

        for (int i = 0; i < this.numberOfNodes; i++) {
            List<Integer> nodes = this.getNeighbours(i);
            for (Integer node : nodes) {
                if (this.getColor(node) == getColor(i)) {
                    eval += 1.0;
                }
            }
        }

        return eval / 2.0;
    }

    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    public int getNumberOfColors() {
        return this.colors + 1;
    }

    public State copyState() {

        boolean[][] m = new boolean[this.numberOfNodes][this.numberOfNodes];

        for (int i = 0; i < this.numberOfNodes; i++) {
            for (int j = 0; j < this.numberOfNodes; j++) {
                m[i][j] = this.matrix[i][j];
            }
        }

        int[] c = new int[this.numberOfNodes];
        for (int i = 0; i < this.numberOfNodes; i++) {
            c[i] = this.coloring[i];
        }

        return new GraphColorState(m, this.colors + 1, c);
    }

    public void setColor(int node, int color) {

        if (node > this.numberOfNodes || color > this.colors)
            throw new IllegalArgumentException("SetColor # node given: " + node + ", color: " + color);

        this.coloring[node] = color;
    }

    public int getColor(int node) {
        return this.coloring[node];
    }

    public List<Integer> getNeighbours(int node) {
        List<Integer> neighbours = new ArrayList<Integer>();

        for (int i = 0; i < this.numberOfNodes; i++) {
            if (this.matrix[node][i] && node != i) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.numberOfNodes; i++) {
            sb.append(i + " (color: " + this.getColor(i) + "): " + getNeighbours(i) + "\n");
        }

        sb.append("\ntoJSON:\n" + this.toJSON() + "\n");

        return sb.toString();
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"nodes\":[");

        for (int i = 0; i < numberOfNodes; i++) {
            sb.append("{\"name\":\"" + i + "\",\"group\":" + coloring[i] + "},");
        }

        sb.deleteCharAt(sb.length() - 1);


        sb.append("],\"links\": [");

        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {

                if (matrix[i][j]) {
                    sb.append("{\"source\":" + i + ",\"target\":" + j + ", \"value\":1},");
                }

            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("]}");

        return sb.toString();
    }

}
