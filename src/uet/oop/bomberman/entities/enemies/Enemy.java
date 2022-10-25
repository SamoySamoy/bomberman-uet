package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;

import static uet.oop.bomberman.BombermanGame.*;

public abstract class Enemy extends MovableEntity {
    public static final int DEFAULT_ENEMY_SPEED = 4;

    public Enemy(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.speed = DEFAULT_ENEMY_SPEED;
    }

    public abstract void move();

    public void killBomber() {
        if (bomberman.getRx() == this.getRx() && bomberman.getRy() == this.getRy()) {
            bomberman.killedByEnemy();
            bomberman.setAlive(false);
        }
    }

    @Override
    public void killedByBomb() {
        for (Bomb bomb : bombs) {
            if (bomb.isFinal()) {
                if (bomb.getRx() == this.rx && bomb.getRy() == this.ry) {
                    this.setAlive(false);
                }
            }
        }
    }

    public abstract void destroyed();

    @Override
    public void update() {
        killedByBomb();
        killBomber();
        move();
    }
}
