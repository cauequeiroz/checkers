package br.com.cauequeiroz.model;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Judge {
    private final Board board;
    private Turn currentTurn;
    private LinkedList<Moviment> validMoviments;

    public Judge(Board board) {
        this.board = board;
        this.currentTurn = Turn.PLAYER_1;
    }
    public boolean isAValidStartMoviment(int x, int y) {
        return board.hasStone(x, y) && isPlayerStone(board.getStone(x, y).getType());
    }

    public boolean isAValidEndMoviment(int x, int y) {
        for (Moviment moviment : validMoviments) {
            Point point = moviment.getDestinyPosition();

            if (point.equals(new Point(x, y))) {
                return true;
            }
        }

        return false;
    }

    public void calculateValidMoviments() {
        validMoviments = new LinkedList<>();
        Point startPosition = board.getMovingStone().getStartPosition();
        Stone stone = board.getMovingStone().getStone();

        Point TOP_LEFT = new Point(startPosition.x - 1, startPosition.y - 1);
        Point TOP_LEFT_X2 = new Point(startPosition.x - 2, startPosition.y - 2);
        Point TOP_RIGHT = new Point(startPosition.x + 1, startPosition.y - 1);
        Point TOP_RIGHT_X2 = new Point(startPosition.x + 2, startPosition.y - 2);
        Point BOTTOM_LEFT = new Point(startPosition.x - 1, startPosition.y + 1);
        Point BOTTOM_LEFT_X2 = new Point(startPosition.x - 2, startPosition.y + 2);
        Point BOTTOM_RIGHT = new Point(startPosition.x + 1, startPosition.y + 1);
        Point BOTTOM_RIGHT_X2 = new Point(startPosition.x + 2, startPosition.y + 2);

        if (Arrays.asList(StoneType.WHITE, StoneType.WHITE_QUEEN, StoneType.BLACK_QUEEN).contains(stone.getType())) {
            // Simple Moviment
            if (isSquareEmpty(TOP_LEFT)) {
                validMoviments.add(new Moviment(TOP_LEFT, null));
            }
            if (isSquareEmpty(TOP_RIGHT)) {
                validMoviments.add(new Moviment(TOP_RIGHT, null));
            }

            // Capture
            if (isSquareWithEnemy(TOP_LEFT) && isSquareEmpty(TOP_LEFT_X2)) {
                validMoviments.add(new Moviment(TOP_LEFT_X2, TOP_LEFT));
            }
            if (isSquareWithEnemy(TOP_RIGHT) && isSquareEmpty(TOP_RIGHT_X2)) {
                validMoviments.add(new Moviment(TOP_RIGHT_X2, TOP_RIGHT));
            }
        }

        if (Arrays.asList(StoneType.BLACK, StoneType.WHITE_QUEEN, StoneType.BLACK_QUEEN).contains(stone.getType())) {
            // Simple Moviment
            if (isSquareEmpty(BOTTOM_LEFT)) {
                validMoviments.add(new Moviment(BOTTOM_LEFT, null));
            }
            if (isSquareEmpty(BOTTOM_RIGHT)) {
                validMoviments.add(new Moviment(BOTTOM_RIGHT, null));
            }

            // Capture
            if (isSquareWithEnemy(BOTTOM_LEFT) && isSquareEmpty(BOTTOM_LEFT_X2)) {
                validMoviments.add(new Moviment(BOTTOM_LEFT_X2, BOTTOM_LEFT));
            }
            if (isSquareWithEnemy(BOTTOM_RIGHT) && isSquareEmpty(BOTTOM_RIGHT_X2)) {
                validMoviments.add(new Moviment(BOTTOM_RIGHT_X2, BOTTOM_RIGHT));
            }
        }
    }

    public LinkedList<Moviment> getValidMoviments() {
        return validMoviments;
    }

    public Moviment getMoviment(int x, int y) {
        for (Moviment moviment : validMoviments) {
            Point point = moviment.getDestinyPosition();

            if (point.equals(new Point(x, y))) {
                return moviment;
            }
        }

        return null;
    }

    public void nextTurn() {
        if (currentTurn == Turn.PLAYER_1) {
            currentTurn = Turn.PLAYER_2;
        } else {
            currentTurn = Turn.PLAYER_1;
        }
    }

    private boolean isInsideBoard(Point coord) {
        return coord.x >= 0 && coord.x < 8 && coord.y >= 0 && coord.y < 8;
    }

    private boolean isSquareEmpty(Point coord) {
        return isInsideBoard(coord) && !board.hasStone(coord.x, coord.y);
    }

    private boolean isSquareWithEnemy(Point coord) {
        return isInsideBoard(coord) && board.hasStone(coord.x, coord.y) && !isPlayerStone(board.getStone(coord.x, coord.y).getType());
    }

    private boolean isPlayerStone(StoneType type) {
        if (currentTurn == Turn.PLAYER_1 && Arrays.asList(StoneType.WHITE, StoneType.WHITE_QUEEN).contains(type)) {
            return true;
        }

        if (currentTurn == Turn.PLAYER_2 && Arrays.asList(StoneType.BLACK, StoneType.BLACK_QUEEN).contains(type)) {
            return true;
        }

        return false;
    }

    public boolean isAPromotion(Point destinyPosition) {
        StoneType type = board.getMovingStone().getStone().getType();

        return (type == StoneType.WHITE && destinyPosition.y == 0) ||
                (type == StoneType.BLACK && destinyPosition.y == 7);
    }
}
