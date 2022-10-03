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
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

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
    // cordinates of all objects in a simplify matrix
    // handle while rendering in Map.java
    public static int[][] objId;
    // first level
    public static int level = 1;
    // add main player start at (rx:1, ry:1) (coordinates in objId),
    // (x:1, y:1) (cordinates in screen size)
    public static Bomber bomberman = new Bomber(1, 1, 1, 1, Sprite.player_right.getFxImage(),"right");
    Enemy ballom1 = new Ballom(3, 3, Sprite.balloom_left1.getFxImage(), 3, 3, true, "left");

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

        // add scene vao stage
        stage.setScene(scene);
        stage.show();

        // Handle keyboard events
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomberman.handleEventPress(keyEvent);
            }
        });

        // map render
        new Map("res/levels/Level2.txt");

        // game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                ballom1.move();
                // bomberman.move();
            }
        };
        timer.start();
    }

    // stage update
    public void update() {
        entities.forEach(Entity::update);
        bomberman.update();
        ballom1.update();
    }


    // object render
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
        ballom1.render(gc);
    }

}
