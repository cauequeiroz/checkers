package br.com.cauequeiroz.model;

public enum StoneType {
    BLACK,
    WHITE;

    @Override
    public String toString() {
        switch(this) {
            case BLACK:
                return "B";
            case WHITE:
                return "W";
            default:
                return "";
        }
    }
}
