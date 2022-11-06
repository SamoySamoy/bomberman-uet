package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bombMatix;
import static uet.oop.bomberman.BombermanGame.objId;

public abstract class MovableEntity extends Entity {
    // moving direction
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
    public static final int DEFAULT_SPEED = 4;
    // max number of frames each character has
    public static final int MAX_NUM_FRAMES = 4;

    public MovableEntity(int rx, int ry, Image img, boolean isAlive, String direction) {
        super(rx, ry, img);
        this.direction = direction;
        this.isAlive = isAlive;
        this.steps = 0;
        this.countToRun = 0;
        this.speed = DEFAULT_SPEED;
    }

    protected boolean isAvailToTakeNewSteps() {
        return this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0;
    }

    public abstract void move();

    protected void setMove(String moveDirection) {
        if (this.isAvailToTakeNewSteps()) {
            switch (moveDirection) {
                case "up":
                    this.setMove("up", this.getRx(), this.getRy() - 1, this.upImg[0]);
                    break;
                case "down":
                    this.setMove("down", this.getRx(), this.getRy() + 1, this.downImg[0]);
                    break;
                case "left":
                    this.setMove("left", this.getRx() - 1, this.getRy(), this.leftImg[0]);
                    break;
                case "right":
                    this.setMove("right", this.getRx() + 1, this.getRy(), this.rightImg[0]);
                    break;
            }
        }
    }

    protected void setMove(String moveDrirection, int moveRx, int moveRy, Image img) {
        this.setDirection(moveDrirection);
        // Kiem tra va cham voi tuong (2) gach (3) bom(2)
        if (objId[moveRx][moveRy] != 2 && objId[moveRx][moveRy] != 3 && bombMatix[moveRx][moveRy] != 2) {
            // Set so buoc phai di chuyen
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            this.checkRun();
        } else {
            this.setImg(img);
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
                this.upStep(); // handle animation va tang rx hoac ry khi hoan thanh
                this.setY(this.getY() - this.speed);
                break;
            case "down":
                this.downStep(); // handle animation va tang rx hoac ry khi hoan thanh
                this.setY(this.getY() + this.speed);
                break;
            case "left":
                this.leftStep(); // handle animation va tang rx hoac ry khi hoan thanh
                this.setX(this.getX() - this.speed);
                break;
            case "right":
                this.rightStep(); // handle animation va tang rx hoac ry khi hoan thanh
                this.setX(this.getX() + this.speed);
                break;
        }
    }

    protected void upStep() {
        this.yAxisStep("up", this.upImg);
    }


    protected void downStep() {
        this.yAxisStep("down", this.downImg);
    }

    protected void leftStep() {
        this.xAxisStep("left", this.leftImg);
    }

    protected void rightStep() {
        this.xAxisStep("right", this.rightImg);
    }

    protected void yAxisStep(String moveDirection, Image[] img) {
        if (this.getY() % this.speed == 0) {
            if (this.getSteps() % MAX_NUM_FRAMES == 0) {
                this.setImage(img[0]);
            } else if (this.getSteps() % MAX_NUM_FRAMES == 3) {
                this.setImage(img[1]);
            } else if (this.getSteps() % MAX_NUM_FRAMES == 2) {
                this.setImage(img[2]);
            } else if (this.getSteps() % MAX_NUM_FRAMES == 1) {
                this.setImage(img[0]);
                if (moveDirection.equals("up")) this.ry--;
                else this.ry++;
            }
        }
    }

    protected void xAxisStep(String moveDirection, Image[] img) {
        if (this.getX() % this.speed == 0) {
            if (this.getSteps() % MAX_NUM_FRAMES == 0) {
                this.setImage(img[0]);
            } else if (this.getSteps() % MAX_NUM_FRAMES == 3) {
                this.setImage(img[1]);
            } else if (this.getSteps() % MAX_NUM_FRAMES == 2) {
                this.setImage(img[2]);
            } else if (this.getSteps() % MAX_NUM_FRAMES == 1) {
                this.setImage(img[0]);
                if (moveDirection.equals("left")) this.rx--;
                else this.rx++;
            }
        }
    }


    public abstract void killedByBomb();

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
}
