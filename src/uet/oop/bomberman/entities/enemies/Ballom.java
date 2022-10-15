package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ballom extends Enemy {
  private static final Random random = new Random();
  private int lastRandomMove = -1;

  public Ballom(int rx, int ry, Image img, boolean isALive, String direction) {
    super(rx, ry, img, isALive, direction);
  }

  @Override
  public void move() {
    if (isAlive) {
      long temp = System.currentTimeMillis();

      if (temp - lastMoveTime >= ENEMY_DELAY) {
        lastMoveTime = temp;

        int randomDirection;
        do {
          randomDirection = random.nextInt(4);
        } while (randomDirection == lastRandomMove);
        lastRandomMove = randomDirection;

        switch (randomDirection) {
          case 0:
            this.moveUp(null, DEFAULT_ENEMY_SPEED);
            break;
          case 1:
            this.moveDown(null, DEFAULT_ENEMY_SPEED);
            break;
          case 2:
            this.moveLeft(Sprite.balloom_left3.getFxImage(), DEFAULT_ENEMY_SPEED);
            break;
          case 3:
            this.moveRight(Sprite.balloom_right3.getFxImage(), DEFAULT_ENEMY_SPEED);
            break;
        }
      }
    }
  }

  @Override
  public void destroyed() {

  }
}
