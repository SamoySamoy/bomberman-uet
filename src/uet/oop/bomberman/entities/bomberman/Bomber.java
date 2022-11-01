package uet.oop.bomberman.entities.bomberman;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends MovableEntity {
    private int deadAnimation = 1;
    private int countTransform = 0;
    private long lastPutBomb = 0;
    private int curBombRemain;
    public static int BOMBER_WAIT_NEXT_STEP;
    public static final int BOMBER_DEFAULT_SPEED = 8;

    public static final int BOMBER_WAIT_NEXT_STEP_NORMAL = 4;
    public static final int BOMBER_WAIT_NEXT_STEP_FAST = 2;

    public Bomber(int rx, int ry, Image img, int deadAnimation, boolean isAlive, String direction) {
        super(rx, ry, img, isAlive, direction);
        this.curBombRemain = 1;
        this.setSpeedState();
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
            // 4 5 6 7 8 lần lượt là Speed, Flame, Bomb item, Bomb limit item, Time limit item
            switch (itemMatrix[this.rx][this.ry]) {
                case 4: // High speed item
                    Bomber.BOMBER_WAIT_NEXT_STEP = Bomber.BOMBER_WAIT_NEXT_STEP_FAST;
                    System.out.println("Speed: " + this.speed);
                    System.out.println("Wait for next step: " + Bomber.BOMBER_WAIT_NEXT_STEP);
                    break;
                case 5: // Gain bomb power item
                    Bomb.gainBombLevel();
                    break;
                case 6: // Gain amount of bomb can be put in once time
                    this.gainBombRemain();
                    break;
                case 7:
                    bomb_number += 10;
                    Menu.bomb.setText("Bomb " + bomb_number);
                    break;
                case 8:
                    time_number += 30;
                    Menu.time.setText("Time " + time_number);
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
        if (curBombRemain > 0 && bomb_number > 0) {
            // lower bomb can be put in the same period
            this.lowerBombRemain();
            // decrease bomb_number remain in bag
            bomb_number--;
            Menu.bomb.setText("Bomb " + bomb_number);

            Bomb bomb = new Bomb(this.rx, this.ry, Sprite.bomb.getFxImage(), false, false);
            new Sound("sound/put_bombs.wav", "putBomb");
            bombs.add(bomb);

        } else
            System.out.println("Tam thoi het bomb");
    }

    private void setSpeedState() {
        this.speed = Bomber.BOMBER_DEFAULT_SPEED;
        Bomber.BOMBER_WAIT_NEXT_STEP = Bomber.BOMBER_WAIT_NEXT_STEP_NORMAL;
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
            if (countTransform % 15 == 0) {
                if (deadAnimation == 1) {
                    this.img = Sprite.player_dead.getFxImage();
                    deadAnimation = 2;
                } else if (deadAnimation == 2) {
                    this.img = Sprite.player_dead_1.getFxImage();
                    deadAnimation = 3;
                } else if (deadAnimation == 3) {
                    this.img = Sprite.player_dead_2.getFxImage();
                    deadAnimation = 4;
                } else {
                    isOver = true;
                }
            }
        }
    }

    public void reset() {
        this.setAlive(true);
        this.setDeadAnimation(1);
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
        this.setSpeedState();
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

    public int getDeadAnimation() {
        return deadAnimation;
    }

    public void setDeadAnimation(int deadAnimation) {
        this.deadAnimation = deadAnimation;
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
