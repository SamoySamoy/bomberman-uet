package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

import javafx.scene.input.KeyEvent;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

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

    // check collision
    public boolean checkCollision(List<Entity> stillObjects) {
        for (Entity x : stillObjects) {
            if (x instanceof Wall || x instanceof Brick) {
                return checkCollision(x);
            }
        }
        return false;
    }

    private boolean checkCollision(Entity block) {
        // The sides of the rectangles
        int leftA, leftB;
        int rightA, rightB;
        int topA, topB;
        int bottomA, bottomB;

        // Calculate the sides of rect A
        leftA = this.x;
        rightA = this.x + SCALED_SIZE;
        topA = this.y;
        bottomA = this.y + SCALED_SIZE;

        // Calculate the sides of rect B
        leftB = block.x;
        rightB = block.x + SCALED_SIZE;
        topB = block.y;
        bottomB = block.y + SCALED_SIZE;

        // If any of the sides from A are outside of B
        if (bottomA <= topB) {
            return false;
        }

        if (topA >= bottomB) {
            return false;
        }

        if (rightA <= leftB) {
            return false;
        }

        if (leftA >= rightB) {
            return false;
        }

        // If none of the sides from A are outside B
        return true;
    }

    // Move
    public void move() {

    }

    @Override
    public void update() {

    }
}
