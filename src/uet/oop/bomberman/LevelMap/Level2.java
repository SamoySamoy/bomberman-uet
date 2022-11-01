package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;


public class Level2 {
    public Level2() {
        LevelUp.clearLevel();
        new Map("res/levels/Level2.txt");

        LevelUp.setBombAndTime(30, 150);

        Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left_2.getFxImage(), true, "left");
        Enemy oneal1 = new Oneal(7, 7, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal2 = new Oneal(11, 1, Sprite.oneal_left_2.getFxImage(), true, "left");
        enemies.add(ballom1);
        enemies.add(oneal1);
        enemies.add(oneal2);

        // load authorView Scr
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);
    }
}

