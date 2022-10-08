package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Oneal extends Enemy {

  public Oneal(int rx, int ry, Image img, boolean isALive, String direction) {
    super(rx, ry, img, isALive, direction);
  }

  @Override
  public void move() {
    if (isAlive) {
      long temp = System.currentTimeMillis();

      if (temp - lastMoveTime >= ENEMY_DELAY) {
        lastMoveTime = temp;

        // Return lại chỗ tọa độ X để tránh việc di chuyển được 1 cả tọa độ X lẫn Y trong 1 lần
        // Nếu di chuyển cả X và Y trong 1 lần thì sẽ bị di chuyển chéo
        // Phải đảm bảo enemy phải di chuyển được, không bị hiện tượng đứng im

        if (bomberman.getX() < this.x) {
          // Nếu di chuyển bên trái thành công thì dừng
          if (this.moveLeft(Sprite.oneal_left3.getFxImage(), DEFAULT_ENEMY_SPEED))
            return;
        } else if (bomberman.getX() > this.x) {
          // Nếu di chuyển bên phải thành công thì dừng
          if (this.moveRight(Sprite.oneal_right3.getFxImage(), DEFAULT_ENEMY_SPEED))
            return;
        }

        // If enemy x = bomber x
        if (bomberman.getY() > this.y) {
          this.moveDown(null, DEFAULT_ENEMY_SPEED);
        } else if (bomberman.getY() < this.y) {
          this.moveUp(null, DEFAULT_ENEMY_SPEED);
        }
      }
    }
  }

  @Override
  public void destroyed() {

  }
}
