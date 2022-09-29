package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
  protected int x;
  protected int y;
  protected Image img;

  public Entity() {

  }

  public Entity(int x_unit, int y_unit, Image img) {
    this.x = x_unit * Sprite.IN_GAME_UNIT;
    this.y = y_unit * Sprite.IN_GAME_UNIT;
    this.img = img;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Image getImg() {
    return img;
  }

  public void setImg(Image img) {
    this.img = img;
  }

  public void render(GraphicsContext gc) { // Using to render the frame of the game
    gc.drawImage(img, x, y);
  }

  public abstract void update();
}
