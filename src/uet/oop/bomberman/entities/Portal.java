package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
  public static boolean is_portal = false; // Variable used to display the portal when the player
                                           // wins each level

  public Portal(int x, int y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {

  }
}
