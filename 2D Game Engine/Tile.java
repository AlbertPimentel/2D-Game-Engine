/*  
 * Execution: N/A. Is the engine's atomic object; utilized in creating the
 *            Tileboard object.
 *            
 * 
 * Description: File containing the object: "Tile". Contains various
 * fields and methods pertaining to the characteristics of a Tile.
 * 
 * 
 */
public class Tile {
  public String image;
  public boolean hasPlayer;
  public boolean hasEnemy;
  public boolean passable;
  
  public Tile(String img, boolean hasPlayer, boolean hasEnemy, boolean passable)  {
    this.image = img;
    this.hasPlayer = hasPlayer;
    this.hasEnemy = hasEnemy;
    this.passable = passable;
  }
  
  /*
   * Description: Populates an entire TileBoard with a single image and default fields       
   *              
   * Input: A string; the filename of the image the board will be populated with.
   *        
   * Output: None.
   */ 
  public void changeImage(String img) {
    this.image = img;
  }
  
  /*
   * Description: Changes whether a tile is passable.     
   *              
   * Input: A boolean; the desired truth value of the tile's passability.
   *        
   * Output: None.
   */ 
  public void changePassability(boolean passable) {
    this.passable = passable;
  }
}