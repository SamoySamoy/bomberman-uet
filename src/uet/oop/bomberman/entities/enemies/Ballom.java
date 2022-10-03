package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.objId;

public class Ballom extends Enemy {

    public Ballom(int xUnit, int yUnit, Image img, int rx, int ry, boolean isALive, String direction) {
        super(xUnit, yUnit, img, rx, ry, isALive, direction);
    }

    @Override
    public void move() {
        long temp = System.currentTimeMillis();

        if (temp - lastMoveTime >= ENEMY_DELAY) {
            lastMoveTime = temp;
            Random random = new Random();
            int direction = random.nextInt(4);
            switch (direction) {
                case 0:
                    // move up
                    if (objId[rx][ry - 1] == 0) {
                        y -= DEFAULT_ENEMY_SPEED;
                        ry--;
                    }
                    break;
                case 1:
                    // move down
                    if (objId[rx][ry + 1] == 0) {
                        y += DEFAULT_ENEMY_SPEED;
                        ry++;
                    }
                    break;
                case 2:
                    // move left
                    if (objId[rx - 1][ry] == 0) {
                        x -= DEFAULT_ENEMY_SPEED;
                        rx--;
                    }
                    break;
                case 3:
                    // move right
                    if (objId[rx + 1][ry] == 0) {
                        x += DEFAULT_ENEMY_SPEED;
                        rx++;
                    }
                    break;
            }
        }
    }

    @Override
    public void kill() {

    }

    @Override
    public void update() {
        super.update();
    }
}
