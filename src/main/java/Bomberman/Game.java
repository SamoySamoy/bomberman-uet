package Bomberman;

import Bomberman.Menu.GameMenu;
import Bomberman.Menu.MainMenu;
import Bomberman.UI.UIComponents;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

import java.util.HashMap;
import java.util.Map;

import static Bomberman.Constants.Constant.*;
import static Bomberman.Sounds.SoundEffect.*;
import static com.almasb.fxgl.dsl.FXGL.*;


public class Game extends GameApplication {
    private Map temp = new HashMap();
    private boolean isLoading = false;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(SCENE_WIDTH);
        gameSettings.setHeight(SCENE_HEIGHT);
        gameSettings.setTitle(GAME_TITLE);
        gameSettings.setVersion(GAME_VERSION);

        gameSettings.setFullScreenAllowed(true);
        gameSettings.setFullScreenFromStart(true);

        gameSettings.setIntroEnabled(false);
        gameSettings.setGameMenuEnabled(true);
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setFontUI("game_font.ttf");
        gameSettings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new MainMenu();
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new GameMenu();
            }

        });
//        gameSettings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void onPreInit() {
        unmute();
        loopBGM("1-01. Title.mp3");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("level", STARTING_LEVEL);
        vars.put("life", 3);
        vars.put("enemies", 0);
        vars.put("score", 0);
        vars.put("flame", 1);
        vars.put("speed", PLAYER_SPEED);
        vars.put("bomb", 1);
        vars.put("levelTime", TIME_LEVEL);
    }

    @Override
    protected void initUI() {
        UIComponents.addILabelUI("level", "🚩 %d", 35, 25);
        UIComponents.addILabelUI("life", "💜 %d", 160, 25);
        UIComponents.addILabelUI("score", "💵  %d", 300, 25);
        UIComponents.addILabelUI("flame", "🔥 %d", 560, 25);
        UIComponents.addILabelUI("speed", "👟  %d", 670, 25);
        UIComponents.addILabelUI("bomb", "💣 %d", 840, 25);
        UIComponents.addILabelUI("enemies", "👻 %d", 1010, 25);
        UIComponents.addDLabelUI("levelTime", "⏰ %.0f", 1140, 25);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
