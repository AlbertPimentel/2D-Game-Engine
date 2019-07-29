import java.awt.Toolkit;
import java.awt.Dimension;

public class Game {
  public int width;
  public int height;
  public TileBoard[] gameAreas; // given set of game areas (each being a tileboard)
  public int boardCounter; // used for determining if gameArea array is full
  
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
  
  /*
   * Description: Populates an entire TileBoard with a single image and default fields       
   *              
   * Input: A Tileboard to be populated and a String (the filename of filler tile).
   *        
   * Output: None.
   */ 
  public static void populateEntireBoard(TileBoard board, String imageFilename) {
    for(int i = 0; i < board.numTilesWidth; i++) {
      for(int j = 0; j < board.numTilesHeight; j++) {
        board.populateTile(i, j, imageFilename, false, false, true);
      }
    }
  }
  
  /*
   * Description: Draws the Tile display around the Player.     
   *              
   * Input: A Player.
   *        
   * Output: None.
   */ 
  public void drawPlayerDisplay(Player player) {
    double pixelPerTileWidth = this.width / player.displayWidth;
    double pixelPerTileHeight = this.height / player.displayHeight;
    
    if(player.displayWidth % 2 == 0 || player.displayWidth <= 0) {
      throw new RuntimeException("Display square side length must be an odd, natural number");
    }
    
    if(player.displayHeight % 2 == 0 || player.displayHeight <= 0) {
      throw new RuntimeException("Display square side length must be an odd, natural number");
    }
    
    // Draws numTilesWidth by numTilesHeight rectangle around player, such that said rectangle occupies
    // entire screen.
    for(int a = -(player.displayWidth - 1) / 2; a < ((player.displayWidth - 1) / 2) + 1; a++) {
      for(int b = -(player.displayHeight - 1) / 2; b < ((player.displayHeight- 1) / 2) + 1; b++) {
        // If tile is past tileboard's bounds, draw past border image.
        if(a + player.firstTileIndex < 0  ||  a + player.firstTileIndex >=
           player.board.tileBoard.length || b + player.secondTileIndex < 0 ||
           b + player.secondTileIndex >= player.board.tileBoard[0].length) {
          PennDraw.picture(((a + ((player.displayWidth - 1) / 2)) * pixelPerTileWidth) +
                           (pixelPerTileWidth / 2), ((b + ((player.displayHeight - 1) / 2)) *
                                                     pixelPerTileHeight) + (pixelPerTileHeight / 2),
                           player.board.pastBorderImage,
                           pixelPerTileWidth, pixelPerTileHeight);
        }
        
        else {
          // If tile is not past tileboard's bounds, draw tile's image.
          PennDraw.picture(((a + ((player.displayWidth - 1) / 2)) * pixelPerTileWidth) +
                           (pixelPerTileWidth / 2), ((b + ((player.displayHeight - 1) / 2)) *
                                                     pixelPerTileHeight) + (pixelPerTileHeight / 2),
                           player.board.tileBoard[a + player.firstTileIndex][b + player.secondTileIndex].image,
                           pixelPerTileWidth, pixelPerTileHeight);
        }
      }
    }
    // Draws player in the center of the display rectangle.
    PennDraw.picture((((player.displayWidth - 1) / 2) * pixelPerTileWidth) + (pixelPerTileWidth / 2),
                     (((player.displayHeight - 1) / 2) * pixelPerTileHeight) + (pixelPerTileHeight / 2),
                     player.image, pixelPerTileWidth, pixelPerTileHeight);
  }
  
  /*
   * Description: Moves a Player left within the given Game's Tileboard.    
   *              
   * Input: A Player.
   *        
   * Output: None.
   */ 
  public void movementLeft(Player player) {
    if(player.canMoveLeft()) {
      player.moveLeft();
      this.drawPlayerDisplay(player);
    }
  }
  
  /*
   * Description: Moves a Player right within the given Game's Tileboard.    
   *              
   * Input: A Player.
   *        
   * Output: None.
   */ 
  public void movementRight(Player player) {
    if(player.canMoveRight()) {
      player.moveRight();
      this.drawPlayerDisplay(player);
    }
  }
  
  /*
   * Description: Moves a Player up within the given Game's Tileboard.    
   *              
   * Input: A Player.
   *        
   * Output: None.
   */   
  public void movementUp(Player player) {
    if(player.canMoveUp()) {
      player.moveUp();
      this.drawPlayerDisplay(player);
    }
  }
  
  /*
   * Description: Moves a Player down within the given Game's Tileboard.    
   *              
   * Input: A Player.
   *        
   * Output: None.
   */ 
  public void movementDown(Player player) {
    if(player.canMoveDown()) {
      player.moveDown();
      this.drawPlayerDisplay(player);
    }
  }
  
  /*
   * Description: Inserts a Tileboard into the given Game.      
   *              
   * Input: A Tileboard.
   *        
   * Output: None.
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
  
  /*
   * Description: Creates a new Tileboard instance and inserts said instance into the given Game.     
   *              
   * Input: Two integers, two strings. Integers are the desired Tileboard dimensions, and strings
   *        are the filenames of the filler and void tile images.
   *        
   * Output: None.
   */ 
  public void insertGameArea(int gameAreaTileWidth, int gameAreaTileHeight, String fillerTileFilename,
                             String voidTileFilename) {
    TileBoard area = new TileBoard(gameAreaTileWidth, gameAreaTileHeight, voidTileFilename);
    area.populateEntireBoard(fillerTileFilename);
    this.insertGameArea(area);  
    
  }
}