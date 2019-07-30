/*************************************************************************
  *  Compilation:  javac Tile.java
  *  Execution:    java Tile
  *
  *  2D Game Engine's primary atomic object, utilized in the creation of
  *  TileBoard instances.
  * 
  *  Version 1.01
  *************************************************************************/
public class Tile {
  private String image;
  private boolean hasPlayer;
  private boolean hasEnemy;
  private boolean passable;
  
  public Tile(String img, boolean hasPlayer, boolean hasEnemy, boolean passable)  {
    this.image = img;
    this.hasPlayer = hasPlayer;
    this.hasEnemy = hasEnemy;
    this.passable = passable;
  }
  
  /**
   * Retrieves the given tile's image name.
   */
  public String getImage() {
    return this.image;
  }
  
  /**
   * Sets the given tile's image to the inputted image name.
   * @param img, the filename of the image the tile's image will be set to
   */
  public void setImage(String img) {
    this.image = img;
  }
  
  /**
   * Retrieves whether or not tile has a player on it.
   */
  public boolean getHasPlayer() {
    return this.hasPlayer;
  }
  
  /**
   * Changes whether a tile has a player on it.
   * @param hasPlayer, whether or not tile has a player on it
   */
  public void setHasPlayer(boolean hasPlayer) {
    this.hasPlayer = hasPlayer;
  }
  
  /**
   * Retrieves whether or not tile has an enemy on it.
   */
  public boolean getHasEnemy() {
    return this.hasEnemy;
  }
  
  /**
   * Changes whether a tile has an enemy on it.
   * @param hasEnemy, whether or not tile has an enemy on it
   */
  public void setHasEnemy(boolean hasEnemy) {
    this.hasEnemy = hasEnemy;
  }
  
  /**
   * Retrieves the given tile's image name.
   */
  public boolean getPassable() {
    return this.passable;
  }
  
  /**
   * Changes whether a tile is passable.
   * @param passable, whether or not tile is passable
   */
  public void setPassable(boolean passable) {
    this.passable = passable;
  }
}