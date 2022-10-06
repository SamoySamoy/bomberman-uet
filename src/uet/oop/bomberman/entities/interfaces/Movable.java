package uet.oop.bomberman.entities.interfaces;

import javafx.scene.image.Image;

public interface Movable {
    void move();

    // Di chuyển trái phải lên xuống thành công thì trả về true, di chuyển không thành công khi gặp tường,...
    boolean moveUp(Image upImage, int speed);

    boolean moveDown(Image downImage, int speed);

    boolean moveLeft(Image leftImage, int speed);

    boolean moveRight(Image rightImage, int speed);
}
