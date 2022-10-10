package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;


public class LevelUp {
  public static void checkLevelUp() {
    if (bomberman.getX() == portal.getX() && bomberman.getY() == portal.getY()) {
      new Level2();
    }
  }

}
