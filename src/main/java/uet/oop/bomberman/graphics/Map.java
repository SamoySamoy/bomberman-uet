package uet.oop.bomberman.graphics;

// Import library
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

// import modules
import uet.oop.bomberman.Entity;
import uet.oop.bomberman.blocks.Block;
import uet.oop.bomberman.blocks.Grass;
import uet.oop.bomberman.blocks.Portal;
import uet.oop.bomberman.blocks.Wall;
import uet.oop.bomberman.Game;
import static uet.oop.bomberman.Game.*;



public class Map {
  public Map(String level) {
    System.out.println(System.getProperty("user.dir"));
    final File fileName = new File(level); // Create object fileName from class File in File library
                                           // imported.
    try (FileReader inputFile = new FileReader(fileName)) { // Try to create new object from class
                                                            // FileReader.
      Scanner ip = new Scanner(inputFile); // Create object ip from class Scanner.
      String line = ip.nextLine(); // Input variable line in string data type.

      StringTokenizer tokens = new StringTokenizer(line); // Create object tokens from class
                                                          // StringTokenizer in library imported.

      // parseInt(): Method that parses the string argument and returns a primitive int.
      Game.level = Integer.parseInt(tokens.nextToken()); // To refer to variable level in
                                                         // main file.
      height = Integer.parseInt(tokens.nextToken());
      width = Integer.parseInt(tokens.nextToken());

      while (ip.hasNextLine()) {
        id_objects = new int[width][height]; // Create new object id_object from main file.
        list_kill = new int[width][height]; // Create new object lít_kill from main file. Main file:
                                            // RunBomberman.java
        for (int i = 0; i < height; ++i) {
          String lineTile = ip.nextLine(); // Input variable lineTile in string data type.
          StringTokenizer tokenTile = new StringTokenizer(lineTile); // Create object tokenTile from
                                                                     // class StringTokenizer in
                                                                     // library imported.

          for (int j = 0; j < width; j++) {
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
                entity = new Wall(j, i, Sprite.wall.getFxImage());
                break;
              /*
               * case 3: entity = new Brick(j, i, Sprite.brick.getFxImage()); break;
               * 
               * case 6: entity = new SpeedItem(j, i, Sprite.brick.getFxImage()); break; case 7:
               * entity = new FlameItem(j, i, Sprite.brick.getFxImage()); break;
               */
              default:
                entity = new Grass(j, i, Sprite.grass.getFxImage());
            }
            id_objects[j][i] = token; //
            block.add(entity); //
          }
        }
      }
    } catch (IOException e) { // Catch exception
      e.printStackTrace(); // printStackTrace(): Help to understand where the problem is actually
                           // happening.
    }
  }
}
