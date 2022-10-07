package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
  private static final long bombLastTime = 2200;
  private static final long uiLastTime = 160;
  private int rx;
  private int ry;
  private long bombStartTime;
  public boolean isExploded;
  private int animationTransform = 1;
  private int countTransform = 0;
  public boolean ui;

  public Bomb(int rx, int ry, Image img, boolean isExploded, boolean ui) {
    super(rx, ry, img);
    this.rx = rx;
    this.ry = ry;
    this.isExploded = isExploded;
    this.ui = ui;
    this.bombStartTime = System.currentTimeMillis();
  }

  public void explosion() {
    if (countTransform % 10 == 0) {
      if (animationTransform == 1) {
        this.img = Sprite.bomb_exploded.getFxImage();
        animationTransform = 2;
        Bomb left =
            new Bomb(rx - 1, ry, Sprite.explosion_horizontal_left_last.getFxImage(), false, true);
        Bomb right =
            new Bomb(rx + 1, ry, Sprite.explosion_horizontal_right_last.getFxImage(), false, true);
        Bomb up =
            new Bomb(rx, ry - 1, Sprite.explosion_vertical_top_last.getFxImage(), false, true);
        Bomb down =
            new Bomb(rx, ry + 1, Sprite.explosion_vertical_down_last.getFxImage(), false, true);
        bombs.add(left);
        bombs.add(right);
        bombs.add(up);
        bombs.add(down);
      } else if (animationTransform == 2) {
        this.img = Sprite.bomb_exploded1.getFxImage();
        animationTransform = 3;
        Bomb left =
            new Bomb(rx - 1, ry, Sprite.explosion_horizontal_left_last1.getFxImage(), false, true);
        Bomb right =
            new Bomb(rx + 1, ry, Sprite.explosion_horizontal_right_last1.getFxImage(), false, true);
        Bomb up =
            new Bomb(rx, ry - 1, Sprite.explosion_vertical_top_last1.getFxImage(), false, true);
        Bomb down =
            new Bomb(rx, ry + 1, Sprite.explosion_vertical_down_last1.getFxImage(), false, true);
        bombs.add(left);
        bombs.add(right);
        bombs.add(up);
        bombs.add(down);
      } else if (animationTransform == 3) {
        this.img = Sprite.bomb_exploded2.getFxImage();
        animationTransform = 4;
        Bomb left =
            new Bomb(rx - 1, ry, Sprite.explosion_horizontal_left_last2.getFxImage(), false, true);
        Bomb right =
            new Bomb(rx + 1, ry, Sprite.explosion_horizontal_right_last2.getFxImage(), false, true);
        Bomb up =
            new Bomb(rx, ry - 1, Sprite.explosion_vertical_top_last2.getFxImage(), false, true);
        Bomb down =
            new Bomb(rx, ry + 1, Sprite.explosion_vertical_down_last2.getFxImage(), false, true);
        bombs.add(left);
        bombs.add(right);
        bombs.add(up);
        bombs.add(down);
      } else {
        this.isExploded = true;
      }
    }
  }

  @Override
  public void update() {
    if (!ui) {
      if (System.currentTimeMillis() - this.bombStartTime > bombLastTime) {
        countTransform++;
        explosion();
      }
    } else {
      if (System.currentTimeMillis() - this.bombStartTime > uiLastTime) {
        isExploded = true;
      }
    }
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

  public long getBombStartTime() {
    return bombStartTime;
  }

  public void setBombStartTime(long bombStartTime) {
    this.bombStartTime = bombStartTime;
  }

  public boolean isExploded() {
    return isExploded;
  }

  public void setExploded(boolean exploded) {
    isExploded = exploded;
  }
}
