package main.java.entities.player;

import javafx.scene.image.Image;
import main.java.Game;
import main.java.entities.Entity;
import main.java.graphics.Sprite;

import java.util.HashSet;

import static java.lang.Integer.max;

public class Player extends Entity {
    public int score = 0;
    String left;
    String right;
    String jump;
    int vx = 0;
    int vy = 0;
    float dx = 0;
    long start_jump;
    public Player(int xUnit, int yUnit, String left, String right, String jump) {
        super(xUnit, yUnit, Sprite.player.getFxImage());
        this.left = left;
        this.right = right;
        this.jump = jump;
    }

    @Override
    public void update() {
//        System.out.println(x +  " " + y);
        HashSet<String> keys = Game.getCurrentlyActiveKeys();

        if (keys.contains(right)) {
            if (dx >= 0) dx += 0.2;
            else dx = 0;
            vx = Sprite.SCALE;
        }
        if (keys.contains(left)) {
            if (dx <= 0) dx -= 0.2;
            else dx = 0;
            vx = -Sprite.SCALE;
        }

        if (keys.isEmpty() || (keys.contains(right) && keys.contains(left))) {
            vx = 0;
            dx = 0;
        }
//        vx += (int) dx;

        for(int i = 0; i < max(vx, 0 - vx); i ++)
        {
            if (vx > 0) {
                right();
            } else {
                left();
            }
        }

        if (keys.contains(jump)) {
            jump();
        }

        if (start_jump != 0) {
            double t = (double) (System.currentTimeMillis() - start_jump) / 1000;
            double s0 = 4.0 * (double) Sprite.SCALED_SIZE - (double) Sprite.SCALE;
            double v0 = 64 * (double) Sprite.SCALE;
            double a = 256 * (double) Sprite.SCALE;
            double yy = s0 - v0 * t + a * t * t / 2;
            if (yy <= s0) {
                y = (int) yy;
                vy = (int) (v0 - a*t);
            }
            else {
                y = 4 * Sprite.SCALED_SIZE - Sprite.SCALE;
                start_jump = 0;
                vy = 0;
            }
        }
    }


    public void left() {
        if (!(x - 1 == 4 * Sprite.SCALE)
            && !(x - 1 == 3 * Sprite.SCALED_SIZE + 10 * Sprite.SCALE)) {
            x --;
        }
        else {
            vx = 0;
            dx = 0;
        }
    }
    public void right() {
        if (!(x + 10 * Sprite.SCALE == 7 * Sprite.SCALED_SIZE - 5 * Sprite.SCALE)
            && !(x + 10 * Sprite.SCALE == 4 * Sprite.SCALED_SIZE - 11 * Sprite.SCALE)) {
            x ++;
        }
        else {
            vx = 0;
            dx = 0;
        }
    }

    public void jump() {
         if (y == 4 * Sprite.SCALED_SIZE - Sprite.SCALE && start_jump == 0) {
             start_jump = System.currentTimeMillis();
         }
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }
}
