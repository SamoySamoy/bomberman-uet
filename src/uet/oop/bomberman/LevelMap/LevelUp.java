package uet.oop.bomberman.LevelMap;

import static uet.oop.bomberman.BombermanGame.*;

public class LevelUp {
  public static void checkLevelUp() {
    if (bomberman.getX() == portal.getX() && bomberman.getY() == portal.getY()) {
      new Level2();
    }
  }
}
