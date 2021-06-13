package main.java.entities.walls;

import javafx.scene.image.Image;
import main.java.entities.Entity;
import main.java.graphics.Sprite;

public class VerticalWall extends Entity {

    public VerticalWall(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.wall_ver.getFxImage());
    }

    @Override
    public void update() {

    }
}
