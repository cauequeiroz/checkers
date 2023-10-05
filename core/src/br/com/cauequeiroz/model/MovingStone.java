package br.com.cauequeiroz.model;

import java.awt.*;

public class MovingStone {
    private final Stone stone;
    private final Point startPosition;

    public MovingStone(Stone stone, int startPositionX, int startPositionY) {
        this.stone = stone;
        this.startPosition = new Point(startPositionX, startPositionY);
    }

    public Stone getStone() {
        return stone;
    }

    public Point getStartPosition() {
        return startPosition;
    }
}
