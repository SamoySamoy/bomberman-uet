package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Enemy extends Entity {
    protected static final int DEFAULT_ENEMY_SPEED = 32;
    protected static final long ENEMY_DELAY = 800;
    protected int rx;
    protected int ry;
    protected boolean isALive;
    protected String direction;

    protected long lastMoveTime = 0;

    public Enemy(int xUnit, int yUnit, Image img, int rx, int ry, boolean isALive, String direction) {
        super(xUnit, yUnit, img);
        this.rx = rx;
        this.ry = ry;
        this.isALive = isALive;
        this.direction = direction;
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

    public abstract void move();

    public abstract void killBomber();

    public abstract void destroyed();

    @Override
    public void update() {

    }
}
