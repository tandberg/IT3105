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

    private static int convertNodeToInt(char node) {
        return Character.toLowerCase(node) - 'a';
    }

    private static char convertIntToNode(int node) {
        return (char) (node + (int) 'a');
    }

    public static void main(String[] args) {

        boolean[][] matrix = {
                {true, true, false, true, false},
                {true, true, true, false, true},
                {false, true, true, false, false},
                {true, false, false, true, false},
                {false, true, false, false, true}
        };

        GraphColorState state = new GraphColorState(matrix, 3); // init with matrix and 3 colors (K = 3)
        System.out.println(state.isNeighbour('c', 'e'));
        state.setColor('a', 2);
        state.setColor('b', 2);
        state.setColor('c', 2);

        System.out.println(state);
        System.out.println(state.evaluate());
        System.out.println(state.getViolationNodes());
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

    public double evaluate() {
        double eval = 0.0;

        for (int i = 0; i < this.numberOfNodes; i++) {
            List<Character> nodes = this.getNeighbours(convertIntToNode(i));
            for (Character node : nodes) {
                if (this.getColor(node) == getColor(convertIntToNode(i))) {
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
            List<Character> neighbours = this.getNeighbours(convertIntToNode(i));

            for (Character node : neighbours) {
                if (this.getColor(node) == getColor(convertIntToNode(i))) {
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
        return this.colors; // XXX: Hva gjør random (0, 10) => tar den med 10?
    }

    public State copyState() {
        return new GraphColorState(this.matrix, this.colors + 1);
    }

    public void setColor(char nodeName, int color) {
        int node = convertNodeToInt(nodeName);

        if (node > this.numberOfNodes || color > this.colors)
            throw new IllegalArgumentException("SetColor # node given: " + node + ", color: " + color);

        this.coloring[node] = color;
    }

    public void setColor(int node, int color) {
        this.setColor(convertIntToNode(node), color);
    }

    public int getColor(char nodeName) {
        return this.coloring[convertNodeToInt(nodeName)];
    }

    public int getColor(int node) {
        return this.coloring[node];
    }

    public boolean isNeighbour(char node1, char node2) {
        int nodeA = convertNodeToInt(node1);
        int nodeB = convertNodeToInt(node2);

        if (nodeA > this.numberOfNodes || nodeB > this.numberOfNodes)
            throw new IllegalArgumentException("isNeighbour # node1: " + nodeA + " node2: " + nodeB);

        return this.matrix[nodeA][nodeB] || this.matrix[nodeB][nodeA];
    }

    public List<Character> getNeighbours(char nodeName) {
        int node = convertNodeToInt(nodeName);
        List<Character> neighbours = new ArrayList<Character>();

        for (int i = 0; i < this.numberOfNodes; i++) {
            if (this.matrix[node][i] && node != i) {
                neighbours.add(convertIntToNode(i));
            }
        }
        return neighbours;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.numberOfNodes; i++) {
            sb.append(convertIntToNode(i) + " (" + this.getColor(convertIntToNode(i)) + "): " + getNeighbours(convertIntToNode(i)) + "\n");
        }

        return sb.toString();
    }

}
