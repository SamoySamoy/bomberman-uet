package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomber extends Entity {
  public int rx;
  public int ry;

  // Huong di chuyen cua nhan vat
  public String direction;

  public static final int DEFAULT_SPEED = 32;
  public static final int HIGH_SPEED = DEFAULT_SPEED * 2;

  public Bomber(int x, int y, int rx, int ry, Image img) {
    super(x, y, img);
    this.rx = rx;
    this.ry = ry;
    this.direction = "right";
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
        // Thay doi hinh anh cua nhan vat khi di chuyen
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
        // Thay doi hinh anh cua nhan vat khi di chuyen
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
        // Thay doi hinh anh cua nhan vat khi di chuyen
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
        // Thay doi hinh anh cua nhan vat khi di chuyen
        if (!direction.equals("right")) {
          direction = "right";
          this.img = Sprite.player_right.getFxImage();
        }
        break;
    }
  }

  // check collision
  public boolean checkCollision(List<Entity> stillObjects) {
    for (Entity x : stillObjects) {
      if (x instanceof Wall || x instanceof Brick) {
        return checkCollision(x);
      }
    }
    return false;
  }

  private boolean checkCollision(Entity block) {
    // The sides of the rectangles
    int leftA, leftB;
    int rightA, rightB;
    int topA, topB;
    int bottomA, bottomB;

    // Calculate the sides of rect A
    leftA = this.x;
    rightA = this.x + SCALED_SIZE;
    topA = this.y;
    bottomA = this.y + SCALED_SIZE;

    // Calculate the sides of rect B
    leftB = block.x;
    rightB = block.x + SCALED_SIZE;
    topB = block.y;
    bottomB = block.y + SCALED_SIZE;

    // If any of the sides from A are outside of B
    if (bottomA <= topB) {
      return false;
    }

    if (topA >= bottomB) {
      return false;
    }

    if (rightA <= leftB) {
      return false;
    }

    if (leftA >= rightB) {
      return false;
    }

    // If none of the sides from A are outside B
    return true;
  }

  // Move
  public void move() {

  }

  @Override
  public void update() {}
}
