package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.objId;

public abstract class Enemy extends Entity {
    protected static final long ENEMY_DELAY = 1000;
    protected static final int DEFAULT_ENEMY_SPEED = 32;
    protected int rx;
    protected int ry;
    protected boolean isALive;
    protected String direction;

    protected long lastMoveTime = 0;

    public Enemy(int xUnit, int yUnit, Image img, int rx, int ry, boolean isALive, String direction) {
        super(xUnit, yUnit, img);
        this.rx = rx;
        this.ry = ry;
        this.isALive = isALive;
        this.direction = direction;
    }

    public int getRx() {
        return rx;
    }

    public void setRx(int rx) {
        this.rx = rx;
    }

    public int getRy() {
        return ry;
    }

    public void setRy(int ry) {
        this.ry = ry;
    }

    public abstract void move();

    public void moveUp() {
        if (objId[rx][ry - 1] == 0) {
            y -= DEFAULT_ENEMY_SPEED;
            ry--;
        }
    }

    public void moveDown() {
        if (objId[rx][ry + 1] == 0) {
            y += DEFAULT_ENEMY_SPEED;
            ry++;
        }
    }

    public void moveLeft(Image enemyLeftImage) {
        if (objId[rx - 1][ry] == 0) {
            x -= DEFAULT_ENEMY_SPEED;
            rx--;
        }
        if (!direction.equals("left")) {
            this.img = enemyLeftImage;
        }
    }

    public void moveRight(Image enemyRightImage) {
        if (objId[rx + 1][ry] == 0) {
            x += DEFAULT_ENEMY_SPEED;
            rx++;
        }
        if (!direction.equals("right")) {
            this.img = enemyRightImage;
        }
    }

    public void killBomber() {
        if (bomberman.getRx() == this.getRx() && bomberman.getRy() == this.getRy()) {
            bomberman.killedByEnemy();
            bomberman.setAlive(false);
        }
    }

    public abstract void destroyed();

    @Override
    public void update() {
        killBomber();
    }
}
