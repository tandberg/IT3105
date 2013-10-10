package project2;

import java.util.ArrayList;
import java.util.List;

public class GraphColorState extends State {

    private boolean[][] matrix;
    private int numberOfNodes;

	public GraphColorState(boolean[][] matrix, int colors) {
		this.matrix = matrix;
        this.numberOfNodes = matrix.length;
	}

    private static int convertNodeToInt(char node) {
        return Character.toLowerCase(node) - 'a';
    }

    private static char convertIntToNode(int node) {
        return (char) (node + (int)'a');
    }

    public boolean isNeighbour(char node1, char node2) {
        int nodeA = convertNodeToInt(node1);
        int nodeB = convertNodeToInt(node2);

        if(nodeA > this.numberOfNodes || nodeB > this.numberOfNodes)
            throw new IllegalArgumentException();

        return this.matrix[nodeA][nodeB] || this.matrix[nodeB][nodeA];
    }

    public List<Character> getNeighbours(char nodeName) {
        int node = convertNodeToInt(nodeName);
        List<Character> neighbours = new ArrayList<Character>();

        for (int i = 0; i < this.numberOfNodes; i++) {
            if(this.matrix[node][i] && node != i) {
                neighbours.add(convertIntToNode(i));
            }
        }
        return neighbours;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.numberOfNodes; i++) {
            sb.append(convertIntToNode(i) + ": " + getNeighbours(convertIntToNode(i)) + "\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        boolean[][] matrix = {
                {true, true, false, true, false},
                {true, true, true, false, true},
                {false, true, true, false, false},
                {true, false, false, true, false},
                {false, true, false, false, true}
        };

        GraphColorState state = new GraphColorState(matrix, 3);
        System.out.println(state.isNeighbour('d', 'c'));
        System.out.println(state);
    }


}
