package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.blocks.Bomb.*;


public class Level1 {
  public Level1() {
    // clean and clear stage
    isStopMoving = false;
    isOver = false;
    isLevelUp = false;
    isPause = false;
    Bomb.curBombLevel = 1;
    enemies.clear();
    bombs.clear();
    new Map("res/levels/Level1.txt");
    bomberman.setAlive(true);
    bomberman.setRx(1);
    bomberman.setRy(1);
    bomberman.setX(32);
    bomberman.setY(32);
    bomberman.setDirection("right");
    bomberman.setImage(Sprite.player_right.getFxImage());
    Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left3.getFxImage(), true, "left");
    Enemy oneal1 = new Oneal(7, 7, Sprite.oneal_left3.getFxImage(), true, "left");
    Enemy oneal2 = new Oneal(11, 1, Sprite.oneal_left3.getFxImage(), true, "left");
    Enemy intel1 = new Intel(13, 12, Sprite.minvo_left3.getFxImage(), true, "left");
    // load authorView Scr
    Image transparent = new Image("images/transparent.png");
    author_view.setImage(transparent);

    enemies.add(ballom1);
    enemies.add(oneal1);
    enemies.add(oneal2);
    enemies.add(intel1);

    for (Enemy enemy : enemies) {
      enemy.setAlive(true);
    }
  }
}
