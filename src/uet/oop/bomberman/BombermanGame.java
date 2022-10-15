package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
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
  public static final int WIDTH = 25;
  public static final int HEIGHT = 15;

  // javafx init
  private GraphicsContext gc;
  private Canvas canvas;
  public static ImageView author_view;

  // game objects
  public static List<Entity> entities = new ArrayList<>();
  public static List<Entity> stillObjects = new ArrayList<>();
  public static List<Enemy> enemies = new ArrayList<>();
  public static List<Bomb> bombs = new ArrayList<>();
  public static List<Item> items = new ArrayList<>();

  // cordinates of all objects in a simplify matrix
  // handle while rendering in Map.java
  public static int[][] objId = new int[WIDTH][HEIGHT]; // Create new object id_object from main
                                                        // file. ;
  public static int[][] bombMatix = new int[WIDTH][HEIGHT]; // Create bomb id base on matrix
  public static int[][] itemMatrix = new int[WIDTH][HEIGHT]; // Create item id base on matrix
  // first level
  public static int level = 1;
  // add main player start at (rx:1, ry:1) (coordinates in objId),
  // (x:1, y:1) (cordinates in screen size)
  public static Bomber bomberman =
      new Bomber(1, 1, Sprite.player_right.getFxImage(), 1, true, "right");
  public static Entity portal = new Portal(WIDTH - 2, HEIGHT - 2, Sprite.portal.getFxImage());
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
    // Create canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    canvas.setTranslateY(32);
    gc = canvas.getGraphicsContext2D();
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
    stage.show();
    // keyboard events
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (!isStopMoving)
          bomberman.handleEventPress(keyEvent);
      }
    });

    // game loop
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        if (!isPause) {
          if (!isOver) {
            enemies.forEach(Enemy::move);
            update();
            Menu.updateMenu();
          } else {
            Image gameOver = new Image("images/over.png");
            author_view.setImage(gameOver);
          }
        }
      }
    };
    timer.start();
    bomberman.setAlive(false);
  }

  // stage update
  public void update() {
    entities.forEach(Entity::update);
    bomberman.update();
    enemies.forEach(Enemy::update);
    enemies.removeIf(enemy -> !enemy.isAlive());
    bombs.forEach(Bomb::update);
    bombs.removeIf(Bomb::isExploded);
    items.forEach(Item::update);
    items.removeIf(Item::isPicked);
    portal.update();
    Sound.updateSound();
  }

  // object render
  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
    items.forEach(item -> item.render(gc));
    bombs.forEach(bomb -> bomb.render(gc));
    bomberman.render(gc);
    enemies.forEach(enemy -> enemy.render(gc));
  }
}
