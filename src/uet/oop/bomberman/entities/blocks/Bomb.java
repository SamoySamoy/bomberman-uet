package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Sound;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
  private static final long bombLastTime = 2200;
  private static final long uiLastTime = 480;
  private static final int MAX_BOMB_LEVEL = 2;
  private int rx;
  private int ry;
  private long bombStartTime;
  public boolean isExploded;
  private boolean isFinal;
  private boolean ui;
  private int animationTransform = 1;
  private int countTransform = 0;

  public static int curBombLevel = 1;
  private Bomb[] left = new Bomb[MAX_BOMB_LEVEL];
  private Bomb[] right = new Bomb[MAX_BOMB_LEVEL];
  private Bomb[] up = new Bomb[MAX_BOMB_LEVEL];
  private Bomb[] down = new Bomb[MAX_BOMB_LEVEL];

  public Bomb(int rx, int ry, Image img, boolean isExploded, boolean ui) {
    super(rx, ry, img);
    this.rx = rx;
    this.ry = ry;
    this.isExploded = isExploded;
    this.ui = ui;
    this.bombStartTime = System.currentTimeMillis();
    this.isFinal = false;
    bombMatix[this.rx][this.ry] = 2;
  }

  public void explosion() {
    if (countTransform % 10 == 0) {
      if (animationTransform == 1) {
        this.img = Sprite.bomb_exploded.getFxImage();
        animationTransform = 2;

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
              this.left[i - 1] = new Bomb(rx - i, ry,
                  Sprite.explosion_horizontal_left_last.getFxImage(), false, true);
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
                this.up[0] =
                    new Bomb(rx, ry - i, Sprite.explosion_vertical.getFxImage(), false, true);
                bombs.add(up[0]);
              } else {
                this.up[i - 1] = new Bomb(rx, ry - i,
                    Sprite.explosion_vertical_top_last.getFxImage(), false, true);
                bombs.add(up[i - 1]);
              }
            } else {
              this.up[i - 1] = new Bomb(rx, ry - i, Sprite.explosion_vertical_top_last.getFxImage(),
                  false, true);
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
                this.down[i - 1] = new Bomb(rx, ry + i,
                    Sprite.explosion_vertical_down_last.getFxImage(), false, true);
                bombs.add(down[i - 1]);
              }
            } else {
              this.down[i - 1] = new Bomb(rx, ry + i,
                  Sprite.explosion_vertical_down_last.getFxImage(), false, true);
              bombs.add(down[i - 1]);
            }
          }
          if (objId[rx][ry + i] == 3)
            break;
        }

      } else if (animationTransform == 2) {
        this.img = Sprite.bomb_exploded1.getFxImage();
        animationTransform = 3;
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
      } else if (animationTransform == 3) {
        this.img = Sprite.bomb_exploded2.getFxImage();
        this.isFinal = true;
        animationTransform = 4;
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
    System.out.printf("Check impact: objId[%d][%d]: %d\n", this.rx, this.ry,
        objId[this.rx][this.ry]);
    // Mới chỉ check nổ với gạch, gạch là 3
    if (this.isFinal && this.ui && objId[this.rx][this.ry] == 3) {
      System.out.println("Found brick");
      for (int i = 0; i < stillObjects.size(); i++) {
        Entity block = stillObjects.get(i);
        if (block.getX() / Sprite.SCALED_SIZE == this.rx
            && block.getY() / Sprite.SCALED_SIZE == this.ry) {
          System.out.println("Replace");
          switch (itemMatrix[this.rx][this.ry]) {
            case 4:
              items.add(new SpeedItem(this.rx, this.ry, Sprite.powerup_speed.getFxImage()));
              break;
            case 5:
              items.add(new FlameItem(this.rx, this.ry, Sprite.powerup_flames.getFxImage()));
              break;
            case 6:
              items.add(new BombItem(this.rx, this.ry, Sprite.powerup_bombs.getFxImage()));
              break;
          }
          stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass.getFxImage()));
          objId[this.rx][this.ry] = itemMatrix[this.rx][this.ry];
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
}
