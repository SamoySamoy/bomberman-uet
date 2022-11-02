package uet.oop.bomberman;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.ViewManager;

public class Game extends Application {
    @Override
    public void start(Stage stage) {
        try {
            ViewManager manager = new ViewManager();
            stage = manager.getMainStage();
            stage.setTitle("Bomberman Game");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}