package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.interfaces.Movable;

import static uet.oop.bomberman.BombermanGame.bombMatix;
import static uet.oop.bomberman.BombermanGame.objId;

public abstract class MovableEntity extends Entity implements Movable {
  // cordinates of all objects in a simplify matrix
  protected int rx;
  protected int ry;
  // Hướng di chuyển
  protected String direction;
  protected boolean isAlive;

  public MovableEntity(int rx, int ry, Image img, boolean isAlive, String direction) {
    super(rx, ry, img);
    this.rx = rx;
    this.ry = ry;
    this.direction = direction;
    this.isAlive = isAlive;
  }

  @Override
  public boolean moveUp(Image upImage, int speed) {
    boolean isMoveSuccess = false;
    // Kiểm tra xem nếu phía trước là cỏ thì di chuyển, nếu là bom thì không thể di chuyển
    if (objId[rx][ry - 1] != 2 && objId[rx][ry - 1] != 3 && bombMatix[rx][ry - 1] != 2) {
      y -= speed;
      ry--;
      isMoveSuccess = true;
    }
    this.direction = "up";
    if (upImage != null)
      this.img = upImage;
    return isMoveSuccess;
  }

  @Override
  public boolean moveDown(Image downImage, int speed) {
    boolean isMoveSuccess = false;
    if (objId[rx][ry + 1] != 2 && objId[rx][ry + 1] != 3 && bombMatix[rx][ry + 1] != 2) {
      y += speed;
      ry++;
      isMoveSuccess = true;
    }
    this.direction = "down";
    if (downImage != null)
      this.img = downImage;
    return isMoveSuccess;
  }

  @Override
  public boolean moveLeft(Image leftImage, int speed) {
    boolean isMoveSuccess = false;
    if (objId[rx - 1][ry] != 2 && objId[rx - 1][ry] != 3 && bombMatix[rx - 1][ry] != 2) {
      x -= speed;
      rx--;
      isMoveSuccess = true;
    }
    this.direction = "left";
    if (leftImage != null)
      this.img = leftImage;
    return isMoveSuccess;
  }

  @Override
  public boolean moveRight(Image rightImage, int speed) {
    boolean isMoveSuccess = false;
    if (objId[rx + 1][ry] != 2 && objId[rx + 1][ry] != 3 && bombMatix[rx + 1][ry] != 2) {
      x += speed;
      rx++;
      isMoveSuccess = true;

    }
    this.direction = "right";
    if (rightImage != null)
      this.img = rightImage;
    return isMoveSuccess;
  }

  public abstract void killedByBomb();

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

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public void setAlive(boolean alive) {
    isAlive = alive;
  }

  public void setImage(Image i) {
    this.img = i;
  }
}
