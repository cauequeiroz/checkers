package br.com.cauequeiroz.model;

import java.util.LinkedList;

public class Board {
    private LinkedList<LinkedList<Stone>> stones;
    private MovingStone movingStone;
    private LinkedList<Moviment> highlightedPositions;

    public Board() {
        stones = new LinkedList<>();

        for (int row=0; row<8; row++) {
            LinkedList<Stone> columns = new LinkedList<>();

            for (int col=0; col<8; col++) {
                columns.add(null);
            }

            stones.add(columns);
        }

        movingStone = null;
        resetHighlightedPositions();
    }

    public LinkedList<LinkedList<Stone>> getStones() {
        return stones;
    }

    public void insertStone(Stone stone, int x, int y) {
        stones.get(y).set(x, stone);
    }

    public Stone getStone(int x, int y) {
        return stones.get(y).get(x);
    }

    public void removeStone(int x, int y) {
        stones.get(y).set(x, null);
    }

    public boolean hasStone(int x, int y) {
        return stones.get(y).get(x) != null;
    }

    public void takeStone(int x, int y) {
        Stone stone = getStone(x, y);
        removeStone(x, y);

        movingStone = new MovingStone(stone, x, y);
    }

    public void leaveStone(int x, int y) {
        Stone stone = movingStone.getStone();
        insertStone(stone, x, y);

        movingStone = null;
    }

    public MovingStone getMovingStone() {
        return movingStone;
    }

    public void returnMovingStone() {
        leaveStone(movingStone.getStartPosition().x, movingStone.getStartPosition().y);
    }

    public LinkedList<Moviment> getHighlightedPositions() {
        return highlightedPositions;
    }

    public void setHighlightedPositions(LinkedList<Moviment> highlightedPositions) {
        this.highlightedPositions = highlightedPositions;
    }

    public void resetHighlightedPositions() {
        highlightedPositions = new LinkedList<>();
    }
}
