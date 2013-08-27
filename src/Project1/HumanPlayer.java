package Project1;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(Quarto game) {
        super(game);
    }

    @Override
    public void placeBrick(int index) {
        Brick b = game.getBricks().get(index);

        System.out.println("Select row:");
        int row = scanner.nextInt();

        System.out.println("Select column:");
        int column = scanner.nextInt();

        if (!game.setPiece(row, column, index)) {
            System.out.println("You can't place a brick there..");
            placeBrick(index);
        }
    }


    @Override
    public int pickOpponentsBrick() {
        System.out.println("Select brick for opponent:");
        return scanner.nextInt();
    }
}
