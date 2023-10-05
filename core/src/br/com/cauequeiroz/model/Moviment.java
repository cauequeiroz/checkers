package br.com.cauequeiroz.model;

import java.awt.*;

public class Moviment {
    private Point destinyPosition;
    private Point eatenStonePosition;

    public Moviment(Point destinyPosition, Point eatenStonePosition) {
        this.destinyPosition = destinyPosition;
        this.eatenStonePosition = eatenStonePosition;
    }

    public Point getDestinyPosition() {
        return destinyPosition;
    }

    public Point getEatenStonePosition() {
        return eatenStonePosition;
    }

    @Override
    public String toString() {
        return String.format("To: %s, Taken: %s", getDestinyPosition().toString(), getEatenStonePosition() != null ? getEatenStonePosition().toString() : "null");
    }
}
