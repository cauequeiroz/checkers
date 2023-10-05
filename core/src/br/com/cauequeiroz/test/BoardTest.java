package br.com.cauequeiroz.test;

import br.com.cauequeiroz.model.Board;
import br.com.cauequeiroz.model.MovingStone;
import br.com.cauequeiroz.model.Stone;
import br.com.cauequeiroz.model.StoneType;

import java.awt.*;

public class BoardTest {
    public static void main(String[] args) {
        System.out.println("Board test.");

        shouldInitializeABoardWith64Tiles();
        shouldInsertStones();
        shouldRemoveStones();
        shouldCheckIfHasStone();

        shouldTakeAStoneToMove();
        shouldLeaveAStoneAfterMove();

        Board board = new Board();

        System.out.println(board.getHighlightedPositions().contains(new Point(0, 0)));
    }

    private static void shouldTakeAStoneToMove() {
        Board board = new Board();
        Stone stone = new Stone(StoneType.BLACK);

        board.insertStone(stone, 0, 0);
        board.takeStone(0, 0);

        MovingStone movingStone = board.getMovingStone();

        if (
            board.getStone(0, 0) == null &&
            movingStone.getStone().equals(stone) &&
            movingStone.getStartPosition().x == 0 &&
            movingStone.getStartPosition().y == 0
        ) {
            System.out.println("shouldTakeAStoneToMove: PASS");
        } else {
            System.out.println("shouldTakeAStoneToMove: FAIL");
        }
    }
    private static void shouldLeaveAStoneAfterMove() {
        Board board = new Board();
        Stone stone = new Stone(StoneType.BLACK);

        board.insertStone(stone, 0, 0);
        board.takeStone(0, 0);
        board.leaveStone(1, 1);

        if (board.getStone(1, 1).equals(stone) && board.getMovingStone() == null) {
            System.out.println("shouldLeaveAStoneAfterMove: PASS");
        } else {
            System.out.println("shouldLeaveAStoneAfterMove: FAIL");
        }
    }

    private static void shouldInitializeABoardWith64Tiles() {
        Board board = new Board();

        if (board.getStones().size() == 8 && board.getStones().get(0).size() == 8) {
            System.out.println("shouldInitializeABoardWith64Tiles: PASS");
        } else {
            System.out.println("shouldInitializeABoardWith64Tiles: FAIL");
        }
    }

    private static void shouldInsertStones() {
        Board board = new Board();
        Stone stone = new Stone(StoneType.BLACK);

        board.insertStone(stone, 0, 0);

        if (board.getStone(0, 0).equals(stone)) {
            System.out.println("shouldInsertStones: PASS");
        } else {
            System.out.println("shouldInsertStones: FAIL");
        }
    }

    private static void shouldRemoveStones() {
        Board board = new Board();
        Stone stone = new Stone(StoneType.BLACK);

        board.insertStone(stone, 0, 0);
        board.removeStone(0, 0);

        if (board.getStone(0, 0) == null) {
            System.out.println("shouldRemoveStones: PASS");
        } else {
            System.out.println("shouldRemoveStones: FAIL");
        }
    }

    private static void shouldCheckIfHasStone() {
        Board board = new Board();
        Stone stone = new Stone(StoneType.BLACK);

        board.insertStone(stone, 0, 0);

        if (board.hasStone(0, 0) && !board.hasStone(1, 1)) {
            System.out.println("shouldCheckIfHasStone: PASS");
        } else {
            System.out.println("shouldCheckIfHasStone: FAIL");
        }
    }
}
