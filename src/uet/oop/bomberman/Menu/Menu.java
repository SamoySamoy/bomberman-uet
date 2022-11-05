package uet.oop.bomberman.Menu;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Sound.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    public final static String FONT_PATH = "res/Demo/kenvector_future.ttf";
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private static final double MENU_BUTTON_START_X = 100;
    private static final double MENU_BUTTON_START_Y = 150;

    AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private GameSubScene helpSubScene;
    private GameSubScene musicSubScene;

    private GameSubScene sceneToHide;

    public List<GameButton> menuButtons;
    public List<GameButton> helpButtonList;
    public List<GameButton> musicButtonList;
    public static Text level, bomb, time;
    // limit is 120 seconds
    public static Image pauseGame, playGame;

    public Menu() {
        menuButtons = new ArrayList<>();
        helpButtonList = new ArrayList<>();
        musicButtonList = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        createLogo();
    }

    private void showSubScene(GameSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {
        helpSubScene = new GameSubScene();
        musicSubScene = new GameSubScene();

        createMusicSubScene();
        createHelpSubScene();
    }

    private void createMusicSubScene() {
        musicSubScene = new GameSubScene();
        mainPane.getChildren().add(musicSubScene);

        Slider slider = new Slider();
        slider.setPrefSize(570,400);
        slider.setLayoutX(45);
        slider.setLayoutY(70);
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        InfoLabel label = new InfoLabel("VOLUME");
        label.setLayoutX(220);
        label.setLayoutY(70);
        label.setFont(Font.font(FONT_PATH, 30));

        //URL soundURL = getClass().getResource("/sound/bg.wav");
        Sound sound = new Sound("sound/bg.wav", "title");

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                label.setText("VOLUME = " + (int)slider.getValue());
                sound.currentVolume = (int)slider.getValue();
                /*if (sound.currentVolume == -40) {
                    sound.currentVolume = - 80;
                }
                sound.fc.setValue(sound.currentVolume);*/
            }
        });

        musicSubScene.getPane().getChildren().addAll(label, slider);
    }

    private void createHelpSubScene() {
        helpSubScene = new GameSubScene();
        mainPane.getChildren().add(helpSubScene);
        GridPane helpGrid = new GridPane();
        helpGrid.setLayoutX(50);
        helpGrid.setLayoutY(10);
        helpGrid.setHgap(20);
        helpGrid.setVgap(20);

        GameButton upButton = new GameButton("W");
        addHelpButtons(upButton);
        GameButton downButton = new GameButton("S");
        addHelpButtons(downButton);
        GameButton leftButton = new GameButton("A");
        addHelpButtons(leftButton);
        GameButton rightButton = new GameButton("D");
        addHelpButtons(rightButton);
        GameButton placeButton = new GameButton("SPACE");
        addHelpButtons(placeButton);

        helpGrid.addRow(0, new InfoLabel("UP"), upButton);
        helpGrid.addRow(1, new InfoLabel("DOWN"), downButton);
        helpGrid.addRow(2, new InfoLabel("LEFT"), leftButton);
        helpGrid.addRow(3, new InfoLabel("RIGHT"), rightButton);
        helpGrid.addRow(4, new InfoLabel("PLACE BOMB"), placeButton);

        helpSubScene.getPane().getChildren().add(helpGrid);
    }

    private void addHelpButtons(GameButton button) {
        helpButtonList.add(button);
        button.setLayoutX(10);
        button.setLayoutY(10 + helpButtonList.size() * 10);
        helpSubScene.getPane().getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createMusicButton();
        createHelpButton();
        createExitButton();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButtons(GameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 120);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createStartButton() {
        GameButton startButton = new GameButton("New Game");
        addMenuButtons(startButton);
        startButton.setOnAction(event -> {
            mainStage.hide();
            GameMenu gameViewManager = new GameMenu();
            gameViewManager.createNewGame(mainStage);
        });
    }

    private void createMusicButton() {
        GameButton musicButton = new GameButton("Music");
        addMenuButtons(musicButton);
        musicButton.setOnAction(event -> showSubScene(musicSubScene));
    }

    private void createHelpButton() {
        GameButton helpButton = new GameButton("Help");
        addMenuButtons(helpButton);
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }

    private void createExitButton() {
        GameButton exitButton = new GameButton("Exit");
        addMenuButtons(exitButton);
        exitButton.setOnAction(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You're about to logout!");
            alert.setContentText("Do you want to save before exiting?");

            if (alert.showAndWait().get() == ButtonType.OK){
                System.out.println("You successfully logged out");
                Platform.exit();
            }
        });
    }

    private void createBackground() {
        Image bgImage = new Image(getClass().getResourceAsStream("/Demo/DemoBackground.gif"),
                256, 256, false, true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(1024, 800, false, false, false, false));
        mainPane.setBackground(new Background(bg));
    }

    private void createLogo() {
        Image logoImage = new Image(getClass().getResourceAsStream("/Demo/logo3.png"),
                500, 100, false, false);
        ImageView logo = new ImageView(logoImage);
        logo.setLayoutX(400);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logo.setEffect(new DropShadow(100, Color.YELLOW));
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }
}
