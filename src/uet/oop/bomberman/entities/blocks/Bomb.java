package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.items.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import static uet.oop.bomberman.BombermanGame.*;

import java.lang.Iterable;

public class Bomb extends Entity {
  private static final long bombLastTime = 2200;
  private static final long uiLastTime = 480;
  private static final int MAX_BOMB_LEVEL = 2;
  private long bombStartTime;
  private boolean isExploded;
  private boolean isFinal;
  private boolean isRaw;
  private boolean ui;
  private int animationTransform = 1;
  private int countTransform = 0;

  public static int curBombLevel = 1;
  private final Bomb[] left = new Bomb[MAX_BOMB_LEVEL];
  private final Bomb[] right = new Bomb[MAX_BOMB_LEVEL];
  private final Bomb[] up = new Bomb[MAX_BOMB_LEVEL];
  private final Bomb[] down = new Bomb[MAX_BOMB_LEVEL];

  public Bomb(int rx, int ry, Image img, boolean isExploded, boolean ui) {
    super(rx, ry, img);
    this.isExploded = isExploded;
    this.ui = ui;
    this.bombStartTime = System.currentTimeMillis();
    this.isFinal = false;
    this.isRaw = true;
    bombMatix[this.rx][this.ry] = 2;
  }

  public void explosion() {
    setRaw(false);
    if (countTransform % 10 == 0) {
      if (animationTransform == 1) {
        this.img = Sprite.bomb_exploded.getFxImage();
        animationTransform = 2;
        explosionPhase1();
      } else if (animationTransform == 2) {
        this.img = Sprite.bomb_exploded1.getFxImage();
        animationTransform = 3;
        explosionPhase2();
      } else if (animationTransform == 3) {
        this.img = Sprite.bomb_exploded2.getFxImage();
        this.isFinal = true;
        animationTransform = 4;
        explosionPhase3();
      } else {
        bombMatix[this.rx][this.ry] = 0;
        this.isExploded = true;
        bomberman.gainBombRemain();
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
        bombMatix[this.rx][this.ry] = 0;
        isExploded = true;
      }
    }
  }

  private void checkImpact() {
    // Brick 3
    if (this.isFinal && this.ui && objId[this.rx][this.ry] == 3) {
      for (int i = 0; i < stillObjects.size(); i++) {
        Entity block = stillObjects.get(i);
        if (block.getX() / Sprite.SCALED_SIZE == this.rx
            && block.getY() / Sprite.SCALED_SIZE == this.ry) {
          // Note: 4 5 6 7 8 is Speed, Flame, Bomb item, Bomb limit item, Time limit item
          switch (itemMatrix[this.rx][this.ry]) {
            case 4:
              items.add(new SpeedItem(this.rx, this.ry));
              break;
            case 5:
              items.add(new FlameItem(this.rx, this.ry));
              break;
            case 6:
              items.add(new BombItem(this.rx, this.ry));
              break;
            case 7:
              items.add(new BombLimitItem(this.rx, this.ry));
              break;
            case 8:
              items.add(new TimeLimitItem(this.rx, this.ry));
              break;
          }
          if (BombermanGame.currentLevel == 1) {
            stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass2.getFxImage()));
          } else if (BombermanGame.currentLevel == 2) {
            stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass3.getFxImage()));
          } else {
            stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass4.getFxImage()));
          }
          objId[this.rx][this.ry] = itemMatrix[this.rx][this.ry];
        }
      }
    }
  }

  public static Bomb getBomb(int bombRx, int bombRy) {
    for (Bomb bomb : bombs) {
      if (bomb.getRx() == bombRx && bomb.getRy() == bombRy) {
        return bomb;
      }
    }
    return null;
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

  public boolean isRaw() {
    return isRaw;
  }

  public void setRaw(boolean isRaw) {
    this.isRaw = isRaw;
  }

  public boolean isUi() {
    return ui;
  }

  public void setUi(boolean ui) {
    this.ui = ui;
  }

  public static int getCurBombLevel() {
    return curBombLevel;
  }

  public static void setCurBombLevel(int curBombLevel) {
    Bomb.curBombLevel = curBombLevel;
  }

  public static void gainBombLevel() {
    if (curBombLevel < MAX_BOMB_LEVEL) {
      curBombLevel++;
    }
  }

  public static void lowerBombLevel() {
    if (curBombLevel > 0) {
      curBombLevel--;
    }
  }

  private void explosionPhase1() {
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (objId[rx - i][ry] == 2)
        break;
      if (objId[rx - i][ry] != 2) {
        if (curBombLevel > 1) {
          if (i == 1) {
            this.left[0] =
                new Bomb(rx - i, ry, Sprite.explosion_horizontal.getFxImage(), false, true);
            bombs.add(left[0]);
          } else {
            this.left[i - 1] = new Bomb(rx - i, ry,
                Sprite.explosion_horizontal_left_last.getFxImage(), false, true);
            bombs.add(left[i - 1]);
          }
        } else {
          this.left[i - 1] =
              new Bomb(rx - i, ry, Sprite.explosion_horizontal_left_last.getFxImage(), false, true);
          bombs.add(left[i - 1]);
        }

      }
      if (objId[rx - i][ry] == 3)
        break;
    }

    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (objId[rx + i][ry] == 2)
        break;
      if (objId[rx + i][ry] != 2) {
        if (curBombLevel > 1) {
          if (i == 1) {
            this.right[0] =
                new Bomb(rx + i, ry, Sprite.explosion_horizontal.getFxImage(), false, true);
            bombs.add(right[0]);
          } else {
            this.right[i - 1] = new Bomb(rx + i, ry,
                Sprite.explosion_horizontal_right_last.getFxImage(), false, true);
            bombs.add(right[i - 1]);
          }
        } else {
          this.right[i - 1] = new Bomb(rx + i, ry,
              Sprite.explosion_horizontal_right_last.getFxImage(), false, true);
          bombs.add(right[i - 1]);
        }
      }
      if (objId[rx + i][ry] == 3)
        break;
    }


    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (objId[rx][ry - i] == 2)
        break;
      if (objId[rx][ry - i] != 2) {
        if (curBombLevel > 1) {
          if (i == 1) {
            this.up[0] = new Bomb(rx, ry - i, Sprite.explosion_vertical.getFxImage(), false, true);
            bombs.add(up[0]);
          } else {
            this.up[i - 1] =
                new Bomb(rx, ry - i, Sprite.explosion_vertical_top_last.getFxImage(), false, true);
            bombs.add(up[i - 1]);
          }
        } else {
          this.up[i - 1] =
              new Bomb(rx, ry - i, Sprite.explosion_vertical_top_last.getFxImage(), false, true);
          bombs.add(up[i - 1]);
        }
      }
      if (objId[rx][ry - i] == 3)
        break;
    }

    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (objId[rx][ry + i] == 2)
        break;
      if (objId[rx][ry + i] != 2) {
        if (curBombLevel > 1) {
          if (i == 1) {
            this.down[0] =
                new Bomb(rx, ry + i, Sprite.explosion_vertical.getFxImage(), false, true);
            bombs.add(down[0]);
          } else {
            this.down[i - 1] =
                new Bomb(rx, ry + i, Sprite.explosion_vertical_down_last.getFxImage(), false, true);
            bombs.add(down[i - 1]);
          }
        } else {
          this.down[i - 1] =
              new Bomb(rx, ry + i, Sprite.explosion_vertical_down_last.getFxImage(), false, true);
          bombs.add(down[i - 1]);
        }
      }
      if (objId[rx][ry + i] == 3)
        break;
    }
  }

  private void explosionPhase2() {
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.left[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.left[i - 1].img = Sprite.explosion_horizontal_left_last1.getFxImage();
          } else {
            this.left[i - 1].img = Sprite.explosion_horizontal1.getFxImage();
          }
        } else {
          this.left[i - 1].img = Sprite.explosion_horizontal_left_last1.getFxImage();
        }
      } else
        break;
    }

    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.right[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.right[i - 1].img = Sprite.explosion_horizontal_right_last1.getFxImage();
          } else {
            this.right[i - 1].img = Sprite.explosion_horizontal1.getFxImage();
          }
        } else {
          this.right[i - 1].img = Sprite.explosion_horizontal_right_last1.getFxImage();
        }
      } else
        break;
    }
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.up[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.up[i - 1].img = Sprite.explosion_vertical_top_last1.getFxImage();
          } else {
            this.up[i - 1].img = Sprite.explosion_vertical1.getFxImage();
          }
        } else {
          this.up[i - 1].img = Sprite.explosion_vertical_top_last1.getFxImage();
        }
      } else
        break;
    }
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.down[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.down[i - 1].img = Sprite.explosion_vertical_down_last1.getFxImage();
          } else {
            this.down[i - 1].img = Sprite.explosion_vertical1.getFxImage();
          }
        } else {
          this.down[i - 1].img = Sprite.explosion_vertical_down_last1.getFxImage();
        }
      } else
        break;
    }
    new Sound("sound/bomb_explosion.wav", "explosion");
  }

  private void explosionPhase3() {
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.left[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.left[i - 1].img = Sprite.explosion_horizontal_left_last2.getFxImage();
          } else {
            this.left[i - 1].img = Sprite.explosion_horizontal2.getFxImage();
          }
        } else {
          this.left[i - 1].img = Sprite.explosion_horizontal_left_last2.getFxImage();
        }
        this.left[i - 1].isFinal = true;
        bombMatix[this.left[i - 1].rx][this.left[i - 1].ry] = 1;
        this.left[i - 1].checkImpact();
      } else
        break;
    }

    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.right[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.right[i - 1].img = Sprite.explosion_horizontal_right_last2.getFxImage();
          } else {
            this.right[i - 1].img = Sprite.explosion_horizontal2.getFxImage();
          }
        } else {
          this.right[i - 1].img = Sprite.explosion_horizontal_right_last2.getFxImage();
        }
        this.right[i - 1].isFinal = true;
        bombMatix[this.right[i - 1].rx][this.right[i - 1].ry] = 1;
        this.right[i - 1].checkImpact();
      } else
        break;
    }
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.up[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.up[i - 1].img = Sprite.explosion_vertical_top_last2.getFxImage();
          } else {
            this.up[i - 1].img = Sprite.explosion_vertical2.getFxImage();
          }
        } else {
          this.up[i - 1].img = Sprite.explosion_vertical_top_last2.getFxImage();
        }
        this.up[i - 1].isFinal = true;
        bombMatix[this.up[i - 1].rx][this.up[i - 1].ry] = 1;
        this.up[i - 1].checkImpact();
      } else
        break;
    }
    for (int i = 1; i <= Bomb.curBombLevel; i++) {
      if (this.down[i - 1] != null) {
        if (curBombLevel > 1) {
          if (i != 1) {
            this.down[i - 1].img = Sprite.explosion_vertical_down_last2.getFxImage();
          } else {
            this.down[i - 1].img = Sprite.explosion_vertical2.getFxImage();
          }
        } else {
          this.down[i - 1].img = Sprite.explosion_vertical_down_last2.getFxImage();
        }
        this.down[i - 1].isFinal = true;
        bombMatix[this.down[i - 1].rx][this.down[i - 1].ry] = 1;
        this.down[i - 1].checkImpact();
      } else
        break;
    }
  }


}
