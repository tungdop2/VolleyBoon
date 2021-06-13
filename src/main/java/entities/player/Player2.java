package main.java.entities.player;

import main.java.graphics.Sprite;

public class Player2 extends Player{
    public Player2() {
        super(6 * Sprite.SCALED_SIZE + Sprite.SCALE, 4 * Sprite.SCALED_SIZE - Sprite.SCALE, "A", "D", "W");
    }
}
