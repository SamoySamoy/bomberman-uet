package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Bomb extends Entity {
  protected int rx;
  protected int ry;
  public boolean isExplode;

  public Bomb(int x, int y, Image img, int rx, int ry, boolean isExplode) {
    super(x, y, img);
    this.rx = rx;
    this.ry = ry;
    this.isExplode = isExplode;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getRx() {
    return this.rx;
  }

  public int getRy() {
    return this.ry;
  }

  public void setRx(int x) {
    this.rx = x;
  }

  public void setRy(int y) {
    this.ry = y;
  }

  public void explosion() {}

  @Override
  public void update() {

  }
}
