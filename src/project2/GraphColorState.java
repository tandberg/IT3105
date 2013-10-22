package project2;

import java.util.*;

public class GraphColorState extends State {

    private boolean[][] matrix;
    private int numberOfNodes;
    private int colors;
    private int[] coloring;

    public GraphColorState(boolean[][] matrix, int colors) {
        this.matrix = matrix;
        this.numberOfNodes = matrix.length;

        this.colors = colors - 1;
        this.coloring = new int[numberOfNodes];

    }

    private GraphColorState(boolean[][] matrix, int colors, int[] coloring) {
        this(matrix, colors);
        this.coloring = coloring;
    }

    public static void main(String[] args) {

        Puzzle puzzle = PredefinedGraphColorStates.getTriforceGraphColorPuzzle();

        GraphColorState state = new GraphColorState(puzzle.matrix, puzzle.colors);
        state.randomize();
        System.out.println(state.toJSON());
        System.out.println("\n\nevaluation: " + state.evaluate());

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

        int node = this.getRandomViolationNode();
        int oldColor = this.getColor(node);
        double[] colorEvals = new double[this.getNumberOfColors()];


        for (int i = 0; i < this.getNumberOfColors(); i++) {

            this.setColor(node, i);
            colorEvals[i] = this.evaluate();
            this.setColor(node, oldColor);
        }

        System.out.println(Arrays.toString(colorEvals));

        double min = Double.MAX_VALUE;
        for (int i = 0; i < this.getNumberOfColors(); i++) {
            if(min > colorEvals[i]) {
                min = colorEvals[i];
                this.setColor(node, i);
            }
        }

    }

    private int getRandomViolationNode() {
        Random r = new Random();

        while(true) {

            int n = r.nextInt(this.numberOfNodes);

            for (int i = 0; i < this.numberOfNodes; i++) {
                for (Integer integer : this.getNeighbours(n)) {
                    if(getColor(i) == getColor(integer))
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

    public Map<Integer, Integer> getViolationNodes() { // Returns <node, numCollisions>
        Map<Integer, Integer> nodes = new HashMap<Integer, Integer>();

        for (int i = 0; i < this.numberOfNodes; i++) {
            int collisions = 0;
            List<Integer> neighbours = this.getNeighbours(i);

            for (Integer node : neighbours) {
                if (this.getColor(node) == getColor(i)) {
                    collisions++;
                }
            }

            if (collisions > 0) {
                nodes.put(i, collisions);
            }
        }

        return nodes;
    }

    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    public int getNumberOfColors() {
        return this.colors + 1; // XXX: Hva gjør random (0, 10) => tar den med 10?
    }

    public State copyState() {
        return new GraphColorState(this.matrix, this.colors + 1, this.coloring);
    }

    public void setColor(int node, int color) {

        if (node > this.numberOfNodes || color > this.colors)
            throw new IllegalArgumentException("SetColor # node given: " + node + ", color: " + color);

        this.coloring[node] = color;
    }

    public int getColor(int node) {
        return this.coloring[node];
    }

    public boolean isNeighbour(int node1, int node2) {

        if (node1 > this.numberOfNodes || node2 > this.numberOfNodes)
            throw new IllegalArgumentException("isNeighbour # node1: " + node1 + " node2: " + node2);

        return this.matrix[node1][node2] || this.matrix[node2][node1];
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

        sb.append("\ntoJSON:\n" + this.toJSON() +"\n");

        return sb.toString();
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"nodes\": [");

        for (int i = 0; i < numberOfNodes; i++) {
            sb.append("{\"name\":\""+i+"\", \"group\": "+coloring[i]+" },");
        }

        sb.deleteCharAt(sb.length()-1);


        sb.append("],\"links\": [");

        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {

                if(matrix[i][j]) {
                    sb.append("{\"source\":" + i + ", \"target\": "+j+", \"value\": 10},");
                }

            }
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("]}");

        return sb.toString();
    }

}
