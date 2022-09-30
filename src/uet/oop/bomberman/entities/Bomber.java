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
  public int vx;
  public int vy;

  public static final int DEFAULT_SPEED = 32;
  public static final int HIGH_SPEED = DEFAULT_SPEED * 2;

  public Bomber(int x, int y, Image img, int vx, int vy) {
    super(x, y, img);
    velX = 0;
    velY = 0;
    this.vx = vx;
    this.vy = vy;
  }

  // Handle keyboard event
  public void handleEventPress(KeyEvent e) {
    switch (e.getCode()) {
      case W:
        // move up
        // velY = -DEFAULT_SPEED;
        if (objId[vx][vy - 1] == 0) {
          y -= DEFAULT_SPEED;
          vy--;
        }
        break;
      case S:
        // move down
        // velY = DEFAULT_SPEED;
        if (objId[vx][vy + 1] == 0) {
          y += DEFAULT_SPEED;
          vy++;
        }
        break;
      case A:
        // move
        // velX = -DEFAULT_SPEED;
        if (objId[vx - 1][vy] == 0) {
          x -= DEFAULT_SPEED;
          vx--;
        }
        break;
      case D:
        // move up
        // velX = +DEFAULT_SPEED;
        if (objId[vx + 1][vy] == 0) {
          x += DEFAULT_SPEED;
          vx++;
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
