package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.SoundManager;
import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
  private static final long bombLastTime = 2200;
  private static final long uiLastTime = 480;
  private int rx;
  private int ry;
  private long bombStartTime;
  public boolean isExploded;
  private boolean isFinal;
  private int animationTransform = 1;
  private int countTransform = 0;
  public boolean ui;

  private Bomb left;
  private Bomb right;
  private Bomb up;
  private Bomb down;

  public Bomb(int rx, int ry, Image img, boolean isExploded, boolean ui) {
    super(rx, ry, img);
    this.rx = rx;
    this.ry = ry;
    this.isExploded = isExploded;
    this.ui = ui;
    this.bombStartTime = System.currentTimeMillis();
    this.isFinal = false;
  }

  public void explosion() {
    if (countTransform % 10 == 0) {
      if (animationTransform == 1) {
        this.img = Sprite.bomb_exploded.getFxImage();
        animationTransform = 2;
        if (objId[rx - 1][ry] != 2) {
          this.left =
              new Bomb(rx - 1, ry, Sprite.explosion_horizontal_left_last.getFxImage(), false, true);
          bombs.add(left);
        }

        if (objId[rx + 1][ry] != 2) {
          this.right = new Bomb(rx + 1, ry, Sprite.explosion_horizontal_right_last.getFxImage(),
              false, true);
          bombs.add(right);
        }

        if (objId[rx][ry - 1] != 2) {
          this.up =
              new Bomb(rx, ry - 1, Sprite.explosion_vertical_top_last.getFxImage(), false, true);
          bombs.add(up);
        }

        if (objId[rx][ry + 1] != 2) {
          this.down =
              new Bomb(rx, ry + 1, Sprite.explosion_vertical_down_last.getFxImage(), false, true);
          bombs.add(down);
        }

      } else if (animationTransform == 2) {
        this.img = Sprite.bomb_exploded1.getFxImage();
        animationTransform = 3;
        if (this.left != null) {
          this.left.img = Sprite.explosion_horizontal_left_last1.getFxImage();
        }
        if (this.right != null) {
          this.right.img = Sprite.explosion_horizontal_right_last1.getFxImage();
        }
        if (this.up != null) {
          this.up.img = Sprite.explosion_vertical_top_last1.getFxImage();
        }
        if (this.down != null) {
          this.down.img = Sprite.explosion_vertical_down_last1.getFxImage();
        }
        new SoundManager("sound/bomb_explosion.wav", "explosion");
      } else if (animationTransform == 3) {
        this.img = Sprite.bomb_exploded2.getFxImage();
        this.isFinal = true;
        animationTransform = 4;
        if (this.left != null) {
          this.left.img = Sprite.explosion_horizontal_left_last2.getFxImage();
          this.left.isFinal = true;
          this.left.checkImpact();
        }
        if (this.right != null) {
          this.right.img = Sprite.explosion_horizontal_right_last2.getFxImage();
          this.right.isFinal = true;
          this.right.checkImpact();
        }
        if (this.up != null) {
          this.up.img = Sprite.explosion_vertical_top_last2.getFxImage();
          this.up.isFinal = true;
          this.up.checkImpact();
        }
        if (this.down != null) {
          this.down.img = Sprite.explosion_vertical_down_last2.getFxImage();
          this.down.isFinal = true;
          this.down.checkImpact();
        }
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

  private void checkImpact() {
    System.out.printf("Check impact: objId[%d][%d]: %d\n", this.rx, this.ry,
        objId[this.rx][this.ry]);
    // Mới chỉ check nổ với gạch, gạch là 3
    // && this.ui
    if (this.isFinal && this.ui && objId[this.rx][this.ry] == 3) {
      System.out.println("Found brick");
      for (int i = 0; i < stillObjects.size(); i++) {
        Entity block = stillObjects.get(i);
        if (block.getX() / Sprite.SCALED_SIZE == this.rx
            && block.getY() / Sprite.SCALED_SIZE == this.ry) {
          System.out.println("Replace");
          stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass.getFxImage()));
          objId[this.rx][this.ry] = 0;
          System.out.println("Replace success");
        }
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

  public boolean isFinal() {
    return isFinal;
  }

  public void setFinal(boolean aFinal) {
    isFinal = aFinal;
  }
}
