package uet.oop.bomberman.graphics;

// Import class

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Portal;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.entities.items.Tele;

import static uet.oop.bomberman.BombermanGame.*;

// Import library
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Map {
  // Constructor MapCreation with parameter "level" in string data type.
  public Map(String level) {
    System.out.println(System.getProperty("user.dir"));
    final File fileName = new File(level);
    try (FileReader inputFile = new FileReader(fileName)) {
      Scanner ip = new Scanner(inputFile); // Create object ip from class Scanner.
      String line = ip.nextLine(); // Input variable line in string data type.

      StringTokenizer tokens = new StringTokenizer(line); // Create object tokens from class
      // StringTokenizer in library imported.

      // parseInt(): Method that parses the string argument and returns a primitive int.
      BombermanGame.level = Integer.parseInt(tokens.nextToken()); // To refer to variable level in
      // main file.

      while (ip.hasNextLine()) {
        for (int i = 0; i < HEIGHT; i++) {
          String lineTile = ip.nextLine(); // Input variable lineTile in string data type.
          StringTokenizer tokenTile = new StringTokenizer(lineTile); // Create object tokenTile from
          // class StringTokenizer in
          // library imported.

          for (int j = 0; j < RWIDTH; j++) {
            int token = Integer.parseInt(tokenTile.nextToken());
            Entity entity; // Create object entity from class Entity.

            // This switch statement running, and we got a full map for a game.
            // Through the program, in the for-loop statement, we can get the map according to each
            // loop it passed.
            switch (token) {
              case 1:
                entity = new Portal(j, i, Sprite.grass.getFxImage()); // In case 1, set entity
                // object equal to object
                // portal with scaled size.
                token = 0;
                break;
              case 2:
                entity = new Wall(j, i, Sprite.wall.getFxImage()); // In case 2, set entity object
                // equal to object wall with
                // scaled size.
                break;
              case 3:
                entity = new Brick(j, i, Sprite.brick.getFxImage()); // In case 3, set entity object
                // equal to object brick with
                // scaled size.
                break;
              case 4:
              case 5:
              case 6:
                // 4 5 6 lần lượt là Speed, Flame, Bomb item
                // khi mới bắt đầu sẽ load gạch
                // Gạch bị bom phá sẽ thay Gạch bằng các item tương ứng
                // thay thể ở trong hàm checkImpact() của bomb
                itemMatrix[j][i] = token;
                token = 3; // 3 là gạch
                entity = new Brick(j, i, Sprite.brick.getFxImage());
                break;
              case 7:
                entity = new Tele(j, i, Sprite.tele.getFxImage());
                break;
              default:
                entity = new Grass(j, i, Sprite.grass.getFxImage());
            }
            objId[j][i] = token; //
            stillObjects.add(entity); //
          }
        }
      }
      ip.close();
    } catch (IOException e) { // Catch exception
      System.out.println("rendering error!!!");
      e.printStackTrace(); // printStackTrace(): Help to understand where the problem is actually
      // happening.
    }

  }
}
