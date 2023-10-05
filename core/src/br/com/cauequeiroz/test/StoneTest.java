package br.com.cauequeiroz.test;

import br.com.cauequeiroz.model.Stone;
import br.com.cauequeiroz.model.StoneType;

public class StoneTest {
    public static void main(String[] args) {
        System.out.println("Stone test.");

        shouldCreateABlackStone();
        shouldCreateAWhiteStone();
    }

    private static void shouldCreateABlackStone() {
        Stone blackStone = new Stone(StoneType.BLACK);

        if (blackStone.getType() == StoneType.BLACK) {
            System.out.println("shouldCreateABlackStone: PASS");
        } else {
            System.out.println("shouldCreateABlackStone: FAIL");
        }
    }

    private static void shouldCreateAWhiteStone() {
        Stone whiteStone = new Stone(StoneType.WHITE);

        if (whiteStone.getType() == StoneType.WHITE) {
            System.out.println("shouldCreateAWhiteStone: PASS");
        } else {
            System.out.println("shouldCreateAWhiteStone: FAIL");
        }
    }
}
