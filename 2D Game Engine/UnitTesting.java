/*************************************************************************
  *  Compilation:  javac Tile.java
  *  Execution:    java Tile
  *
  *  2D Game Engine's primary atomic object, utilized in the creation of
  *  TileBoard instances.
  * 
  *  Version 1.01
  *************************************************************************/

import static org.junit.Assert.*;
import org.junit.*;  

public class UnitTesting {
  // Test Tile object constructor
  @Test
  public void testTileConstructor() {
    Tile test = new Tile("test", false, false, true);
    assertEquals("test", test.getImage());
    assertEquals(false, test.getHasPlayer());
    assertEquals(false, test.getHasEnemy());
    assertEquals(true, test.getPassable());
  }
  
  // Test TileBoard object constructor
  @Test
  public void testTileBoardConstructor() {
    TileBoard test = new TileBoard(5, 6, "void");
    assertEquals(5, test.getNumTilesWidth());
    assertEquals(6, test.getNumTilesHeight());
    assertEquals("void", test.getPastBorderImage());
    
  }
  
  // Test TileBoard populate single tile
  @Test
  public void testTileBoardPopulateTile() {
    TileBoard test = new TileBoard(5, 6, "void");
    test.populateTile(0, 0, "mario.png", false, false, true);
    assertEquals("mario.png", test.getTileBoard()[0][0].getImage());
    assertEquals(false, test.getTileBoard()[0][0].getHasPlayer());
    assertEquals(false, test.getTileBoard()[0][0].getHasEnemy());
    assertEquals(true, test.getTileBoard()[0][0].getPassable());
    
  }
  
  // Test first player object constructor
  @Test
  public void testPlayerConstructor1() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    
    assertEquals("Mario.png", testPlayer.getImage());
    assertEquals(testGame.getGameAreas()[0], testPlayer.getBoard());
    assertEquals(2, testPlayer.getFirstTileIndex());
    assertEquals(2, testPlayer.getSecondTileIndex());
    assertEquals(7, testPlayer.getDisplayWidth());
    assertEquals(7, testPlayer.getDisplayHeight());
  }
  
  // Test second player object constructor
  @Test
  public void testPlayerConstructor2() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", 2, testGame.getGameAreas()[0], 2, 2, 7, 7);
    
    assertEquals("Mario.png", testPlayer.getImage());
    assertEquals(2, testPlayer.getFacingValue());
    assertEquals(testGame.getGameAreas()[0], testPlayer.getBoard());
    assertEquals(2, testPlayer.getFirstTileIndex());
    assertEquals(2, testPlayer.getSecondTileIndex());
    assertEquals(7, testPlayer.getDisplayWidth());
    assertEquals(7, testPlayer.getDisplayHeight());
    
  }
  
  // Test player up movement
  @Test
  public void testPlayerMoveUp() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    testPlayer.moveUp();    
    assertEquals(3, testPlayer.getSecondTileIndex());
  }
  
  // Test player down movement
  @Test
  public void testPlayerMoveDown() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    testPlayer.moveDown();
    assertEquals(1, testPlayer.getSecondTileIndex());
    
  }
  
  // Test player left movement
  @Test
  public void testPlayerMoveLeft() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    testPlayer.moveLeft();
    assertEquals(1, testPlayer.getFirstTileIndex());
    
  }
  
  // Test player right movement boolean retriever
  @Test
  public void testPlayerMoveRight() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    testPlayer.moveRight();
    assertEquals(3, testPlayer.getFirstTileIndex());
    
  }
  
  // Test player up movement boolean retriever
  @Test
  public void testPlayerCanMoveUp() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    assertEquals(true, testPlayer.canMoveUp());
    
  }
  
  // Test player down movement boolean retriever
  @Test
  public void testPlayerCanMoveDown() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    assertEquals(true, testPlayer.canMoveDown());
    
  }
  
  // Test player left movement boolean retriever
  @Test
  public void testPlayerCanMoveLeft() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    assertEquals(true, testPlayer.canMoveLeft());
    
  }
  
  // Test player right movement boolean retriever
  @Test
  public void testPlayerCanMoveRight() {
    Game testGame = new Game(1); 
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    Player testPlayer = new Player ("Mario.png", testGame.getGameAreas()[0], 2, 2, 7, 7);
    assertEquals(true, testPlayer.canMoveLeft());
    
    
  }
  
  // Test game object constructor
  @Test
  public void testGameConstructor() {
    Game testGame2 = new Game(3);
    assertEquals(3, testGame2.getGameAreas().length);
    
  }
  
  // Test game area insertion
  @Test
  public void testGameInsertGameaArea() {
    TileBoard test = new TileBoard(5, 6, "void");
    TileBoard test2 = new TileBoard(6, 5, "void");
    Game testGame3 = new Game(5);
    testGame3.insertGameArea(test);
    assertEquals(test, testGame3.getGameAreas()[0]);
    testGame3.insertGameArea(test2);
    assertEquals(test2, testGame3.getGameAreas()[1]);
  }
  
}