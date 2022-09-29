package uet.oop.bomberman.blocks;

import javafx.scene.image.Image;

public class Portal extends Block {
  // Only open when player wins the level
  public static boolean isOpen = false;

  public Portal(int x_unit, int y_unit, Image img) {
    super(x_unit, y_unit, img);
  }

  @Override
  public void update() {

  }
}
