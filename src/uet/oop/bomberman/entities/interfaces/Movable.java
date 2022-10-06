package uet.oop.bomberman.entities.interfaces;

import javafx.scene.image.Image;

public interface Movable {
    void move();

    void moveUp(Image upImage);

    void moveDown(Image downImage);

    void moveLeft(Image leftImage);

    void moveRight(Image rightImage);
}
