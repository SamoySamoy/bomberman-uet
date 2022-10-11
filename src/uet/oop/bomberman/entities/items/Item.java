package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    protected int rx;
    protected int ry;
    protected boolean isPicked;

    public Item(int rx, int ry, Image img) {
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

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
