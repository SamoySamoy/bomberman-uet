package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {
    public int velX;
    public int velY;

    public static final int DEFAULT_SPEED = 32;
    public static final int HIGH_SPEED = DEFAULT_SPEED * 2;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        velX = 0;
        velY = 0;
    }

    // Handle keyboard event
    public void handleEventPress(KeyEvent e) {
        switch (e.getCode()) {
            case W:
                // move up
//                velY = -DEFAULT_SPEED;
                y -= DEFAULT_SPEED;
                break;
            case S:
                // move down
//                velY = DEFAULT_SPEED;
                y += DEFAULT_SPEED;
                break;
            case A:
//                 move
//                velX = -DEFAULT_SPEED;
                x -= DEFAULT_SPEED;
                break;
            case D:
                // move up
//                velX = +DEFAULT_SPEED;
                x += DEFAULT_SPEED;
                break;
        }
    }

    public void handleEventRelease(KeyEvent e) {
        switch (e.getCode()) {
            case W:
            case S:
//                velY = 0;
                break;
            case A:
            case D:
//                velX = 0;
                // move
                break;
        }
    }

    // Move
//    public void move() {
//        x += velX;
//        y += velY;
//    }

    @Override
    public void update() {

    }
}
