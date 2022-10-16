package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.entities.bomberman.Bomber;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;


public class Level2 {
    public Level2() {
        // clean and clear stage
        isStopMoving = false;
        isOver = false;
        isLevelUp = false;
        isPause = false;
        enemies.clear();
        bombs.clear();
        new Map("res/levels/Level2.txt");

        bomberman.reset();

        Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left3.getFxImage(), true, "left");
        Enemy oneal1 = new Oneal(7, 7, Sprite.oneal_left3.getFxImage(), true, "left");
        Enemy oneal2 = new Oneal(11, 1, Sprite.oneal_left3.getFxImage(), true, "left");

        // load authorView Scr
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);

        enemies.add(ballom1);
        enemies.add(oneal1);
        enemies.add(oneal2);

        for (Enemy enemy : enemies) {
            enemy.setAlive(true);
        }
    }
}

