package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Map.*;


import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

  // screen size
  public static final int WIDTH = 25;
  public static final int HEIGHT = 15;

  // javafx init
  private GraphicsContext gc;
  private Canvas canvas;

  // game objects
  public static List<Entity> entities = new ArrayList<>();
  public static List<Entity> stillObjects = new ArrayList<>();
  public static int[][] objId;
  public static int level = 1;


  public static void main(String[] args) {
    Application.launch(BombermanGame.class);
  }

  // game init
  @Override
  public void start(Stage stage) {
    // Tao Canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    Group root = new Group();
    root.getChildren().add(canvas);

    // Tao scene
    Scene scene = new Scene(root);

    // Them scene vao stage
    stage.setScene(scene);
    stage.show();

    // game loop
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        update();
      }
    };
    timer.start();

    // map render
    new Map("res/levels/Level1.txt");
    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    entities.add(bomberman);
  }

  // stage update
  public void update() {
    entities.forEach(Entity::update);
  }

  // render
  public void render() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
  }
}