package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovableEntity;

import static uet.oop.bomberman.BombermanGame.bomberman;

public abstract class Enemy extends MovableEntity {
  protected static final long ENEMY_DELAY = 1000;
  protected static final int DEFAULT_ENEMY_SPEED = 32;
  protected static final int DEATH_DISTANCE = 48;
  protected long lastMoveTime = 0;

  public Enemy(int rx, int ry, Image img, boolean isALive, String direction) {
    super(rx, ry, img, isALive, direction);
  }

  public abstract void move();


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

  public long getLastMoveTime() {
    return lastMoveTime;
  }

  public void setLastMoveTime(long lastMoveTime) {
    this.lastMoveTime = lastMoveTime;
  }
}
