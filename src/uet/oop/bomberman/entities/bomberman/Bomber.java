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
    private int animationTransform = 1;
    private int countTransform = 0;
    private long lastPutBomb = 0;
    private int curBombRemain;

    public static final int BOMBER_DEFAULT_SPEED = 8;
    public static final int BOMBER_HIGH_SPEED = 16;

    public Bomber(int rx, int ry, Image img, int animationTransform, boolean isAlive,
                  String direction) {
        super(rx, ry, img, isAlive, direction);
        this.curBombRemain = 1;
        this.speed = BOMBER_DEFAULT_SPEED;
        // Set img
        upImg[0] = Sprite.player_up.getFxImage();
        upImg[1] = Sprite.player_up_1.getFxImage();
        upImg[2] = Sprite.player_up_2.getFxImage();

        downImg[0] = Sprite.player_down.getFxImage();
        downImg[1] = Sprite.player_down_1.getFxImage();
        downImg[2] = Sprite.player_down_2.getFxImage();

        leftImg[0] = Sprite.player_left.getFxImage();
        leftImg[1] = Sprite.player_left_1.getFxImage();
        leftImg[2] = Sprite.player_left_2.getFxImage();

        rightImg[0] = Sprite.player_right.getFxImage();
        rightImg[1] = Sprite.player_right_1.getFxImage();
        rightImg[2] = Sprite.player_right_2.getFxImage();
    }

    @Override
    public void move() {

    }

    @Override
    protected void upStep() {
        super.upStep();
        this.checkPickItem();
    }

    @Override
    protected void downStep() {
        super.downStep();
        this.checkPickItem();
    }

    @Override
    protected void leftStep() {
        super.leftStep();
        this.checkPickItem();
    }

    @Override
    protected void rightStep() {
        super.rightStep();
        this.checkPickItem();
    }

    // keyboard event
    public void handleEventPress(KeyEvent e) {
        switch (e.getCode()) {
            case W:
            case UP:
                this.setMove("up");
                break;
            case S:
            case DOWN:
                this.setMove("down");
                break;
            case A:
            case LEFT:
                this.setMove("left");
                break;
            case D:
            case RIGHT:
                this.setMove("right");
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
                    break;
                case 5:
                    Bomb.gainBombLevel();
                    break;
                case 6:
                    this.gainBombRemain();
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

    private void putBomb() {
        if (curBombRemain > 0) {
            this.lowerBombRemain();
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
                    this.img = Sprite.player_dead.getFxImage();
                    animationTransform = 2;
                } else if (animationTransform == 2) {
                    this.img = Sprite.player_dead_1.getFxImage();
                    animationTransform = 3;
                } else if (animationTransform == 3) {
                    this.img = Sprite.player_dead_2.getFxImage();
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
        this.setSteps(0);
        this.setCountToRun(0);
        this.setSpeed(Bomber.BOMBER_DEFAULT_SPEED);
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
