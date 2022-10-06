package uet.oop.bomberman.entities.bomberman;

import javafx.scene.image.Image;

import java.util.*;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends MovableEntity {
    private int animationTransform;

    public static final int DEFAULT_SPEED = 32;

    public Bomber(int rx, int ry, Image img, int animationTransform, boolean isAlive, String direction) {
        super(rx, ry, img, isAlive, direction);
        this.animationTransform = animationTransform;
    }

    public void move() {
    }

    // keyboard event
    public void handleEventPress(KeyEvent e) {
        switch (e.getCode()) {
            case W:
            case UP:
                this.moveUp(Sprite.player_up.getFxImage(), DEFAULT_SPEED);
                break;
            case S:
            case DOWN:
                this.moveDown(Sprite.player_down.getFxImage(), DEFAULT_SPEED);
                break;
            case A:
            case LEFT:
                this.moveLeft(Sprite.player_left.getFxImage(), DEFAULT_SPEED);
                break;
            case D:
            case RIGHT:
                this.moveRight(Sprite.player_right.getFxImage(), DEFAULT_SPEED);
                break;
            case SPACE:
                // put bomb
                System.out.println(x + " " + y + " " + rx + " " + ry);
                Entity bomb = new Bomb(x, y, Sprite.bomb.getFxImage(), rx, ry, false);
                bombs.add(bomb);
                break;
        }
    }

    public void killedByEnemy() {
        if (!this.isAlive) {
            // transform from dead1 state to dead3 state
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

    @Override
    public void update() {

    }
}
