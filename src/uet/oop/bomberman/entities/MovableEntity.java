package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bombMatix;
import static uet.oop.bomberman.BombermanGame.objId;

public abstract class MovableEntity extends Entity {
  // cordinates of all objects in a simplify matrix
  protected int rx;
  protected int ry;
  // Hướng di chuyển
  protected String direction;
  protected boolean isAlive;
  // character img
  protected Image[] upImg = new Image[3];
  protected Image[] downImg = new Image[3];
  protected Image[] leftImg = new Image[3];
  protected Image[] rightImg = new Image[3];

  // move stats
  protected int speed;
  protected int steps;
  protected int countToRun;
  protected int waitNextStep;
  public static final int DEFAULT_SPEED = 4;
  public static final int DEFAULT_WAIT_NEXT_STEP = 4;

  // max number of frames each character has
  public static final int MAX_NUM_FRAMES = 4;


  public MovableEntity(int rx, int ry, Image img, boolean isAlive, String direction) {
    super(rx, ry, img);
    this.rx = rx;
    this.ry = ry;
    this.direction = direction;
    this.isAlive = isAlive;
    this.steps = 0;
    this.countToRun = 0;
    this.speed = DEFAULT_SPEED;
    this.waitNextStep = DEFAULT_WAIT_NEXT_STEP;
  }

  protected boolean isAvailToTakeNewSteps() {
    return this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0;
  }

  public abstract void move();

  protected void setMove(String moveDirection) {
    if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
      switch (moveDirection) {
        case "up":
          this.setDirection("up");
          // Kiem tra va cham voi tuong (2) gach (3) bom(2)
          if (objId[rx][ry - 1] != 2 && objId[rx][ry - 1] != 3 && bombMatix[rx][ry - 1] != 2) {
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
          if (objId[rx][ry + 1] != 2 && objId[rx][ry + 1] != 3 && bombMatix[rx][ry + 1] != 2) {
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
          if (objId[rx - 1][ry] != 2 && objId[rx - 1][ry] != 3 && bombMatix[rx - 1][ry] != 2) {
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
          if (objId[rx + 1][ry] != 2 && objId[rx + 1][ry] != 3 && bombMatix[rx + 1][ry] != 2) {
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

  public void checkRun() {
    // Kiem tra xem con phai di bao nhieu buoc
    if (this.getSteps() > 0) {
      this.setMoveDirection();
      this.setSteps(this.getSteps() - 1);
    }
  }

  protected void setMoveDirection() {
    switch (this.direction) {
      case "up":
        upStep(); // handle animation va tang rx hoac ry khi hoan thanh
        this.setY(this.getY() - this.speed);
        break;
      case "down":
        downStep(); // handle animation va tang rx hoac ry khi hoan thanh
        this.setY(this.getY() + this.speed);
        break;
      case "left":
        leftStep(); // handle animation va tang rx hoac ry khi hoan thanh
        this.setX(this.getX() - this.speed);
        break;
      case "right":
        rightStep(); // handle animation va tang rx hoac ry khi hoan thanh
        this.setX(this.getX() + this.speed);
        break;
    }
  }

  protected void upStep() {
    if (this.getY() % this.speed == 0) {
      if (this.getSteps() % MAX_NUM_FRAMES == 0) {
        this.setImage(this.upImg[0]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 3) {
        this.setImage(this.upImg[1]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 2) {
        this.setImage(this.upImg[2]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 1) {
        this.setImage(this.upImg[0]);
        this.ry--;
        System.out.println(rx + " " + ry);
      }
    }
  }


  protected void downStep() {
    if (this.getY() % this.speed == 0) {
      if (this.getSteps() % MAX_NUM_FRAMES == 0) {
        this.setImage(this.downImg[0]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 3) {
        this.setImage(this.downImg[1]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 2) {
        this.setImage(this.downImg[2]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 1) {
        this.setImage(this.downImg[0]);
        this.ry++;
        System.out.println(rx + " " + ry);
      }
    }
  }

  protected void leftStep() {
    if (this.getX() % this.speed == 0) {
      if (this.getSteps() % MAX_NUM_FRAMES == 0) {
        this.setImage(this.leftImg[0]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 3) {
        this.setImage(this.leftImg[1]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 2) {
        this.setImage(this.leftImg[2]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 1) {
        this.setImage(this.leftImg[0]);
        this.rx--;
        System.out.println(rx + " " + ry);
      }
    }
  }

  protected void rightStep() {
    if (this.getX() % this.speed == 0) {
      if (this.getSteps() % MAX_NUM_FRAMES == 0) {
        this.setImage(this.rightImg[0]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 3) {
        this.setImage(this.rightImg[1]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 2) {
        this.setImage(this.rightImg[2]);
      } else if (this.getSteps() % MAX_NUM_FRAMES == 1) {
        this.setImage(this.rightImg[0]);
        this.rx++;
        System.out.println(rx + " " + ry);
      }
    }
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

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getSteps() {
    return steps;
  }

  public void setSteps(int steps) {
    this.steps = steps;
  }

  public int getCountToRun() {
    return countToRun;
  }

  public void setCountToRun(int countToRun) {
    this.countToRun = countToRun;
  }

  public int getWaitNextStep() {
    return waitNextStep;
  }

  public void setWaitNextStep(int waitNextStep) {
    this.waitNextStep = waitNextStep;
  }
}
