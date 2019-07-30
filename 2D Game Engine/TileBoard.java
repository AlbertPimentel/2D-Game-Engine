/*************************************************************************
  *  Compilation:  javac TileBoard.java
  *  Execution:    java TileBoard
  *
  *  2D Game Engine's secondary atomic object. Made up of Tiles, allows for
  *  the manipulation of many Tiles at once. Used to create a Game object and
  *  Player object.
  * 
  *  Version 1.01
  *************************************************************************/
public class TileBoard {
  private Tile[][] tileBoard;
  private int numTilesWidth;
  private int numTilesHeight;
  private String pastBorderImage;
  
  // The Tileboard is an 2D-Array of tiles, in which the lower left tile is at [0][0]
  // and the upper right tile is at [height][width]
  // User's screen resolution is automatically detected and used to set window size
  public TileBoard(int numTilesWidth, int numTilesHeight, String pastBorderImage) {
    this.numTilesWidth = numTilesWidth;
    this.numTilesHeight = numTilesHeight;
    this.tileBoard = new Tile [numTilesWidth][numTilesHeight]; // Tileboard dimensions
    this.pastBorderImage = pastBorderImage; // What image is drawn in place of tiles 
    // beyond the tileboard's bounds?
  }
  
  /**
   * Retrieves given TileBoard's width dimension.
   */
  public int getNumTilesWidth() {
    return this.numTilesWidth;
  }
  
  /**
   * Sets given TileBoard's width dimension.
   * @param numTilesWidth, width dimension to be used
   */
  public void setNumTilesWidth(int numTilesWidth) {
    this.numTilesWidth = numTilesWidth;
  }
  
  /**
   * Retrieves given TileBoard's height dimension.
   */
  public int getNumTilesHeight() {
    return this.numTilesHeight;
  }
  
  /**
   * Sets given TileBoard's height dimension.
   * @param numTilesHeight, height dimension to be used
   */
  public void setNumTilesHeight(int numTilesHeight) {
    this.numTilesHeight = numTilesHeight;
  }
  
  /**
   * Retrieves given TileBoard's Tile[][].
   */
  public Tile[][] getTileBoard() {
    return this.tileBoard;
  }
  
  /**
   * Retrieves given void tile filename.
   */
  public String getPastBorderImage() {
    return this.pastBorderImage;
  }
  
  /**
   * Sets given TileBoard's void tile filename.
   * @param pastBorderImage, filename to be used
   */
  public void setPastBorderImage(String pastBorderImage) {
    this.pastBorderImage = pastBorderImage;
  }
  
  /**
   * Creates a new tile with inputted fields and inserts said tile into an inputted
   * location within the TileBoard.
   * @param horizontalArrayIndex, the horizontal index of where the tile is within
   * the TileBoard's 2D Tile array
   * @param verticalArrayIndex, the vertical index of where the tile is within
   * the TileBoard's 2D Tile array
   * @param tileImg, the image the inputted tile should have
   * @param tileHasPlayer, whether or not the tile has a player on it
   * @param tileHasEnemy, whether or not the tile has an enemy on it 
   * @param passable, whether or not the tile is passable
   */
  public void populateTile(int horizontalArrayIndex, int verticalArrayIndex,
                           String tileImg, boolean tileHasPlayer, boolean tileHasEnemy,
                           boolean passable) {
    this.tileBoard[horizontalArrayIndex][verticalArrayIndex] = new Tile(tileImg, 
                                                                        tileHasPlayer,  
                                                                        tileHasEnemy, passable);
  }
  
  /**
   * Populates an entire TileBoard with a single image and default fields.
   * @param imageFilename, the filename of the image the board will be populated with
   */
  public void populateEntireBoard(String imageFilename) {
    for(int i = 0; i < this.numTilesWidth; i++) {
      for(int j = 0; j < this.numTilesHeight; j++) {
        this.populateTile(i, j, imageFilename, false, false, true);
      }
    }
  }
}