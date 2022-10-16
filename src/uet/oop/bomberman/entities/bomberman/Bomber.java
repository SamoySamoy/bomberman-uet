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
                    this.setCount(Sprite.SCALED_SIZE / this.speed);
                    this.checkRun();
                    break;
                case "down":
                    this.setDirection("down");
                    this.setCount(Sprite.SCALED_SIZE / this.speed);
                    this.checkRun();
                    break;
                case "left":
                    this.setDirection("left");
                    this.setCount(Sprite.SCALED_SIZE / this.speed);
                    this.checkRun();
                    break;
                case "right":
                    this.setDirection("right");
                    this.setCount(Sprite.SCALED_SIZE / this.speed);
                    this.checkRun();
                    break;
            }
        }
    }

    public void checkRun() {
        if (this.getCount() > 0) {
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
                this.setX(this.getX() - this.speed);
                break;
            case "right":
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
                ry++;
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
        this.checkPickItem();
        this.checkTele();
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
}
