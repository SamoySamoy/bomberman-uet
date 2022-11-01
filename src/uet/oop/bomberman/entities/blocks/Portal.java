package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import static uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.LevelMap.LevelUp;

public class Portal extends Entity {
  public Portal(int x, int y, Image img) {
    super(x, y, img);
  }

  public static void checkPortal() {
    if (enemies.size() == 0 && !isLevelUp) {
      if (BombermanGame.level == 1) {
        stillObjects.add(portal1);
        LevelUp.checkLevelUp();
        System.out.println(level);
      } else if (BombermanGame.level == 2) {
        stillObjects.add(portal2);
        LevelUp.checkLevelUp();
        System.out.println(level);
      }
    }
  }

  @Override
  public void update() {
    checkPortal();
  }
}
