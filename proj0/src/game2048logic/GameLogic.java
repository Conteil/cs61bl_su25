package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return minR.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // TODO: Fill this in in tasks 2, 3, 4
//        找到第一个0元素
//        for (int i = 0; i < r; i++) {
//            if (board[i][c] == 0) {
//                board[i][c] = board[r][c];
//                board[r][c] = 0;
//                break;
//            }
//        }
//        找到r前最后一个非0元素
        if (r == 0 || board[r][c] == 0) {
            return minR;
        }
        int count = -1;
        for (int i = 0; i < r; i++) {
            if (board[i][c] != 0) {
                count = i;
            }
        }
        if (count != -1 && board[count][c] == board[r][c]) {
            board[count][c] = 2 * board[count][c];
            board[r][c] = 0;
            return count + 1;
        }else if (count + 1 != r){
            board[count + 1][c] = board[r][c];
            board[r][c] = 0;
            return minR;
        }else {
            return minR;
        }
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // TODO: fill this in in task 5
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.NORTH) {
            return;
        } else if (side == Side.EAST) {
            return;
        } else if (side == Side.SOUTH) {
            return;
        } else if (side == Side.WEST) {
            return;
        } else {
            System.out.println("Invalid side specified");
        }
    }
}
