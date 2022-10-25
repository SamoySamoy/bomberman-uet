package uet.oop.bomberman.entities.bomberman;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends MovableEntity {
    private static final long putBombPeriod = 2300;
    private int animationTransform = 1;
    private int countTransform = 0;
    private long lastPutBomb = 0;
    private int curBombRemain;

    public static final int DEFAULT_SPEED = 8;
    public static final int HIGH_SPEED = 16;
    private int speed;
    private int count;
    private int countToRun;
    private int swap;

    public Bomber(int rx, int ry, Image img, int animationTransform, boolean isAlive,
                  String direction) {
        super(rx, ry, img, isAlive, direction);
        this.curBombRemain = 1;
        this.count = 0;
        this.countToRun = 0;
        this.swap = 1;
        this.speed = DEFAULT_SPEED;
    }

    @Override
    public void move() {

    }

    public void move(String moveDirection) {
        if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
            switch (moveDirection) {
                case "up":
                    this.setDirection("up");
                    if (objId[rx][ry - 1] != 2 && objId[rx][ry - 1] != 3 && bombMatix[rx][ry - 1] != 2) {
                        this.setCount(Sprite.SCALED_SIZE / this.speed);
                        this.checkRun();
                    } else {
                        this.img = Sprite.player_up.getFxImage();
                    }
                    break;
                case "down":
                    this.setDirection("down");
                    if (objId[rx][ry + 1] != 2 && objId[rx][ry + 1] != 3 && bombMatix[rx][ry + 1] != 2) {
                        this.setCount(Sprite.SCALED_SIZE / this.speed);
                        this.checkRun();
                    } else {
                        this.img = Sprite.player_down.getFxImage();
                    }
                    break;
                case "left":
                    this.setDirection("left");
                    if (objId[rx - 1][ry] != 2 && objId[rx - 1][ry] != 3 && bombMatix[rx - 1][ry] != 2) {
                        this.setCount(Sprite.SCALED_SIZE / this.speed);
                        this.checkRun();
                    } else {
                        this.img = Sprite.player_left.getFxImage();
                    }
                    break;
                case "right":
                    this.setDirection("right");
                    if (objId[rx + 1][ry] != 2 && objId[rx + 1][ry] != 3 && bombMatix[rx + 1][ry] != 2) {
                        this.setCount(Sprite.SCALED_SIZE / this.speed);
                        this.checkRun();
                    } else {
                        this.img = Sprite.player_right.getFxImage();
                    }
                    break;
            }
        }
    }

    public void checkRun() {
        if (this.getCount() > 0) {
            System.out.println("Checking running");
            this.setMoveDirection();
            this.setCount(this.getCount() - 1);
        }
    }

    private void setMoveDirection() {
        switch (this.direction) {
            case "up":
                upStep();
                this.setY(this.getY() - this.speed);
                break;
            case "down":
                downStep();
                this.setY(this.getY() + this.speed);
                break;
            case "left":
                leftStep();
                this.setX(this.getX() - this.speed);
                break;
            case "right":
                rightStep();
                this.setX(this.getX() + this.speed);
                break;
        }
    }

    private void upStep() {
        if (this.getY() % 8 == 0) {
            if (this.getSwap() == 1) {
                this.setImage(Sprite.player_up.getFxImage());
                this.setSwap(2);
            } else if (this.getSwap() == 2) {
                this.setImage(Sprite.player_up_1.getFxImage());
                this.setSwap(3);
            } else if (this.getSwap() == 3) {
                this.setImage(Sprite.player_up_2.getFxImage());
                this.setSwap(4);
            } else {
                this.setImage(Sprite.player_up.getFxImage());
                ry--;
                System.out.println(rx + " " + ry);
                this.checkPickItem();
//                this.checkTele();
                this.setSwap(1);
            }
        }
    }

    private void downStep() {
        if (this.getY() % 8 == 0) {
            if (this.getSwap() == 1) {
                this.setImage(Sprite.player_down.getFxImage());
                this.setSwap(2);
            } else if (this.getSwap() == 2) {
                this.setImage(Sprite.player_down_1.getFxImage());
                this.setSwap(3);
            } else if (this.getSwap() == 3) {
                this.setImage(Sprite.player_down_2.getFxImage());
                this.setSwap(4);
            } else {
                this.setImage(Sprite.player_down.getFxImage());
                ry++;
                System.out.println(rx + " " + ry);
                this.checkPickItem();
//                this.checkTele();
                this.setSwap(1);
            }
        }
    }

    private void leftStep() {
        if (this.getX() % 8 == 0) {
            if (this.getSwap() == 1) {
                this.setImage(Sprite.player_left.getFxImage());
                this.setSwap(2);
            } else if (this.getSwap() == 2) {
                this.setImage(Sprite.player_left_1.getFxImage());
                this.setSwap(3);
            } else if (this.getSwap() == 3) {
                this.setImage(Sprite.player_left_2.getFxImage());
                this.setSwap(4);
            } else {
                this.setImage(Sprite.player_left.getFxImage());
                rx--;
                System.out.println(rx + " " + ry);
                this.checkPickItem();
//                this.checkTele();
                this.setSwap(1);
            }
        }
    }

    private void rightStep() {
        if (this.getX() % 8 == 0) {
            if (this.getSwap() == 1) {
                this.setImage(Sprite.player_right.getFxImage());
                this.setSwap(2);
            } else if (this.getSwap() == 2) {
                this.setImage(Sprite.player_right_1.getFxImage());
                this.setSwap(3);
            } else if (this.getSwap() == 3) {
                this.setImage(Sprite.player_right_2.getFxImage());
                this.setSwap(4);
            } else {
                this.setImage(Sprite.player_right.getFxImage());
                rx++;
                System.out.println(rx + " " + ry);
                this.checkPickItem();
//                this.checkTele();
                this.setSwap(1);
            }
        }
    }


    // keyboard event
    public void handleEventPress(KeyEvent e) {
        switch (e.getCode()) {
            case W:
            case UP:
                this.move("up");
                break;
            case S:
            case DOWN:
                this.move("down");
                break;
            case A:
            case LEFT:
                this.move("left");
                break;
            case D:
            case RIGHT:
                this.move("right");
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

//    private void checkTele() {
//        if (objId[rx][ry] == 7) {
//            int desX = 0;
//            int desY = 0;
//            for (int i = 0; i < WIDTH; i++) {
//                for (int j = 0; j < HEIGHT; j++) {
//                    if (i != rx && j != ry && objId[i][j] == 7) {
//                        desX = i;
//                        desY = j;
//                        break;
//                    }
//                }
//            }
//            this.setRx(desX);
//            this.setRy(desY);
//            this.setX(desX * Sprite.SCALED_SIZE);
//            this.setY(desY * Sprite.SCALED_SIZE);
//            System.out.printf("rx:%d ry:%d x:%d y:%d\n", this.rx, this.ry, this.x, this.y);
//        }
//    }

    private void putBomb() {
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

    public void reset() {
        this.setAlive(true);
        this.setAnimationTransform(1);
        this.setCountTransform(0);
        this.setLastPutBomb(0);
        this.setRx(1);
        this.setRy(1);
        this.setX(32);
        this.setY(32);
        this.setDirection("right");
        this.setImage(Sprite.player_right.getFxImage());
        this.setCurBombRemain(1);
        this.setCount(0);
        this.setCountToRun(0);
        this.setSwap(1);
        this.setSpeed(Bomber.DEFAULT_SPEED);
        Bomb.curBombLevel = 1;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountToRun() {
        return countToRun;
    }

    public void setCountToRun(int countToRun) {
        this.countToRun = countToRun;
    }

    public int getSwap() {
        return swap;
    }

    public void setSwap(int swap) {
        this.swap = swap;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAnimationTransform() {
        return animationTransform;
    }

    public void setAnimationTransform(int animationTransform) {
        this.animationTransform = animationTransform;
    }

    public int getCountTransform() {
        return countTransform;
    }

    public void setCountTransform(int countTransform) {
        this.countTransform = countTransform;
    }

    public long getLastPutBomb() {
        return lastPutBomb;
    }

    public void setLastPutBomb(long lastPutBomb) {
        this.lastPutBomb = lastPutBomb;
    }
}
