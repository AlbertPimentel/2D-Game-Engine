/*  
 * Execution: N/A. Run the program to simulate an example of the
 *            engine's use.
 * 
 * Description: This class showcases one example of the game engine's use.
 *              Here, a new Game is created and populated, and the Player is able
 *              to move around.
 * 
 */
public class SampleClass {
  public static void main(String [] args) {
    
    // STEP 1: Create a game object. Decide how many tileboards you want to have.
    Game testGame = new Game(1); 
    
    // STEP 2: Insert a 7x7 tileboard with a block texture as its primary tile and lava as its void tile.
    testGame.insertGameArea(7, 7, "Block.png", "Lava.png");
    
    // STEP 3: Create a new Player object. Assign it an image, a Tileboard to be placed on,
    // a specific [][] index to be placed on, and how large its display will be.
    // This player is placed on the first game area, on tile [2][2] with a 7x7 tile display around it
    Player stickFigure = new Player ("Mario.png", testGame.gameAreas[0], 2, 2, 7, 7);
    testGame.drawPlayerDisplay(stickFigure);
    
    while(true) {
      if(PennDraw.hasNextKeyTyped()) {
        String input = PennDraw.nextKeyTyped() + "";
        if(input.equals("w") || input.equals("W")) {
          testGame.movementUp(stickFigure);
          
        }   
        if(input.equals("a") || input.equals("A")) {
          testGame.movementLeft(stickFigure);
          
        }    
        if(input.equals("s") || input.equals("S")) {
          testGame.movementDown(stickFigure);
          
        }        
        if(input.equals("d") || input.equals("D")) {
          testGame.movementRight(stickFigure);
        }
      }
    }
  }
}