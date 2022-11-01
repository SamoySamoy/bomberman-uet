package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.ViewManager;

import java.io.IOException;

public class Game extends Application {
    @Override
    public void start(Stage stage) {
        try {
            ViewManager manager = new ViewManager();
            stage = manager.getMainStage();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}