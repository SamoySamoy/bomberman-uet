package uet.oop.bomberman.LevelMap;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.entities.blocks.Bomb;

import static uet.oop.bomberman.BombermanGame.*;

public class LevelUp {
    public static void clearLevel() {
        // clean and clear stage
        isStopMoving = false;
        isOver = false;
        isLevelUp = false;
        isPause = false;
        Bomb.curBombLevel = 1;
        entities.clear();
        stillObjects.clear();
        enemies.clear();
        bombs.clear();
        items.clear();
        // clear bomb matrix, item matrix, objId,
        for (int i = 0; i < LEVEL_WIDTH; i++) {
            for (int j = 0; j < LEVEL_HEIGHT; j++) {
                bombMatix[i][j] = 0;
                itemMatrix[i][j] = 0;
                objId[i][j] = 0;
            }
        }
        bomberman.reset();
    }

    public static void setBombAndTime(int bombLimit, int timeLimit) {
        // Set time and bomb limit
        BombermanGame.bomb_number = bombLimit;
        BombermanGame.time_number = timeLimit;
        Menu.bomb.setText("Bomb " + bomb_number);
        Menu.time.setText("Time " + time_number);
        // reset level text
        Menu.level.setText("Level " + currentLevel);
    }

    public static void checkLevelUp() {
        if (bomberman.getX() == portal.getX() && bomberman.getY() == portal.getY()) {
            if (currentLevel == 1) {
                new Level2();
            } else if (currentLevel == 2) {
                new Level3();
            }
        }
    }
}
