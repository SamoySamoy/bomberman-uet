package uet.oop.bomberman.entities.bomberman;

import javafx.scene.image.Image;

import java.util.*;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomber extends Entity {
  private boolean isAlive;
  private int rx;
  private int ry;
  private int animationTransform = 1;

  // Huong di chuyen cua nhan vat
  public String direction;

  public static final int DEFAULT_SPEED = 32;
  public static final int HIGH_SPEED = DEFAULT_SPEED * 2;

  public Bomber(int x, int y, int rx, int ry, Image img, String direction) {
    super(x, y, img);
    this.rx = rx;
    this.ry = ry;
    this.direction = direction;
    this.isAlive = true;
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

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public void move() {

  }

  // keyboard event
  public void handleEventPress(KeyEvent e) {
    switch (e.getCode()) {
      case W:
      case UP:
        // move up
        if (objId[rx][ry - 1] == 0) {
          y -= DEFAULT_SPEED;
          ry--;
        }
        // change direction when moving
        if (!direction.equals("up")) {
          direction = "up";
          this.img = Sprite.player_up.getFxImage();
        }
        break;
      case S:
      case DOWN:
        // move down
        if (objId[rx][ry + 1] == 0) {
          y += DEFAULT_SPEED;
          ry++;

        }
        // change direction when moving
        if (!direction.equals("down")) {
          direction = "down";
          this.img = Sprite.player_down.getFxImage();
        }
        break;
      case A:
      case LEFT:
        // move left
        if (objId[rx - 1][ry] == 0) {
          x -= DEFAULT_SPEED;
          rx--;

        }
        // change direction when moving
        if (!direction.equals("left")) {
          direction = "left";
          this.img = Sprite.player_left.getFxImage();
        }
        break;
      case D:
      case RIGHT:
        // move right
        if (objId[rx + 1][ry] == 0) {
          x += DEFAULT_SPEED;
          rx++;

        }
        // change direction when moving
        if (!direction.equals("right")) {
          direction = "right";
          this.img = Sprite.player_right.getFxImage();
        }
        break;
      case SPACE:
        // put bomb
        System.out.println(x + " " + y + " " + rx + " " + ry);
        Entity bomb = new Bomb(x, y, Sprite.bomb.getFxImage(), rx, ry, false);
        bombs.add(bomb);
        break;
    }
  }

  public void killedByEnemy() {
    if (this.isAlive == false) {
      // transform from dead1 state to dead3 state
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

  @Override
  public void update() {

  }
}
