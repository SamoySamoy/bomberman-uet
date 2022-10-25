package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.enemies.PathFinding.AStar;
import uet.oop.bomberman.entities.enemies.PathFinding.Node;
import static uet.oop.bomberman.BombermanGame.*;
import java.util.*;

public class Intel extends Enemy {

  public Intel(int rx, int ry, Image img, boolean isALive, String direction) {
    super(rx, ry, img, isALive, direction);
  }

  public void basicMove(int nextX, int nextY) {}

  @Override
  public void move() {
    if (isAlive) {
      long temp = System.currentTimeMillis();

      if (temp - lastMoveTime >= ENEMY_DELAY) {
        lastMoveTime = temp;
        Node initial_node = new Node(this.ry, this.rx);
        Node final_node = new Node(bomberman.getRy(), bomberman.getRx());

        int rows = SCREEN_HEIGHT;
        int cols = LEVEL_WIDTH;

        AStar a_star = new AStar(rows, cols, initial_node, final_node);

        int[][] blocks_in_array = new int[LEVEL_WIDTH * SCREEN_HEIGHT][2];
        int count_block = 0;

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
          for (int j = 0; j < LEVEL_WIDTH; j++) {
            if (objId[j][i] != 0) {
              blocks_in_array[count_block][0] = i;
              blocks_in_array[count_block][1] = j;
              count_block++;
            }
          }
        }
        a_star.setBlocks(blocks_in_array, count_block);
        List<Node> path = a_star.findPath();
        if (path.size() != 0) {
          int nextX = path.get(1).getCol();
          int nextY = path.get(1).getRow();
          if (this.ry > nextY && this.moveUp(null, DEFAULT_ENEMY_SPEED)) {
            this.moveUp(null, DEFAULT_ENEMY_SPEED);
          } else if (this.ry < nextY && this.moveDown(null, DEFAULT_ENEMY_SPEED)) {
            this.moveDown(null, DEFAULT_ENEMY_SPEED);
          } else if (this.rx > nextX && this.moveLeft(null, DEFAULT_ENEMY_SPEED)) {
            this.moveLeft(null, DEFAULT_ENEMY_SPEED);
          } else if (this.rx < nextX && this.moveRight(null, DEFAULT_ENEMY_SPEED)) {
            this.moveRight(null, DEFAULT_ENEMY_SPEED);
          }
        }
      }
    }

  }


  @Override
  public void destroyed() {

  }
}





