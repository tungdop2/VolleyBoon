package main.java.entities.walls;

import javafx.scene.image.Image;
import main.java.entities.Entity;
import main.java.graphics.Sprite;

public class MiddleWall extends Entity {
    public MiddleWall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall_mid.getFxImage());
    }

    @Override
    public void update() {

    }
}
