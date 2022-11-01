package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.blocks.Bomb;

import java.util.Arrays;


public class Level1 {
    public Level1() {
        LevelUp.clearLevel();
        new Map("res/levels/Level1.txt");

        LevelUp.setBombAndTime(20, 10);

        // Set enemy
        Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left.getFxImage(), true, "left");
        Enemy oneal1 = new Oneal(7, 7, Sprite.oneal_left.getFxImage(), true, "left");
        Enemy oneal2 = new Oneal(11, 1, Sprite.oneal_left.getFxImage(), true, "left");
        Enemy minvo1 = new Minvo(13, 12, Sprite.minvo_left.getFxImage(), true, "left");
//        enemies.add(ballom1);
//        enemies.add(oneal1);
//        enemies.add(oneal2);
//        enemies.add(minvo1);

        // load authorView Scr
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);

    }
}
