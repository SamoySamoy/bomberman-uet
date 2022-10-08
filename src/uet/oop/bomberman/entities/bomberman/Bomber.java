package uet.oop.bomberman.entities.bomberman;

import javafx.scene.image.Image;

import java.util.*;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends MovableEntity {
  private static final long putBombPeriod = 2300;
  private int animationTransform = 1;
  private int countTransform = 0;
  private long lastPutBomb = 0;

  public static final int DEFAULT_SPEED = 32;

  public Bomber(int rx, int ry, Image img, int animationTransform, boolean isAlive,
      String direction) {
    super(rx, ry, img, isAlive, direction);
  }

  public void move() {}

  // keyboard event
  public void handleEventPress(KeyEvent e) {
    switch (e.getCode()) {
      case W:
      case UP:
        this.moveUp(Sprite.player_up.getFxImage(), DEFAULT_SPEED);
        break;
      case S:
      case DOWN:
        this.moveDown(Sprite.player_down.getFxImage(), DEFAULT_SPEED);
        break;
      case A:
      case LEFT:
        this.moveLeft(Sprite.player_left.getFxImage(), DEFAULT_SPEED);
        break;
      case D:
      case RIGHT:
        this.moveRight(Sprite.player_right.getFxImage(), DEFAULT_SPEED);
        break;
      case SPACE:
        this.putBomb();
        break;
    }
  }

  private void putBomb() {
    long now = System.currentTimeMillis();
    if (now - lastPutBomb > putBombPeriod) {
      lastPutBomb = now;
      // put bomb
      System.out.printf("x: %d, y: %d, rx: %d, ry: %d\n", this.x, this.y, this.rx, this.ry);
      Bomb bomb = new Bomb(this.rx, this.ry, Sprite.bomb.getFxImage(), false, false);
      bombs.add(bomb);
    }
  }


  @Override
  public void killedByBomb() {
    for (Bomb bomb : bombs) {
      if (bomb.isFinal()) {
        if (bomb.getRx() == this.rx && bomb.getRy() == this.ry) {
          this.killedByEnemy();
          this.setAlive(false);
        }
      }
    }
  }

  public void killedByEnemy() {
    isStopMoving = true;
    if (!this.isAlive) {
      // transform from dead1 state to dead3 state
      if (countTransform % 25 == 0) {
        if (animationTransform == 1) {
          this.img = Sprite.player_dead1.getFxImage();
          animationTransform = 2;
        } else if (animationTransform == 2) {
          this.img = Sprite.player_dead2.getFxImage();
          animationTransform = 3;
        } else if (animationTransform == 3) {
          this.img = Sprite.player_dead3.getFxImage();
          animationTransform = 4;
        } else {
          isOver = true;
        }
      }
    }

  }

  @Override
  public void update() {
    countTransform++;
    killedByBomb();
  }
}
