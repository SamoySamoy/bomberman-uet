package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.LevelMap.LevelUp;

public class Portal extends Entity {
  public Portal(int x, int y, Image img) {
    super(x, y, img);
  }

  public static void checkPortal() {
    if (enemies.size() == 0 && !isLevelUp) {
      if (currentLevel == 1) {
        stillObjects.add(portal1);
        LevelUp.checkLevelUp();
        System.out.println(currentLevel);
      } else if (currentLevel == 2) {
        stillObjects.add(portal2);
        LevelUp.checkLevelUp();
        System.out.println(currentLevel);
      }
    }
  }

  @Override
  public void update() {
    checkPortal();
  }
}
