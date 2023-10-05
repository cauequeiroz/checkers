package br.com.cauequeiroz.model;

import java.awt.*;
import java.util.LinkedList;

public class Judge {
    private final Board board;
    private StoneType currentTurn;
    private LinkedList<Moviment> validMoviments;

    public Judge(Board board) {
        this.board = board;
        this.currentTurn = StoneType.WHITE;
    }
    public boolean isAValidStartMoviment(int x, int y) {
        return board.hasStone(x, y) && board.getStone(x, y).getType() == currentTurn;
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

        Point leftCoord;
        Point rightCoord;
        Point leftCoordPlusOne;
        Point rightCoordPlusOne;

        if (currentTurn == StoneType.WHITE) {
            leftCoord = new Point(startPosition.x - 1, startPosition.y - 1);
            rightCoord = new Point(startPosition.x + 1, startPosition.y - 1);
            leftCoordPlusOne = new Point(startPosition.x - 2, startPosition.y - 2);
            rightCoordPlusOne = new Point(startPosition.x + 2, startPosition.y - 2);
        } else {
            leftCoord = new Point(startPosition.x - 1, startPosition.y + 1);
            rightCoord = new Point(startPosition.x + 1, startPosition.y + 1);
            leftCoordPlusOne = new Point(startPosition.x - 2, startPosition.y + 2);
            rightCoordPlusOne = new Point(startPosition.x + 2, startPosition.y + 2);
        }

        // Simple Moviment
        if (isSquareEmpty(leftCoord)) {
            validMoviments.add(new Moviment(leftCoord, null));
        }
        if (isSquareEmpty(rightCoord)) {
            validMoviments.add(new Moviment(rightCoord, null));
        }

        // Capture
        if (isSquareWithEnemy(leftCoord) && isSquareEmpty(leftCoordPlusOne)) {
            validMoviments.add(new Moviment(leftCoordPlusOne, leftCoord));
        }
        if (isSquareWithEnemy(rightCoord) && isSquareEmpty(rightCoordPlusOne)) {
            validMoviments.add(new Moviment(rightCoordPlusOne, rightCoord));
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
        if (currentTurn == StoneType.BLACK) {
            currentTurn = StoneType.WHITE;
        } else {
            currentTurn = StoneType.BLACK;
        }
    }

    private boolean isInsideBoard(Point coord) {
        return coord.x >= 0 && coord.x < 8 && coord.y >= 0 && coord.y < 8;
    }

    private boolean isSquareEmpty(Point coord) {
        return isInsideBoard(coord) && !board.hasStone(coord.x, coord.y);
    }

    private boolean isSquareWithEnemy(Point coord) {
        return isInsideBoard(coord) && board.hasStone(coord.x, coord.y) && board.getStone(coord.x, coord.y).getType() != currentTurn;
    }
}
