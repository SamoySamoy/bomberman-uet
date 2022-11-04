package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;

public class Level3 {
  public Level3() {
    // clear level
    LevelUp.clearLevel();
    new Map("res/levels/Level3.txt");

    // set bomb and time limit
    LevelUp.setBombAndTime(50, 10);

    // set and add enemies
    Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left.getFxImage(), true, "left");
    Enemy kondo1 = new Kondoria(7, 7, Sprite.kondoria_left.getFxImage(), true, "left");
    Enemy minvo1 = new Minvo(13, 12, Sprite.minvo_left.getFxImage(), true, "left");
    enemies.add(ballom1);
    enemies.add(kondo1);
    enemies.add(minvo1);

    // load authorView Scr
    Image transparent = new Image("images/transparent.png");
    author_view.setImage(transparent);
  }
}

