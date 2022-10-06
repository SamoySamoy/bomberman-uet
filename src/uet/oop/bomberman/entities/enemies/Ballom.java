package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;

public class Ballom extends Enemy {

    public Ballom(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
    }

    @Override
    public void move() {
        long temp = System.currentTimeMillis();

        if (temp - lastMoveTime >= ENEMY_DELAY) {
            lastMoveTime = temp;
            Random random = new Random();
            int randomDirection = random.nextInt(4);
            switch (randomDirection) {
                case 0:
                    this.moveUp(null);
                    break;
                case 1:
                    this.moveDown(null);
                    break;
                case 2:
                    this.moveLeft(Sprite.balloom_left3.getFxImage());
                    break;
                case 3:
                    this.moveRight(Sprite.balloom_right3.getFxImage());
                    break;
            }
        }
    }

    @Override
    public void destroyed() {

    }
}
