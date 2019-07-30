/*************************************************************************
  *  Compilation:  javac Game.java
  *  Execution:    java Game
  *
  *  2D Game Engine's tertiary atomic object. Each Game may contain one or
  *  more "areas", which are TileBoards. This class connects the Player object
  *  to the Tile and TileBoard objects.
  * 
  *  Version 1.01
  *************************************************************************/

import java.awt.Toolkit;
import java.awt.Dimension;

public class Game {
  private int width;
  private int height;
  private TileBoard[] gameAreas; // given set of game areas (each being a tileboard)
  private int boardCounter; // used for determining if gameArea array is full
  
  // numGameAreas is how many tileboards this game has
  // Obtains user's screen resolution automatically
  // Ex. for a 1920x1080 display, width = 1920, height = 1080
  public Game(int numGameAreas) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.width = (int) screenSize.getWidth();
    this.height = (int) screenSize.getHeight();
    
    PennDraw.setCanvasSize(width, height); 
    PennDraw.setXscale(0, width);
    PennDraw.setYscale(0, height);
    
    this.gameAreas = new TileBoard[numGameAreas]; 
  }
  
  /**
   * Retrieves a given Game's game areas.
   */
  public TileBoard[] getGameAreas() {
    return this.gameAreas;
  }
  
  /**
   * Populates an entire TileBoard with a single image and default fields  
   * @param board, the TileBoard to be populated
   * @param imageFilename, the name of the filler tile
   */
  private static void populateEntireBoard(TileBoard board, String imageFilename) {
    for(int i = 0; i < board.getNumTilesWidth(); i++) {
      for(int j = 0; j < board.getNumTilesHeight(); j++) {
        board.populateTile(i, j, imageFilename, false, false, true);
      }
    }
  }
  
  /**
   * Draws the Tile display around the Player. 
   * @param player, the player in question
   */
  public void drawPlayerDisplay(Player player) {
    double pixelPerTileWidth = this.width / player.getDisplayWidth();
    double pixelPerTileHeight = this.height / player.getDisplayHeight();
    
    if(player.getDisplayWidth() % 2 == 0 || player.getDisplayWidth() <= 0) {
      throw new RuntimeException("Display square side length must be an odd, natural number");
    }
    
    if(player.getDisplayHeight() % 2 == 0 || player.getDisplayHeight() <= 0) {
      throw new RuntimeException("Display square side length must be an odd, natural number");
    }
    
    // Draws numTilesWidth by numTilesHeight rectangle around player, such that said rectangle occupies
    // entire screen.
    for(int a = -(player.getDisplayWidth() - 1) / 2; a < ((player.getDisplayWidth() - 1) / 2) + 1; a++) {
      for(int b = -(player.getDisplayHeight() - 1) / 2; b < ((player.getDisplayHeight()- 1) / 2) + 1; b++) {
        // If tile is past tileboard's bounds, draw past border image.
        if(a + player.getFirstTileIndex() < 0  ||  a + player.getFirstTileIndex() >=
           player.getBoard().getNumTilesWidth() || b + player.getSecondTileIndex() < 0 ||
           b + player.getSecondTileIndex() >= player.getBoard().getNumTilesHeight()) {
          PennDraw.picture(((a + ((player.getDisplayWidth() - 1) / 2)) * pixelPerTileWidth) +
                           (pixelPerTileWidth / 2), ((b + ((player.getDisplayHeight() - 1) / 2)) *
                                                     pixelPerTileHeight) + (pixelPerTileHeight / 2),
                           player.getBoard().getPastBorderImage(),
                           pixelPerTileWidth, pixelPerTileHeight);
        }
        
        else {
          // If tile is not past tileboard's bounds, draw tile's image.
          PennDraw.picture(((a + ((player.getDisplayWidth() - 1) / 2)) * pixelPerTileWidth) +
                           (pixelPerTileWidth / 2), ((b + ((player.getDisplayHeight() - 1) / 2)) *
                                                     pixelPerTileHeight) + (pixelPerTileHeight / 2),
                           player.getBoard().getTileBoard()
                             [a + player.getFirstTileIndex()][b + player.getSecondTileIndex()].getImage(),
                           pixelPerTileWidth, pixelPerTileHeight);
        }
      }
    }
    // Draws player in the center of the display rectangle.
    PennDraw.picture((((player.getDisplayWidth() - 1) / 2) * pixelPerTileWidth) + (pixelPerTileWidth / 2),
                     (((player.getDisplayHeight() - 1) / 2) * pixelPerTileHeight) + (pixelPerTileHeight / 2),
                     player.getImage(), pixelPerTileWidth, pixelPerTileHeight);
  }
  
  /**
   * Moves a Player left within the given Game's Tileboard. 
   * @param player, the player in question
   */
  public void movementLeft(Player player) {
    if(player.canMoveLeft()) {
      player.moveLeft();
      this.drawPlayerDisplay(player);
    }
  }
  
  /**
   * Moves a Player right within the given Game's Tileboard. 
   * @param player, the player in question
   */
  public void movementRight(Player player) {
    if(player.canMoveRight()) {
      player.moveRight();
      this.drawPlayerDisplay(player);
    }
  }
  
  /*
   /**
   * Moves a Player up within the given Game's Tileboard. 
   * @param player, the player in question
   */  
  public void movementUp(Player player) {
    if(player.canMoveUp()) {
      player.moveUp();
      this.drawPlayerDisplay(player);
    }
  }
  
  /**
   * Moves a Player down within the given Game's Tileboard. 
   * @param player, the player in question
   */
  public void movementDown(Player player) {
    if(player.canMoveDown()) {
      player.moveDown();
      this.drawPlayerDisplay(player);
    }
  }
  
  /**
   * Inserts a TileBoard into the given Game.
   * @param board, the TileBoard in question
   */
  public void insertGameArea(TileBoard board) {
    if(this.boardCounter >= this.gameAreas.length) {
      throw new RuntimeException("This game cannot contain any more game areas");
    }
    else {
      this.gameAreas[boardCounter] = board;
      boardCounter++;
    }
  }
  
  /**
   * Creates a new Tileboard instance and inserts said instance into the given Game.
   * @param gameAreaTileWidth, desired Tileboard width dimension
   * @param gameAreaTileHeight, desired Tileboard height dimension
   * @param fillerTileFilename, name of the image to be used for populating filler tiles
   * @param voidTileFilename, name of the image to be used for populating void (outer) tiles
   */
  public void insertGameArea(int gameAreaTileWidth, int gameAreaTileHeight, String fillerTileFilename,
                             String voidTileFilename) {
    TileBoard area = new TileBoard(gameAreaTileWidth, gameAreaTileHeight, voidTileFilename);
    area.populateEntireBoard(fillerTileFilename);
    this.insertGameArea(area);  
    
  }
}