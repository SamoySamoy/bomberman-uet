package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import uet.oop.bomberman.entities.bomberman.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.entities.blocks.Portal;
import uet.oop.bomberman.Sound.Sound;

import java.util.*;

public class BombermanGame extends Application {

    // screen size
    public static final int SCREEN_WIDTH = 25;
    public static final int SCREEN_HEIGHT = 15;

    public static final int SCREEN_WIDTH_PIXELS = SCREEN_WIDTH * Sprite.SCALED_SIZE;
    public static final int SCREEN_HEIGHT_PIXELS = SCREEN_HEIGHT * Sprite.SCALED_SIZE;
    public static final int LEVEL_WIDTH = 50;
    public static final int LEVEL_HEIGHT = 15;

    public static final int LEVEL_WIDTH_PIXELS = LEVEL_WIDTH * Sprite.SCALED_SIZE;
    public static final int LEVEL_HEIGHT_PIXELS = LEVEL_HEIGHT * Sprite.SCALED_SIZE;
    // javafx init
    public static ImageView author_view;

    // game objects
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();

    // cordinates of all objects in a simplify matrix
    // handle while rendering in Map.java
    public static int[][] objId = new int[LEVEL_WIDTH][LEVEL_HEIGHT]; // Create new object id_object
    // from main
    // file. ;
    public static int[][] bombMatix = new int[LEVEL_WIDTH][LEVEL_HEIGHT]; // Create bomb id base on
    // matrix
    public static int[][] itemMatrix = new int[LEVEL_WIDTH][LEVEL_HEIGHT]; // Create item id base on
    // matrix
    public static int currentLevel = 1;

    // The time and bomb limit, each level has different time and bomb limit
    public static int bomb_number;
    public static int time_number;

    // add main player start at (rx:1, ry:1) (coordinates in objId),
    // (x:1, y:1) (cordinates in screen size)
    public static Bomber bomberman =
            new Bomber(1, 1, Sprite.player_right.getFxImage(), 1, true, "right");
    public static Entity portal1 = new Portal(42, 7, Sprite.portal.getFxImage());
    public static Entity portal2 = new Portal(16, 7, Sprite.portal.getFxImage());
    public static Entity portal3 = new Portal(16, 3, Sprite.portal.getFxImage());
    public static boolean isOver = false;
    public static boolean isStopMoving = false;// only bomberman, prevent press after being killed
    public static boolean isPause = false;
    public static boolean isLevelUp = false;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    // game init
    @Override
    public void start(Stage stage) {
        try {
            Menu manager = new Menu();
            stage = manager.getMainStage();
            stage.setTitle("Bomberman Game");
            stage.setResizable(false);
            //stage.setFullScreen(true);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
