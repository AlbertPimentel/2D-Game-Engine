/*************************************************************************
  *  Compilation:  javac Player.java
  *  Execution:    java Player
  *
  *  This class contains fields and methods pertaining to the user-controlled
  *  object, "Player" which is placed on TileBoard that it can move on.
  * 
  *  Version 1.01
  *************************************************************************/

public class Player {
  private String image;
  private int facingValue; // 0 to 3 value indicating which of 4 directions the player is facing.
  // 0 indicates facing up, 1 facing right, 2 facing down, 3 facing left.
  private TileBoard board; // On which tileboard (game areas) is this player on?
  private int firstTileIndex; // Index of first array of 2D array of Tileboard on which player starts
  private int secondTileIndex; // Index of second array of 2D array of Tileboard on which player starts
  private int displayWidth; // Number of tiles along the width of the display around the player
  private int displayHeight; // Number of tiles along the height of the display around the player
  
  /**
   * Creates a new Player object with inputted fields and the default facing position, 0
   * or up.
   * @param image, the name of the image to be drawn at the player's location
   * @param facingValue, the direction the player is facing 
   * @param board, the TileBoard the player is currently on
   * @param firstTileIndex, the first index of the tile on which the player first spawns
   * @param secondTileIndex, the second index of the tile on which the player first spawns
   * @param displayWidth, the number of tiles along the width to be displayed centered around the player
   * @param displayHeight, the number of tiles along the height to be displayed centered around the player
   */
  public Player(String image, TileBoard board, int firstTileIndex, int secondTileIndex,
                int displayWidth, int displayHeight) {
    // Player's starting position cannot be outside of the board's bounds. 
    if(firstTileIndex >= board.getTileBoard().length || secondTileIndex >= board.getTileBoard()[0].length) {
      throw new RuntimeException("Starting player index must not be outside of game area's bounds.");
    }
    
    if(displayHeight % 2 == 0 || displayHeight < 0 || displayWidth % 2 == 0 || displayWidth < 0) {
      throw new RuntimeException("Both display lengths must be an odd, natural numbers");         
    }
    
    this.image = image;
    this.facingValue = 0;
    this.board = board;
    this.firstTileIndex = firstTileIndex;
    this.secondTileIndex = secondTileIndex;
    
    this.board.getTileBoard()[firstTileIndex][secondTileIndex].setHasPlayer(true);
    this.board.getTileBoard()[firstTileIndex][secondTileIndex].setPassable(false);
    
    this.displayWidth = displayWidth;
    this.displayHeight = displayHeight;
  }
  
  /**
   * Creates a new Player object with inputted fields and a custom facing position (0-3).
   * @param image, the name of the image to be drawn at the player's location
   * @param facingValue, the direction the player is facing 
   * @param board, the TileBoard the player is currently on
   * @param firstTileIndex, the first index of the tile on which the player first spawns
   * @param secondTileIndex, the second index of the tile on which the player first spawns
   * @param displayWidth, the number of tiles along the width to be displayed centered around the player
   * @param displayHeight, the number of tiles along the height to be displayed centered around the player
   */
  public Player(String image, int facingValue, TileBoard board, int firstTileIndex, int secondTileIndex,
                int displayWidth, int displayHeight) {
    // Player's starting position cannot be outside of the board's bounds. 
    if(firstTileIndex >= board.getTileBoard().length || secondTileIndex >= board.getTileBoard()[0].length) {
      throw new RuntimeException("Starting player index must not be outside of game area's bounds.");
    }
    
    if(displayHeight % 2 == 0 || displayHeight < 0 || displayWidth % 2 == 0 || displayWidth < 0) {
      throw new RuntimeException("Both display lengths must be an odd, natural numbers");         
    }
    
    this.image = image;
    this.facingValue = facingValue;
    this.board = board;
    this.firstTileIndex = firstTileIndex;
    this.secondTileIndex = secondTileIndex;
    
    this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(true);
    this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(false);
    
    this.displayWidth = displayWidth;
    this.displayHeight = displayHeight;
  }
  
  /**
   * Retrieves the given player's image name.
   */
  public String getImage() {
    return this.image;
  }
  
  /**
   * Retrieves the direction player is facing.
   */
  public int getFacingValue() {
    return this.facingValue;
  }
  
  /**
   * Retrieves the given player's TileBoard.
   */
  public TileBoard getBoard() {
    return this.board;
  }
  
  /**
   * Retrieves the first index of the given player's 
   * location on its respective TileBoard.
   */
  public int getFirstTileIndex() {
    return this.firstTileIndex;
  }
  
  /**
   * Retrieves the second index of the given player's 
   * location on its respective TileBoard/
   */
  public int getSecondTileIndex() {
    return this.secondTileIndex;
  }
  
  /**
   * Retrieves the number of tiles along the width of
   * the display around the player
   */
  public int getDisplayWidth() {
    return this.displayWidth;
  }
  
  /**
   * Retrieves the number of tiles along the height of
   * the display around the player
   */
  public int getDisplayHeight() {
    return this.displayHeight;
  }
  
  /**
   * Moves the player 1 space up on its respective TileBoard, if possible.
   */
  public void moveUp() {
    // If above tile goes beyond the board's bounds or above tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.secondTileIndex + 1 >= this.board.getTileBoard()[0].length ||
       !(this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex + 1].getPassable())) {
      return;
    }
    
    else {
      // Set currently occupied tile to unoccupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(false);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(true);
      
      // Occupied tile is now the above tile.
      this.secondTileIndex = this.secondTileIndex + 1;
      
      // Occupied tile is now occupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(true);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(false);
    }
  }
  
  /**
   * Moves the player 1 space down on its respective TileBoard, if possible.
   */
  public void moveDown() {
    // If below tile goes beyond the board's bounds or below tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.secondTileIndex - 1 < 0 || 
       !(this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex - 1].getPassable())) {
      return;
    }
    else {
      // Set currently occupied tile to unoccupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(false);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(true);
      
      // Occupied tile is now the above tile.
      this.secondTileIndex = this.secondTileIndex - 1;
      
      // Occupied tile is now occupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(true);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(false);
    }
  }
  
  /**
   * Moves the player 1 space to the left on its respective TileBoard, if possible.
   */
  public void moveLeft() {
    // If left tile goes beyond the board's bounds or left tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.firstTileIndex - 1 < 0 ||
       !(this.board.getTileBoard()[this.firstTileIndex - 1][this.secondTileIndex].getPassable())) {
      return;
    }
    else {
      // Set currently occupied tile to unoccupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(false);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(true);
      
      // Occupied tile is now the above tile.
      this.firstTileIndex = this.firstTileIndex - 1;
      
      // Occupied tile is now occupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(true);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(false);
    }
  }
  
  /**
   * Moves the player 1 space to the right on its respective TileBoard, if possible.
   */
  public void moveRight() {
    // If right tile goes beyond the board's bounds or right tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.firstTileIndex + 1 >= this.board.getTileBoard().length ||
       !(this.board.getTileBoard()[this.firstTileIndex + 1][this.secondTileIndex].getPassable())) {
      return; 
    }
    else {
      // Set currently occupied tile to unoccupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(false);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(true);
      
      // Occupied tile is now the above tile.
      this.firstTileIndex = this.firstTileIndex + 1;
      
      // Occupied tile is now occupied.
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setHasPlayer(true);
      this.board.getTileBoard()[this.firstTileIndex][this.secondTileIndex].setPassable(false);
    }
  }
  
  /**
   * Checks if the player in question is able to move left on the tileboard.
   */
  public boolean canMoveLeft() {
    if(this.firstTileIndex - 1 >= 0) {
      return true; 
    }
    else {
      return false;
    }
  }
  
  /**
   * Checks if the player in question is able to move right on the tileboard.
   */
  public boolean canMoveRight() {
    if(this.firstTileIndex + 1 < this.board.getTileBoard().length) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * Checks if the player in question is able to move up on the tileboard.
   */
  public boolean canMoveUp() {
    if(this.secondTileIndex + 1 < this.board.getTileBoard()[0].length) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * Checks if the player in question is able to move down on the tileboard.
   */
  public boolean canMoveDown() {
    if(this.secondTileIndex - 1 >= 0) {
      return true;
    }
    else {
      return false;
    }
  }
}