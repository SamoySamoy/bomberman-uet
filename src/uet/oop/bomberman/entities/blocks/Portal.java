package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import static uet.oop.bomberman.BombermanGame.*;
import uet.oop.bomberman.LevelMap.LevelUp;
public class Portal extends Entity {
  public static boolean is_portal = false; // Variable used to display the portal when the player
                                           // wins each level

  public Portal(int x, int y, Image img) {
    super(x, y, img);
  }

  public static void checkLevelUp() {
    if (enemies.size() == 0 && !isLevelUp) {
      stillObjects.add(portal);
      LevelUp.checkLevelUp();
    }
  }

  @Override
  public void update() {
    checkLevelUp();
  }
}
