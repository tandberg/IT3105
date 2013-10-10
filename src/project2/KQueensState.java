package project2;

/**
 * Created with IntelliJ IDEA.
 * User: sigurd
 * Date: 10/10/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class KQueensState extends State {

    private int kSize;
    private boolean[][] board;
    private int queensOnBoard;


    public int getCollisions() {

        return 0;
    }


    private boolean checkColumns() {
        for (int i = 0; i < kSize; i++) {
            for (int j = 0; j < kSize; j++) {

            }

        }
        return false;
    }

    private boolean checkRows() {
        for (int i = 0; i < kSize; i++) {

        }
        return false;
    }


}
