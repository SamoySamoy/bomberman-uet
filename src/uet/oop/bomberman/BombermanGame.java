package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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
    private GraphicsContext gc;
    private Canvas canvas;

    // For time counter
    private long lastSecond;
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
    public static boolean isHelp = false;

    // camera smooth translate
    private static final TranslateTransition camera = new TranslateTransition();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    // game init
    @Override
    public void start(Stage stage) {
        // Create canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * LEVEL_WIDTH, Sprite.SCALED_SIZE * SCREEN_HEIGHT);
        canvas.setTranslateY(36);
        gc = canvas.getGraphicsContext2D();
        camera.setDuration(Duration.millis(200));
        camera.setNode(canvas);
        Image author = new Image("images/background.jpg");
        author_view = new ImageView(author);
        author_view.setX(-400);
        author_view.setY(-208);
        author_view.setScaleX(0.5);
        author_view.setScaleY(0.5);
        Group root = new Group();
        Menu.createMenu(root);
        root.getChildren().add(canvas);
        root.getChildren().add(author_view);
        Scene scene = new Scene(root);

        // add scene to stage
        stage.setScene(scene);
        stage.setWidth(SCREEN_WIDTH * 32 + 15);
        stage.setHeight(SCREEN_HEIGHT * 32 + 74);
        stage.show();
        // keyboard events
        scene.setOnKeyPressed(keyEvent -> {
            if (!isStopMoving && !isPause)
                bomberman.handleEventPress(keyEvent);
        });

        // game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                if (!isPause) {
                    if (!isOver) {
                        update();
                        time();
                        Menu.updateMenu();
                        camera.play();
                    } else {
                        Image gameOver = new Image("images/over.png");
                        author_view.setImage(gameOver);
                    }
                }
            }
        };
        timer.start();
        bomberman.setAlive(false);
        lastSecond = System.currentTimeMillis();
    }

    // stage update
    public void update() {
        entities.forEach(Entity::update);

        bomberman.update();
        bomberman.setCountToRun(bomberman.getCountToRun() + 1);
        if (bomberman.getCountToRun() == Bomber.BOMBER_WAIT_NEXT_STEP) {
            bomberman.checkRun();
            bomberman.setCountToRun(0);
        }

        enemies.forEach(Enemy::update);
        for (Enemy enemy : enemies) {
            enemy.setCountToRun(enemy.getCountToRun() + 1);
            if (enemy.getCountToRun() == Enemy.ENEMY_WAIT_NEXT_STEP) {
                enemy.checkRun();
                enemy.setCountToRun(0);
            }
        }
        enemies.removeIf(enemy -> !enemy.isAlive());

        bombs.forEach(Bomb::update);
        bombs.removeIf(Bomb::isExploded);

        items.forEach(Item::update);
        items.removeIf(Item::isPicked);

        portal1.update();
        portal2.update();
        portal3.update();
        Sound.updateSound();
    }

    public void time() {
        long now = System.currentTimeMillis();
        if (now - lastSecond > 1000) {
            lastSecond = now;
            if (bomberman.isAlive() && time_number > 0) {
                time_number--;
                Menu.time.setText("Time " + time_number);
                if (time_number <= 0) {
                    bomberman.setAlive(false);
                    isOver = true;
                }
            }
        }
    }

    // Handle camera
    public void moveCamera() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int cameraX = bomberman.getX() - (SCREEN_WIDTH_PIXELS - Sprite.SCALED_SIZE) / 2;
        if (cameraX < 0)
            cameraX = 0;
        if (cameraX + SCREEN_WIDTH_PIXELS > LEVEL_WIDTH_PIXELS)
            cameraX = SCREEN_WIDTH_PIXELS;
        camera.setToX(-cameraX);

        // set camera to y
    }

    // object render
    public void render() {
        moveCamera();
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        items.forEach(item -> item.render(gc));
        bombs.forEach(bomb -> bomb.render(gc));
        bomberman.render(gc);
        enemies.forEach(enemy -> enemy.render(gc));
    }
}
