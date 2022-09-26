package uet.oop.bomberman.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entity;

public abstract class Block extends Entity {
    public Block(int x_unit, int y_unit, Image img) {
        super(x_unit, y_unit, img);
    }
}
