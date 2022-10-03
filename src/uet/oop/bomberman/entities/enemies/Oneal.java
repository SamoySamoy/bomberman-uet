package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Oneal extends Enemy {

  public Oneal(int xUnit, int yUnit, Image img, int rx, int ry, boolean isALive, String direction) {
    super(xUnit, yUnit, img, rx, ry, isALive, direction);
  }

  @Override
  public void move() {
    long temp = System.currentTimeMillis();

    if (temp - lastMoveTime >= ENEMY_DELAY) {
      lastMoveTime = temp;

      // Return lại chỗ tọa độ X để tránh việc di chuyển được 1 cả tọa độ X lẫn Y trong 1 lần
      // Nếu di chuyển cả X và Y trong 1 lần thì sẽ bị di chuyển chéo

      if (bomberman.getX() < this.x) {
        this.moveLeft(Sprite.oneal_left3.getFxImage());
        return;
      } else if (bomberman.getX() > this.x) {
        this.moveRight(Sprite.oneal_right3.getFxImage());
        return;
      }
      if (bomberman.getY() > this.y) {
        this.moveDown();
      } else if (bomberman.getY() < this.y) {
        this.moveUp();
      }
    }
  }

  @Override
  public void destroyed() {

  }
}
