package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.interfaces.Movable;

public abstract class MovableEntity extends Entity implements Movable {
    // cordinates of all objects in a simplify matrix
    protected int rx;
    protected int ry;

    public MovableEntity(int rx, int ry, Image img) {
        super(rx, ry, img);
        this.rx = rx;
        this.ry = ry;
    }

    public int getRx() {
        return rx;
    }

    public void setRx(int rx) {
        this.rx = rx;
    }

    public int getRy() {
        return ry;
    }

    public void setRy(int ry) {
        this.ry = ry;
    }
}
