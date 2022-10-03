package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.BombermanGame.*;

public class Ballom extends Enemy {

  public Ballom(int xUnit, int yUnit, Image img, int rx, int ry, boolean isALive,
      String direction) {
    super(xUnit, yUnit, img, rx, ry, isALive, direction);
  }

  @Override
  public void move() {
    long temp = System.currentTimeMillis();

    if (temp - lastMoveTime >= ENEMY_DELAY) {
      lastMoveTime = temp;
      Random random = new Random();
      int randomDirection = random.nextInt(4);
      switch (randomDirection) {
        case 0:
          this.moveUp();
          break;
        case 1:
          this.moveDown();
          break;
        case 2:
          this.moveLeft(Sprite.balloom_left3.getFxImage());
          break;
        case 3:
          this.moveRight(Sprite.balloom_right3.getFxImage());
          break;
      }
    }
  }

  @Override
  public void destroyed() {

  }
}
