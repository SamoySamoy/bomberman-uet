package uet.oop.bomberman.Menu;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.LevelMap.Level1;
import uet.oop.bomberman.LevelMap.Level2;
import uet.oop.bomberman.LevelMap.Level3;


public class Menu {
    private static ImageView statusGame;
    public static Text level, bomb, time;
    // limit is 120 seconds
    public static Image pauseGame, playGame;

    public static void createMenu(Group root) { // Create a menu
        level = new Text("Level");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);
        bomb = new Text("Bombs");
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(512);
        bomb.setY(20);
        time = new Text("Times");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);

        Image newGame = new Image("images/start.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        Pane pane = new Pane();
        pane.getChildren().addAll(level, bomb, time, statusGame);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");

        root.getChildren().add(pane);

        playGame = new Image("images/pause.png");
        pauseGame = new Image("images/resume.png");

        statusGame.setOnMouseClicked(event -> {
            if (bomberman.isAlive()) {
                isPause = !isPause;
            } else {
                if (BombermanGame.currentLevel == 1) {
                    new Level1();
                    isOver = false;
                } else if (BombermanGame.currentLevel == 2) {
                    new Level2();
                    isOver = false;
                } else if (BombermanGame.currentLevel == 3) {
                    new Level3();
                    isOver = false;
                }

            }
            updateMenu();
        });

    }

    public static void updateMenu() { // Update menu
        if (bomberman.isAlive())
            if (isPause) {
                statusGame.setImage(pauseGame);
            } else {
                statusGame.setImage(playGame);
            }
        else {
            Image newGame = new Image("images/start.png");
            statusGame.setImage(newGame);
        }
    }
}
