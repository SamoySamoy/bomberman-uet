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

import uet.oop.bomberman.entities.bomberman.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.blocks.Bomb;

import java.util.*;

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
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();

    // cordinates of all objects in a simplify matrix
    // handle while rendering in Map.java
    public static int[][] objId;
    // first level
    public static int level = 1;
    // add main player start at (rx:1, ry:1) (coordinates in objId),
    // (x:1, y:1) (cordinates in screen size)
    public static Bomber bomberman =
            new Bomber(1, 1, Sprite.player_right.getFxImage(), 1, true, "right");
    Enemy ballom1 = new Ballom(1, 5, Sprite.balloom_left3.getFxImage(), true, "left");
    Enemy oneal1 = new Oneal(7, 7, Sprite.oneal_left3.getFxImage(), true, "left");
    Enemy oneal2 = new Oneal(11, 1, Sprite.oneal_left3.getFxImage(), true, "left");
    public static boolean isOver = false;
    public static boolean isStopMoving = false;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    // game init
    @Override
    public void start(Stage stage) {
        // Create canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // scene
        Scene scene = new Scene(root);

        // keyboard events
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!isStopMoving)
                    bomberman.handleEventPress(keyEvent);
            }
        });

        // add scene vao stage
        stage.setScene(scene);
        stage.show();

        // map render
        new Map("res/levels/Level1.txt");

        // add enemies tạm thời, sẽ add enemies ở phần tạo level sau
        enemies.add(ballom1);
        enemies.add(oneal1);
        enemies.add(oneal2);

        // game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isOver) {
                    enemies.forEach(Enemy::move);
                    update();
                    render();
                }
            }
        };
        timer.start();
    }


    // stage update
    public void update() {
        entities.forEach(Entity::update);
        bomberman.update();
        enemies.forEach(Enemy::update);
        bombs.forEach(Bomb::update);
        bombs.removeIf(Bomb::isExploded);
    }

    // object render
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
        enemies.forEach(enemy -> enemy.render(gc));
        bombs.forEach(bomb -> bomb.render(gc));
    }
}
