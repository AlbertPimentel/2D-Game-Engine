public class Player {
  public String image;
  public int facingValue; // 0 to 3 value indicating which of 4 directions the player is facing.
  // 0 indicates facing up, 1 facing right, 2 facing down, 3 facing left.
  public TileBoard board; // On which tileboard (game areas) is this player on?
  public int firstTileIndex; // Index of first array of 2D array of Tileboard on which player starts
  public int secondTileIndex; // Index of second array of 2D array of Tileboard on which player starts
  public int displayHeight; // Number of tiles along the height of the display around the player
  public int displayWidth; // Number of tiles along the width of the display around the player
  
  /*
   * Description: Creates a new player object.
   *             
   *              
   * Input: Name of player's image file, an integer representing its direction,
   *        the tileboard on which it is placed, as well as its tile coordinates.
   * 
   * Output: None.
   */ 
  // Creates a new Player object with the default facing position, 0 (up).
  public Player(String image, TileBoard board, int firstTileIndex, int secondTileIndex,
                int displayHeight, int displayWidth) {
    // Player's starting position cannot be outside of the board's bounds. 
    if(firstTileIndex >= board.tileBoard.length || secondTileIndex >= board.tileBoard[0].length) {
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
    
    this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = true;
    this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = false;
    
    this.displayHeight = displayHeight;
    this.displayWidth = displayWidth;
    
  }
  
// Creates a new Player object with a custom facing position (0-3).
  public Player(String image, int facingValue, TileBoard board, int firstTileIndex, int secondTileIndex,
                int displayHeight, int displayWidth) {
    // Player's starting position cannot be outside of the board's bounds. 
    if(firstTileIndex >= board.tileBoard.length || secondTileIndex >= board.tileBoard[0].length) {
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
    
    this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = true;
    this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = false;
    
    this.displayHeight = displayHeight;
    this.displayWidth = displayWidth;
    
  }
  
  /*
   * Description: Allows the player to move up on the tileboard.        
   *              
   * Input: None.
   *        
   * Output: None.
   */ 
  public void moveUp() {
    // If above tile goes beyond the board's bounds or above tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.secondTileIndex + 1 >= this.board.tileBoard[0].length ||
       !(this.board.tileBoard[this.firstTileIndex][this.secondTileIndex + 1].passable)) {
      return;
    }
    
    else {
      // Set currently occupied tile to unoccupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = false;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = true;
      
      // Occupied tile is now the above tile.
      this.secondTileIndex = this.secondTileIndex + 1;
      
      // Occupied tile is now occupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = true;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = false;
    }
  }
  
  /*
   * Description: Allows the player to move down on the tileboard.         
   *              
   * Input: None.
   *        
   * Output: None.
   */ 
  public void moveDown() {
    // If below tile goes beyond the board's bounds or below tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.secondTileIndex - 1 < 0 || 
       !(this.board.tileBoard[this.firstTileIndex][this.secondTileIndex - 1].passable)) {
      return;
    }
    else {
      // Set currently occupied tile to unoccupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = false;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = true;
      
      // Occupied tile is now the above tile.
      this.secondTileIndex = this.secondTileIndex - 1;
      
      // Occupied tile is now occupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = true;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = false;
    }
  }
  
  /*
   * Description: Allows the player to move left on the tileboard.
   *                       
   * Input: None.
   *        
   * Output: None.
   */ 
  public void moveLeft() {
    // If left tile goes beyond the board's bounds or left tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.firstTileIndex - 1 < 0 ||
       !(this.board.tileBoard[this.firstTileIndex - 1][this.secondTileIndex].passable)) {
      return;
    }
    else {
      // Set currently occupied tile to unoccupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = false;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = true;
      
      // Occupied tile is now the above tile.
      this.firstTileIndex = this.firstTileIndex - 1;
      
      // Occupied tile is now occupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = true;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = false;
    }
  }
  
  /*
   * Description: Allows the player to move right on the tileboard.        
   *              
   * Input: None.
   *        
   * Output: None.
   */ 
  public void moveRight() {
    // If right tile goes beyond the board's bounds or right tile is unpassable
    // (has player/enemy/wall), do nothing.
    if(this.firstTileIndex + 1 >= this.board.tileBoard.length ||
       !(this.board.tileBoard[this.firstTileIndex + 1][this.secondTileIndex].passable)) {
      return; 
    }
    else {
      // Set currently occupied tile to unoccupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = false;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = true;
      
      // Occupied tile is now the above tile.
      this.firstTileIndex = this.firstTileIndex + 1;
      
      // Occupied tile is now occupied.
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].hasPlayer = true;
      this.board.tileBoard[this.firstTileIndex][this.secondTileIndex].passable = false;
    }
  }
  
  /*
   * Description: Checks if the player in question is able to move left on the tileboard.
   *                     
   * Input: None.
   *        
   * Output: A boolean.
   */ 
  public boolean canMoveLeft() {
    if(this.firstTileIndex - 1 >= 0) {
      return true; 
    }
    else {
      return false;
    }
  }
  
  /*
   * Description: Checks if the player in question is able to move right on the tileboard.
   *                       
   * Input: None.
   *        
   * Output: A boolean.
   */ 
  public boolean canMoveRight() {
    if(this.firstTileIndex + 1 < this.board.tileBoard.length) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /*
   * Description: Checks if the player in question is able to move up on the tileboard.
   *                        
   * Input: None.
   *        
   * Output: A boolean.
   */ 
  public boolean canMoveUp() {
    if(this.secondTileIndex + 1 < this.board.tileBoard[0].length) {
      return true;
    }
    else {
      return false;
    }
  }
  
  /*
   * Description: Checks if the player in question is able to move down on the tileboard.         
   *              
   * Input: None.
   *        
   * Output: A boolean.
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