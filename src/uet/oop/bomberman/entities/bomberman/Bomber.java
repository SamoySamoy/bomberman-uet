package uet.oop.bomberman.entities.bomberman;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends MovableEntity {
  private static final long putBombPeriod = 2300;
  private int animationTransform = 1;
  private int countTransform = 0;
  private long lastPutBomb = 0;
  private int curBombRemain;
  public static final int DEFAULT_SPEED = 32;
  private static final int screenPosX = 10;
  private static final int screenPosY = 5;

  public Bomber(int rx, int ry, Image img, int animationTransform, boolean isAlive,
      String direction) {
    super(rx, ry, img, isAlive, direction);
    this.curBombRemain = 1;
  }

  // keyboard event
  public void handleEventPress(KeyEvent e) {
    switch (e.getCode()) {
      case W:
      case UP:
        this.moveUp(Sprite.player_up.getFxImage(), DEFAULT_SPEED);
        this.checkPickItem();
        this.checkTele();
        break;
      case S:
      case DOWN:
        this.moveDown(Sprite.player_down.getFxImage(), DEFAULT_SPEED);
        this.checkPickItem();
        this.checkTele();
        break;
      case A:
      case LEFT:
        this.moveLeft(Sprite.player_left.getFxImage(), DEFAULT_SPEED);
        this.checkPickItem();
        this.checkTele();
        break;
      case D:
      case RIGHT:
        this.moveRight(Sprite.player_right.getFxImage(), DEFAULT_SPEED);
        this.checkPickItem();
        this.checkTele();
        break;
      case SPACE:
        this.putBomb();
        break;
    }
  }

  private void checkPickItem() {
    if (itemMatrix[this.rx][this.ry] != 0) {
      switch (itemMatrix[this.rx][this.ry]) {
        case 4:
          System.out.println("An duoc speed item");
          break;
        case 5:
          System.out.println("An duoc flame item");
          Bomb.gainBombLevel();
          break;
        case 6:
          System.out.println("An duoc bomb item");
          this.gainBombRemain();
          System.out.println("So bomb hien tai " + this.curBombRemain);
          break;
      }
      for (Item item : items) {
        if (item.getRx() == this.rx && item.getRy() == this.ry)
          item.setPicked(true);
      }
      objId[this.rx][this.ry] = 0;
      itemMatrix[this.rx][this.ry] = 0;
    }
  }

  private void checkTele() {
    if (objId[rx][ry] == 7) {
      int desX = 0;
      int desY = 0;
      for (int i = 0; i < WIDTH; i++) {
        for (int j = 0; j < HEIGHT; j++) {
          if (i != rx && j != ry && objId[i][j] == 7) {
            desX = i;
            desY = j;
            break;
          }
        }
      }
      x = desX * 32;
      y = desY * 32;
      rx = desX;
      ry = desY;
    }
  }

  private void putBomb() {
    // long now = System.currentTimeMillis();
    // if (now - lastPutBomb > putBombPeriod) {
    // lastPutBomb = now;
    // // put bomb
    // System.out.printf("x: %d, y: %d, rx: %d, ry: %d\n", this.x, this.y, this.rx, this.ry);
    // Bomb bomb = new Bomb(this.rx, this.ry, Sprite.bomb.getFxImage(), false, false);
    // new Sound("sound/put_bombs.wav", "putBomb");
    // bombs.add(bomb);
    // }
    System.out.println("Truoc khi dat bom");
    System.out.println("So bomb hien tai: " + this.curBombRemain);
    if (curBombRemain > 0) {
      this.lowerBombRemain();

      System.out.println("Sau khi dat bom");
      System.out.println("So bomb con lai: " + this.curBombRemain);
      System.out.printf("x: %d, y: %d, rx: %d, ry: %d\n", this.x, this.y, this.rx, this.ry);

      Bomb bomb = new Bomb(this.rx, this.ry, Sprite.bomb.getFxImage(), false, false);
      new Sound("sound/put_bombs.wav", "putBomb");
      bombs.add(bomb);

    } else
      System.out.println("Tam thoi het bomb");
  }


  @Override
  public void killedByBomb() {
    for (Bomb bomb : bombs) {
      if (bomb.isFinal()) {
        if (bomb.getRx() == this.rx && bomb.getRy() == this.ry) {
          this.killedByEnemy();
          this.setAlive(false);
          isOver = true;
        }
      }
    }
  }

  public void killedByEnemy() {
    isStopMoving = true;
    if (!this.isAlive) {
      // transform from dead1 state to dead3 state
      if (countTransform % 25 == 0) {
        if (animationTransform == 1) {
          this.img = Sprite.player_dead1.getFxImage();
          animationTransform = 2;
        } else if (animationTransform == 2) {
          this.img = Sprite.player_dead2.getFxImage();
          animationTransform = 3;
        } else if (animationTransform == 3) {
          this.img = Sprite.player_dead3.getFxImage();
          animationTransform = 4;
        } else {
          isOver = true;
        }
      }
    }
  }

  @Override
  public void update() {
    countTransform++;
    killedByBomb();
  }

  public int getCurBombRemain() {
    return curBombRemain;
  }

  public void setCurBombRemain(int curBombRemain) {
    this.curBombRemain = curBombRemain;
  }

  public void gainBombRemain() {
    curBombRemain++;
  }

  public void lowerBombRemain() {
    curBombRemain--;
  }
}
