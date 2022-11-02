package uet.oop.bomberman.Menu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    private GridPane gridPane1, gridPane2;
    private AnimationTimer gameTimer;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 700;

    Random randomPosGen;
    private static final String BUTTON_SFX = "src/main/java/uet/oop/bomberman/sound/title_screen.wav";

    public GameViewManager() {
        randomPosGen = new Random();
        initializeStage();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setTitle("Bomberman");
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
        gameStage.setOnCloseRequest(x -> {
            x.consume();
            // if(ConfirmExit.askConfirmation()) {
            // Platform.exit();
            gameStage.close();
            menuStage.show();
            // }
        });
    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createGameLoop();
        gameStage.show();
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
            }
        };
        gameTimer.start();
    }

}
