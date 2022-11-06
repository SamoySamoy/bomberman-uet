package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Sound;

import static uet.oop.bomberman.BombermanGame.*;

public class Kondoria extends Minvo {
    public Kondoria(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.delayTime = KONDORIA_DELAY_TIME;
        // Set img
        upImg[0] = Sprite.kondoria_left.getFxImage();
        upImg[1] = Sprite.kondoria_left_1.getFxImage();
        upImg[2] = Sprite.kondoria_left_2.getFxImage();

        downImg[0] = Sprite.kondoria_right.getFxImage();
        downImg[1] = Sprite.kondoria_right_1.getFxImage();
        downImg[2] = Sprite.kondoria_right_2.getFxImage();

        leftImg[0] = Sprite.kondoria_left.getFxImage();
        leftImg[1] = Sprite.kondoria_left_1.getFxImage();
        leftImg[2] = Sprite.kondoria_left_2.getFxImage();

        rightImg[0] = Sprite.kondoria_right.getFxImage();
        rightImg[1] = Sprite.kondoria_right_1.getFxImage();
        rightImg[2] = Sprite.kondoria_right_2.getFxImage();
    }

    // set lai move de co the di chuyen qua bomb
    @Override
    protected void setMove(String moveDrirection, int moveRx, int moveRy, Image img) {
        this.setDirection(moveDrirection);
        // Kiem tra va cham voi tuong (2) gach (3) bom(2)
        if (objId[moveRx][moveRy] != 2 && objId[moveRx][moveRy] != 3) {
            // Set so buoc phai di chuyen
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            this.checkRun();
        } else {
            this.setImg(img);
        }
    }

    // KT an bom
    private void checkEatBomb() {
        // Up
        checkBomb(this.getRx(), this.getRy() - 1);
        // Down
        checkBomb(this.getRx(), this.getRy() + 1);
        // Left
        checkBomb(this.getRx() - 1, this.getRy());
        // Right
        checkBomb(this.getRx() + 1, this.getRy());
    }

    private void checkBomb(int bombRx, int bombRy) {
        if (bombMatix[bombRx][bombRy] == 2) {
            Bomb curBomb = Bomb.getBomb(bombRx, bombRy);
            if (curBomb != null && !curBomb.isExploded() && curBomb.isRaw()
                && !curBomb.isUi()) {
                bombs.remove(curBomb);
                new Sound("sound/eat.wav", "eat");
                bombMatix[bombRx][bombRy] = 0;
                bomberman.gainBombRemain();
            }
        }
    }

    @Override
    public void update() {
        super.update();
        this.checkEatBomb();
    }
}
