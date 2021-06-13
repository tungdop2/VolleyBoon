package main.java.entities.ball;

import main.java.Game;
import main.java.entities.Entity;
import main.java.entities.player.Player;
import main.java.graphics.Sprite;

import static java.lang.Math.max;

public class Ball extends Entity {

    long birth_time;
    double x0 = 3 * Sprite.SCALED_SIZE + 6 * Sprite.SCALE;
    double dx = 0;
    double xx = 3 * Sprite.SCALED_SIZE + 6 * Sprite.SCALE;
    double vx = Sprite.SCALE * 24;

    double y0 = 3.0 * (double) Sprite.SCALED_SIZE - 25.0 * (double) Sprite.SCALE;
    double yy = 3.0 * (double) Sprite.SCALED_SIZE - 25.0 * (double) Sprite.SCALE;
    double vy = 48 * (double) Sprite.SCALE;
    double a = 96 * (double) Sprite.SCALE;

    long pre_time;
    double t;

    public Ball(int i) {
        super(3 * Sprite.SCALED_SIZE + 6 * Sprite.SCALE, 3 * Sprite.SCALED_SIZE - Sprite.SCALE * 25, Sprite.ball.getFxImage());
        vx *= i;
        birth_time = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - birth_time <= 3000) {

        }
        else {
            t = (double) (System.currentTimeMillis() - pre_time) / 1000;
            xx += vx * t;
            x = (int) xx;
            yy -= vy * t;
            vy -= a * t;
            y = (int) yy;
            bounce_player();
            bounce_wall();
        }
        pre_time = System.currentTimeMillis();

    }

    public boolean between(int x, int a, int b) {
        return (x - b) * (x + 4 * Sprite.SCALE - 1 - a) <= 0;
    }

    public boolean meetP(Player p) {
        int xx = p.getX();
        int yy = p.getY();
        return (vy < 0
                && (y + 4 * Sprite.SCALE >= yy - 1 && y + 4 * Sprite.SCALE < yy + Sprite.SCALE * 4)
                && between(x, xx, xx + 10 * Sprite.SCALE - 1)
        );
    }

    public void bounce_player() {
        Player p = null;
        if (meetP(Game.getP1())) {
            p = Game.getP1();
        }
        if (meetP(Game.getP2())) {
            p = Game.getP2();
        }

        if (p != null) {
            System.out.println(vx + " " +  vy);
            System.out.println(p.getVx() + " " + p.getVy());
            if (p.getVy() < 0)
                vy = - vy;
            else {
                vy = -vy - p.getVy() / 16;
            }
            int sign = (int) ((int) vx / max(vx, 0 - vx));

            if (p.getVx() != 0) {
                if (p.getVx() * vx > 0) {
                    dx += 20;
                    vx += sign * dx;
                } else {
                    vx = p.getVx() * 50 - sign * dx;
                }
            }
        }
    }

    public void bounce_wall() {
        if ((vx < 0 && x + vx * t <= 5 * Sprite.SCALE)
                || (vx > 0 && x + vx * t + 4 * Sprite.SCALE - 1 >= 7 * Sprite.SCALED_SIZE - 5 * Sprite.SCALE - 1)) {
            vx = -vx;
        }

        else
        if (
                y + - vy * t + 4 * Sprite.SCALE - 1 >= 3 * Sprite.SCALED_SIZE - Sprite.SCALE * 5
                        &&
                        (
                                (
                                        vx > 0
                                                && x  + vx * t + 4 * Sprite.SCALE - 1 >= 3 * Sprite.SCALED_SIZE + 5 * Sprite.SCALE - 1
                                                && x + vx * t < 3 * Sprite.SCALED_SIZE + 5 * Sprite.SCALE - 1
                                )
                                        || (
                                        vx < 0 && x + vx * t <= 3 * Sprite.SCALED_SIZE + 11 * Sprite.SCALE
                                                && x + vx * t + 4 * Sprite.SCALE - 1 > 3 * Sprite.SCALED_SIZE + 11 * Sprite.SCALE
                                )
                        )
        ) {
            vx = -vx;
        }

        else
        if (vy > 0 && y - vy * t <= 5 * Sprite.SCALE + 1) {
            vy = -vy;
        }

        else
        if (vy < 0
                && y - vy * t + 4 * Sprite.SCALE >= 3 * Sprite.SCALED_SIZE - 5 * Sprite.SCALE - 1
                && between((int) (x + vx * t), 3 * Sprite.SCALED_SIZE + 5 * Sprite.SCALE, 3 * Sprite.SCALED_SIZE + 11 * Sprite.SCALE - 1)) {
            vy = -vy;
        }


    }
}
