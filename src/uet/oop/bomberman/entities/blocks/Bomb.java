package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Bomb extends Entity {
    private static final long bombLastTime = 3000;
    private int rx;
    private int ry;
    private long bombStartTime;
    public boolean isExploded;

    public Bomb(int rx, int ry, Image img, boolean isExploded) {
        super(rx, ry, img);
        this.rx = rx;
        this.ry = ry;
        this.isExploded = isExploded;
        this.bombStartTime = System.currentTimeMillis();
    }

    public void explosion() {
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - this.bombStartTime > bombLastTime) this.isExploded = true;
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

    public long getBombStartTime() {
        return bombStartTime;
    }

    public void setBombStartTime(long bombStartTime) {
        this.bombStartTime = bombStartTime;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }
}
