package uet.oop.bomberman.Control;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButton extends Button {


    public GameButton(String text) {
        setStyle("-fx-background-color: yellow");
        setStyle("-fx-background-image: url('/uet/oop/bomberman/images/yellow_button.png')");
        setBackground(getBackground());
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        initialiseButtonListeners();

    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream("src/main/resources/uet/oop/bomberman/images/kenvector_future.ttf"), 25));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 25));
            System.out.println("Font not found or could not be loaded. Using default \"Verdana\"");
        }
    }

    private void setButtonPressedStyle() {
        setStyle("-fx-background-color: yellow");
        setStyle("-fx-background-image: url('/uet/oop/bomberman/images/yellow_button_pressed.png')");
        setPrefHeight(49);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyle() {
        setStyle("-fx-background-color: yellow");
        setStyle("-fx-background-image: url('/uet/oop/bomberman/images/yellow_button.png')");
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);

    }

    private void initialiseButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }

        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow(50, Color.YELLOW));
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
