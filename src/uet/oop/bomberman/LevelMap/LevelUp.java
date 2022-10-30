package uet.oop.bomberman.LevelMap;

import static uet.oop.bomberman.BombermanGame.*;

public class LevelUp {
  public static void checkLevelUp() {
    if (bomberman.getX() == portal.getX() && bomberman.getY() == portal.getY()) {
      if (level == 1) {
        new Level2();
      } else if (level == 2) {
        new Level3();
      }
    }
  }
}
