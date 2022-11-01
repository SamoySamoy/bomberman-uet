package uet.oop.bomberman.LevelMap;

import uet.oop.bomberman.entities.blocks.Bomb;

import static uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.BombermanGame;

public class LevelUp {
  public static void clearLevel() {
    // clean and clear stage
    isStopMoving = false;
    isOver = false;
    isLevelUp = false;
    isPause = false;
    Bomb.curBombLevel = 1;
    entities.clear();
    stillObjects.clear();
    enemies.clear();
    bombs.clear();
    items.clear();
    // clear bomb matrix, item matrix, objId,
    for (int i = 0; i < LEVEL_WIDTH; i++) {
      for (int j = 0; j < LEVEL_HEIGHT; j++) {
        bombMatix[i][j] = 0;
        itemMatrix[i][j] = 0;
        objId[i][j] = 0;
      }
    }
    bomberman.reset();
  }

  public static void checkLevelUp() {
    if (BombermanGame.level == 1) {
      if (bomberman.getX() == portal1.getX() && bomberman.getY() == portal1.getY()) {
        new Level2();
      }
    } else if (BombermanGame.level == 2) {
      if (bomberman.getX() == portal2.getX() && bomberman.getY() == portal2.getY()) {
        new Level3();
      }
    }

  }
}
