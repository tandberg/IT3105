package project2;

import java.util.Scanner;

public class GeneralPuzzleSolver {


    public static final String game1 = "KQueens";
    public static final String game2 = "GraphColor";
    public static final String game3 = "Futoshiki";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LocalStateManager manager;
        Algorithm algorithm;

        while (true) {
            System.out.println("Select the game you want to play:");
            System.out.println("[1] " + game1);
            System.out.println("[2] " + game2);
            System.out.println("[3] " + game3);
            int game = sc.nextInt();

            if (game < 1 || game > 3) {
                System.out.println("Wrong input, try again!");
                continue;
            }

            System.out.println("Select the difficulty for the game:");
            System.out.println("[1] Easy");
            System.out.println("[2] Medium");
            System.out.println("[3] Hard");

            int difficulty = sc.nextInt();

            if (difficulty < 1 || difficulty > 3) {
                System.out.println("Wrong input, try again!");
                continue;
            }

            System.out.println("Select the algorithm:");
            System.out.println("[1] Simulated Annealing");
            System.out.println("[2] Min Conflicts");

            int algorithmType = sc.nextInt();

            if (algorithmType < 1 || algorithmType > 2) {
                System.out.println("Wrong input, try again!");
                continue;
            }


            switch (game) {
                case 1:
                    manager = new KQueensStateManager(difficulty);
                    break;
                case 2:
                    manager = new GraphColorStateManager(difficulty);
                    break;
                case 3:
                    manager = new FutoshikiStateManager(difficulty);
                    break;
                default:
                    continue;
            }


            if (algorithmType == 1) {
                algorithm = SimulatedAnnealing.getInstance();
            } else {
                algorithm = MinConflict.getInstance();
            }
            long startTime = System.currentTimeMillis();
            System.out.println("Starting... \n");
            algorithm.solve(manager);

            System.out.println(manager);
            System.out.println("Run time: " + (System.currentTimeMillis() - startTime) + "ms");

            System.out.println("Want to run another problem? [y/n]");

            if (!sc.next().toUpperCase().equals("Y")) {
                break;
            }

        }


    }

}
