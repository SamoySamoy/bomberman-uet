package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;


public class Level2 {
    public Level2() {
        // clear level
        LevelUp.clearLevel();
        new Map("res/levels/Level2.txt");

        LevelUp.setBombAndTime(40, 200);
        // set and add enemies
        Enemy oneal1 = new Oneal(2, 3, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal2 = new Oneal(1, 4, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal3 = new Oneal(2, 5, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal4 = new Oneal(1, 6, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal5 = new Oneal(2, 7, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal6 = new Oneal(1, 8, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal7 = new Oneal(2, 9, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal8 = new Oneal(1, 10, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal9 = new Oneal(2, 11, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal10 = new Oneal(1, 12, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal11 = new Oneal(31, 6, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy oneal12 = new Oneal(39, 6, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy ballom1 = new Ballom(27, 8, Sprite.balloom_left_2.getFxImage(), true, "left");
        Enemy ballom2 = new Ballom(46, 8, Sprite.balloom_left_2.getFxImage(), true, "left");
        enemies.add(oneal1);
        enemies.add(oneal2);
        enemies.add(oneal3);
        enemies.add(oneal4);
        enemies.add(oneal5);
        enemies.add(oneal6);
        enemies.add(oneal7);
        enemies.add(oneal8);
        enemies.add(oneal9);
        enemies.add(oneal10);
        enemies.add(oneal11);
        enemies.add(oneal12);
        enemies.add(ballom1);
        enemies.add(ballom2);

        // load authorView Scr
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);
    }
}

