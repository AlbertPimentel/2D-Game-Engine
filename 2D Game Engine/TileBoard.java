/*  
 * Execution: N/A. Made up of Tiles. Allows manipulation of all Tiles at once.
 *            
 * 
 * Description: File containing the object: "Tileboard". Contains various
 * fields and methods pertaining to the characteristics of a Tileboard.
 * 
 * 
 */
public class TileBoard {
  public Tile[][] tileBoard;
  public int numTilesWidth;
  public int numTilesHeight;
  public String pastBorderImage;
  
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
  
  public void populateTile(int horizontalArrayIndex, int verticalArrayIndex,
                           String tileImg, boolean tileHasPlayer, boolean tileHasEnemy,
                           boolean passable) {
    this.tileBoard[horizontalArrayIndex][verticalArrayIndex] = new Tile(tileImg, 
                                                                        tileHasPlayer,  
                                                                        tileHasEnemy, passable);
  }
  
  /*
   * Description: Populates an entire TileBoard with a single image and default fields       
   *              
   * Input: A string; the filename of the image the board will be populated with.
   *        
   * Output: None.
   */ 
  public void populateEntireBoard(String imageFilename) {
    for(int i = 0; i < this.numTilesWidth; i++) {
      for(int j = 0; j < this.numTilesHeight; j++) {
        this.populateTile(i, j, imageFilename, false, false, true);
      }
    }
  }
}