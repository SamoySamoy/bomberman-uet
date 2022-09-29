package uet.oop.bomberman.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

public class Brick extends Block {
  public Brick(int x_unit, int y_unit, Image img) {
    super(x_unit, y_unit, img);
  }

  private void checkHidden(List<Entity> block, int[][] list_kill) { // Check Brick's Visibility
    for (Entity entity : block) {
      if (entity instanceof Brick)
        // At the element of the 2-dimensional listKill array with the value 4, Brick and Grass will
        // appear
        if (list_kill[entity.getX() / 32][entity.getY() / 32] == 4) {
          entity.setImg(Sprite.grass.getFxImage());
        }
    }
  }

  @Override
  public void update() {

  }
}
