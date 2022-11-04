package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Sound;

import static uet.oop.bomberman.BombermanGame.*;

public class Kondoria extends Minvo {
  public Kondoria(int rx, int ry, Image img, boolean isALive, String direction) {
    super(rx, ry, img, isALive, direction);
    upImg[0] = Sprite.kondoria_left.getFxImage();
    upImg[1] = Sprite.kondoria_left_1.getFxImage();
    upImg[2] = Sprite.kondoria_left_2.getFxImage();

    downImg[0] = Sprite.kondoria_right.getFxImage();
    downImg[1] = Sprite.kondoria_right_1.getFxImage();
    downImg[2] = Sprite.kondoria_right_2.getFxImage();

    leftImg[0] = Sprite.kondoria_left.getFxImage();
    leftImg[1] = Sprite.kondoria_left_1.getFxImage();
    leftImg[2] = Sprite.kondoria_left_2.getFxImage();

    rightImg[0] = Sprite.kondoria_right.getFxImage();
    rightImg[1] = Sprite.kondoria_right_1.getFxImage();
    rightImg[2] = Sprite.kondoria_right_2.getFxImage();
  }

  // set lai move de co the di chuyen qua bomb
  protected void setMove(String moveDirection) {
    if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
      switch (moveDirection) {
        case "up":
          this.setDirection("up");
          // Kiem tra va cham voi tuong (2) gach (3) bom(2)
          if (objId[rx][ry - 1] != 2 && objId[rx][ry - 1] != 3) {
            // Set so buoc phai di chuyen
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            this.checkRun();
          } else {
            this.img = this.upImg[0];
          }
          break;
        case "down":
          this.setDirection("down");
          // Kiem tra va cham voi tuong (2) gach (3) bom(2)
          if (objId[rx][ry + 1] != 2 && objId[rx][ry + 1] != 3) {
            // Set so buoc phai di chuyen
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            this.checkRun();
          } else {
            this.img = this.downImg[0];
          }
          break;
        case "left":
          this.setDirection("left");
          // Kiem tra va cham voi tuong (2) gach (3) bom(2)
          if (objId[rx - 1][ry] != 2 && objId[rx - 1][ry] != 3) {
            // Set so buoc phai di chuyen
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            this.checkRun();
          } else {
            this.img = this.leftImg[0];
          }
          break;
        case "right":
          this.setDirection("right");
          // Kiem tra va cham voi tuong (2) gach (3) bom(2)
          if (objId[rx + 1][ry] != 2 && objId[rx + 1][ry] != 3) {
            // Set so buoc phai di chuyen
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            this.checkRun();
          } else {
            this.img = this.rightImg[0];
          }
          break;
      }
    }
  }

  private void checkEatBomb() {
    // KT an bom
    if (bombMatix[this.rx - 1][this.ry] == 2) {
      Bomb curBomb = Bomb.getBomb(this.rx - 1, this.ry);
      if (curBomb != null && !curBomb.isExploded() && curBomb.isRaw() == true
          && curBomb.isUi() == false) {
        System.out.println("Pacman eat bomb");
        bombs.remove(curBomb);
        new Sound("sound/eat.wav", "eat");
        bombMatix[this.rx - 1][this.ry] = 0;
        bomberman.gainBombRemain();
      }
    }
    if (bombMatix[this.rx + 1][this.ry] == 2) {
      Bomb curBomb = Bomb.getBomb(this.rx + 1, this.ry);
      if (curBomb != null && !curBomb.isExploded() && curBomb.isRaw() == true
          && curBomb.isUi() == false) {
        System.out.println("Pacman eat bomb");
        bombs.remove(curBomb);
        new Sound("sound/eat.wav", "eat");
        bombMatix[this.rx + 1][this.ry] = 0;
        bomberman.gainBombRemain();
      }
    }
    if (bombMatix[this.rx][this.ry - 1] == 2) {
      Bomb curBomb = Bomb.getBomb(this.rx, this.ry - 1);
      if (curBomb != null && !curBomb.isExploded() && curBomb.isRaw() == true
          && curBomb.isUi() == false) {
        System.out.println("Pacman eat bomb");
        bombs.remove(curBomb);
        new Sound("sound/eat.wav", "eat");
        bombMatix[this.rx][this.ry - 1] = 0;
        bomberman.gainBombRemain();
      }
    }
    if (bombMatix[this.rx][this.ry + 1] == 2) {
      Bomb curBomb = Bomb.getBomb(this.rx, this.ry + 1);
      if (curBomb != null && !curBomb.isExploded() && curBomb.isRaw() == true
          && curBomb.isUi() == false) {
        System.out.println("Pacman eat bomb");
        bombs.remove(curBomb);
        new Sound("sound/eat.wav", "eat");
        bombMatix[this.rx][this.ry + 1] = 0;
        bomberman.gainBombRemain();
      }
    }
  }

  @Override
  public void update() {
    super.update();
    checkEatBomb();
  }
}
