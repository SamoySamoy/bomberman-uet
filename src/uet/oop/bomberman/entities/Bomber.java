package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.scene.input.KeyEvent;
import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Entity {
  public int velX;
  public int velY;
  public int rx;
  public int ry;

  public static final int DEFAULT_SPEED = 32;
  public static final int HIGH_SPEED = DEFAULT_SPEED * 2;

  public Bomber(int x, int y, Image img, int rx, int ry) {
    super(x, y, img);
    velX = 0;
    velY = 0;
    this.rx = rx;
    this.ry = ry;
  }

  // Handle keyboard event
  public void handleEventPress(KeyEvent e) {
    switch (e.getCode()) {
      case W:
        // move up
        // velY = -DEFAULT_SPEED;
        if (objId[rx][ry - 1] == 0) {
          y -= DEFAULT_SPEED;
          ry--;
        }
        break;
      case S:
        // move down
        // velY = DEFAULT_SPEED;
        if (objId[rx][ry + 1] == 0) {
          y += DEFAULT_SPEED;
          ry++;
        }
        break;
      case A:
        // move
        // velX = -DEFAULT_SPEED;
        if (objId[rx - 1][ry] == 0) {
          x -= DEFAULT_SPEED;
          rx--;
        }
        break;
      case D:
        // move up
        // velX = +DEFAULT_SPEED;
        if (objId[rx + 1][ry] == 0) {
          x += DEFAULT_SPEED;
          rx++;
        }
        break;
    }
  }

  public void handleEventRelease(KeyEvent e) {
    switch (e.getCode()) {
      case W:
      case S:
        // velY = 0;
        break;
      case A:
      case D:
        // velX = 0;
        // move
        break;
    }
  }

  // Move
  // public void move() {
  // x += velX;
  // y += velY;
  // }

  @Override
  public void update() {

  }
}
