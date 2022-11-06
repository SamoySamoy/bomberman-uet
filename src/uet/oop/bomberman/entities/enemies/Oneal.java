package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Oneal extends Enemy {
    public Oneal(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.delayTime = ONEAL_DELAY_TIME;
        // Set img
        upImg[0] = Sprite.oneal_left.getFxImage();
        upImg[1] = Sprite.oneal_left_1.getFxImage();
        upImg[2] = Sprite.oneal_left_2.getFxImage();

        downImg[0] = Sprite.oneal_right.getFxImage();
        downImg[1] = Sprite.oneal_right_1.getFxImage();
        downImg[2] = Sprite.oneal_right_2.getFxImage();

        leftImg[0] = Sprite.oneal_left.getFxImage();
        leftImg[1] = Sprite.oneal_left_1.getFxImage();
        leftImg[2] = Sprite.oneal_left_2.getFxImage();

        rightImg[0] = Sprite.oneal_right.getFxImage();
        rightImg[1] = Sprite.oneal_right_1.getFxImage();
        rightImg[2] = Sprite.oneal_right_2.getFxImage();
    }

    @Override
    public void move() {
        if (isAvailToTakeNewSteps()) {
            // Return lại chỗ tọa độ X để tránh việc di chuyển được 1 cả tọa độ X lẫn Y trong 1 lần
            // Nếu di chuyển cả X và Y trong 1 lần thì sẽ bị di chuyển chéo

            if (bomberman.getX() < this.getX()) {
                // Nếu di chuyển bên trái thành công thì dừng
                this.setMove("left");
                return;
            } else if (bomberman.getX() > this.getX()) {
                // Nếu di chuyển bên phải thành công thì dừng
                this.setMove("right");
                return;
            }

            // If enemy x = bomber x
            if (bomberman.getY() > this.getY()) {
                this.setMove("down");
            } else if (bomberman.getY() < this.getY()) {
                this.setMove("up");
            }
        }
    }

    @Override
    public void destroyed() {

    }
}
