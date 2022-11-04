package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;


public class Level1 {
  public Level1() {
    // clear level
    LevelUp.clearLevel();
    new Map("res/levels/Level1.txt");

    // set bomb and time limit
    LevelUp.setBombAndTime(30, 150);

    // set and add enemies
    // Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left.getFxImage(), true, "left");
    // Enemy oneal1 = new Oneal(2, 7, Sprite.oneal_left.getFxImage(), true, "left");
    // Enemy oneal2 = new Oneal(11, 1, Sprite.oneal_left.getFxImage(), true, "left");
    // Enemy oneal3 = new Oneal(42, 11, Sprite.oneal_left.getFxImage(), true, "left");
    // Enemy oneal4 = new Oneal(44, 3, Sprite.oneal_left.getFxImage(), true, "left");
    // Enemy oneal5 = new Oneal(41, 5, Sprite.oneal_left.getFxImage(), true, "left");
    // Enemy minvo1 = new Minvo(23, 12, Sprite.minvo_left.getFxImage(), true, "left");
    // Enemy minvo2 = new Minvo(20, 6, Sprite.minvo_left.getFxImage(), true, "left");

    Enemy kondoria1 = new Kondoria(2, 7, Sprite.kondoria_left.getFxImage(), true, "left");
    Enemy kondoria2 = new Kondoria(6, 1, Sprite.kondoria_left.getFxImage(), true, "left");
    enemies.add(kondoria1);
    enemies.add(kondoria2);

    // enemies.add(ballom1);
    // enemies.add(oneal1);
    // enemies.add(oneal2);
    // enemies.add(oneal3);
    // enemies.add(oneal4);
    // enemies.add(oneal5);
    // enemies.add(minvo1);
    // enemies.add(minvo2);

    // load authorView Scr
    Image transparent = new Image("images/transparent.png");
    author_view.setImage(transparent);
  }
}
