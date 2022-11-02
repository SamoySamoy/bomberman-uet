package uet.oop.bomberman.Menu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import uet.oop.bomberman.Control.GameButton;
import uet.oop.bomberman.Control.GameSubScene;
import uet.oop.bomberman.Control.InfoLabel;
import uet.oop.bomberman.Control.SoundEffects;


public class ViewManager {

    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private final static int MENU_BUTTON_START_X = 100;
    private final static int MENU_BUTTON_START_Y = 150;
    private static final String BUTTON_SFX = "src/main/java/uet/oop/bomberman/sound/title_screen.wav";

    AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private GameSubScene creditsSubScene;
    private GameSubScene helpSubScene;
    private GameSubScene scoreSubScene;
    private GameSubScene sceneToHide;
    private GameSubScene shipChooserSubScene;

    List<GameButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScene();
        createButtons();
        createBackground();
        //createLogo();
    }

    private void showsSubScene(GameSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScene() {
        creditsSubScene = new GameSubScene();
        helpSubScene = new GameSubScene();
        scoreSubScene = new GameSubScene();
        shipChooserSubScene = new GameSubScene();

        createScoreSubScene();
        createHelpSubScene();
        createCreditsSubScene();
        createShipChooserSubScene();
    }

    private void createScoreSubScene() {
        scoreSubScene = new GameSubScene();
        mainPane.getChildren().add(scoreSubScene);
        InfoLabel score = new InfoLabel("<<<< Scores >>>>");
        score.setLayoutX(115);
        score.setLayoutY(20);
        VBox scoreContainer = new VBox();
        scoreContainer.setLayoutX(150);
        scoreContainer.setLayoutY(150);

        Label scoreHeading = new Label("     Name			Score   ");
        scoreHeading.setUnderline(true);
        Label score1 = new Label("Bomber 1		  100");
        Label score2 = new Label("Bomber 2		  100");
        Label score3 = new Label("Bomber 3		  100");
        scoreHeading.setFont(Font.font("Verdana",20));
        score1.setFont(Font.font("Verdana",20));
        score2.setFont(Font.font("Verdana",20));
        score3.setFont(Font.font("Verdana",20));
        scoreContainer.setBackground(new Background(new BackgroundFill(Color.MEDIUMAQUAMARINE, new CornerRadii(20), new Insets(-20,-20,-20,-20))));
        scoreContainer.getChildren().addAll(scoreHeading, score1, score2, score3);

        scoreSubScene.getPane().getChildren().addAll(score, scoreContainer);//, score1, score2, score3);
    }

    private void createHelpSubScene() {
        helpSubScene = new GameSubScene();
        mainPane.getChildren().add(helpSubScene);
        InfoLabel help = new InfoLabel("   <<<< Help >>>>");
        help.setLayoutX(150);
        help.setLayoutY(20);
        GridPane helpGrid = new GridPane();
        helpGrid.setLayoutX(100);
        helpGrid.setLayoutY(100);
        helpGrid.setHgap(20);
        helpGrid.setVgap(20);

        helpSubScene.getPane().getChildren().addAll(help, helpGrid);

    }

    private void createCreditsSubScene() {
        creditsSubScene = new GameSubScene();
        mainPane.getChildren().add(creditsSubScene);

        InfoLabel credits = new InfoLabel("  <<< Credits >>>");
        credits.setLayoutX(120);
        credits.setLayoutY(20);
        creditsSubScene.getPane().getChildren().add(credits);
        Application app = new Application() {@Override public void start(Stage primaryStage) throws Exception{}};
        HostServices services = app.getHostServices();
    }

    private void createShipChooserSubScene() {
        //shipChooserSubScene = new SpaceRunnerSubScene();
        mainPane.getChildren().add(shipChooserSubScene);

        InfoLabel chooseShipLabel = new InfoLabel("LET'S GO");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(20);

        shipChooserSubScene.getPane().getChildren().addAll(chooseShipLabel, createButtonToStart());

    }

    private GameButton createButtonToStart() {
        GameButton startButton = new GameButton("Start");
        startButton.setLayoutX(350);
        startButton.setLayoutY(320);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showsSubScene(shipChooserSubScene);
            }
        });
        return startButton;
    }

    private void createButtons() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(GameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createStartButton() {
        GameButton startButton = new GameButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showsSubScene(shipChooserSubScene);
            }
        });

    }

    private void createScoresButton() {
        GameButton scoresButton = new GameButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showsSubScene(scoreSubScene);
            }
        });

    }

    private void createHelpButton() {
        GameButton helpButton = new GameButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showsSubScene(helpSubScene);
            }
        });
    }

    private void createCreditsButton() {
        GameButton creditsButton = new GameButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showsSubScene(creditsSubScene);
            }
        });

    }

    private void createExitButton() {
        GameButton exitButton = new GameButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                Platform.exit();
                // mainStage.close();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image(getClass().getResourceAsStream("/uet/oop/bomberman/images/menubg.jpg"), 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT    ,
                BackgroundPosition.DEFAULT, new BackgroundSize(1024, 800, false, false, false, false));
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        Image logoImage = new Image(getClass().getResourceAsStream("/uet/oop/bomberman/images/Bomberman_Logo.jpg"), 500, 100, false, false);
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
