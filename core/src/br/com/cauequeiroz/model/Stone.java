package br.com.cauequeiroz.model;

public class Stone {
    private StoneType type;

    public Stone(StoneType type) {
        this.type = type;
    }

    public StoneType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
