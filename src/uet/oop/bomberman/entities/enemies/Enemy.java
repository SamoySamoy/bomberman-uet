package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.objId;

public abstract class Enemy extends MovableEntity {
    protected static final long ENEMY_DELAY = 1000;
    protected static final int DEFAULT_ENEMY_SPEED = 32;
    protected static final int DEATH_DISTANCE = 48;
    protected boolean isALive;
    protected String direction;
    protected long lastMoveTime = 0;

    public Enemy(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img);
        this.isALive = isALive;
        this.direction = direction;
    }

    public abstract void move();

    public void moveUp(Image enemyUpImage) {
        if (objId[rx][ry - 1] == 0) {
            y -= DEFAULT_ENEMY_SPEED;
            ry--;
        }
        this.direction = "up";
        if (enemyUpImage != null) this.img = enemyUpImage;
    }

    public void moveDown(Image enemyRightImage) {
        if (objId[rx][ry + 1] == 0) {
            y += DEFAULT_ENEMY_SPEED;
            ry++;
        }
        this.direction = "down";
        if (enemyRightImage != null) this.img = enemyRightImage;
    }

    public void moveLeft(Image enemyLeftImage) {
        if (objId[rx - 1][ry] == 0) {
            x -= DEFAULT_ENEMY_SPEED;
            rx--;
        }
        this.direction = "left";
        if (enemyLeftImage != null) this.img = enemyLeftImage;
    }

    public void moveRight(Image enemyRightImage) {
        if (objId[rx + 1][ry] == 0) {
            x += DEFAULT_ENEMY_SPEED;
            rx++;

        }
        this.direction = "right";
        if (enemyRightImage != null) this.img = enemyRightImage;
    }

  /* public boolean checkOpposite(String a, String b) {
    if (a.equals("left") && b.equals("right") || a.equals("right") && b.equals("left")
        || a.equals("up") && b.equals("down") || a.equals("down") && b.equals("up")) {
      return true;
    }
    return false;
  } */

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

    public boolean isALive() {
        return isALive;
    }

    public void setALive(boolean ALive) {
        isALive = ALive;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }
}
