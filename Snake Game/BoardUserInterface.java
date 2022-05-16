import javax.swing.*;
import java.awt.*;

public class BoardUserInterface extends JPanel {

    //YOUR INSTANCE VARIABLES HERE
	//private ...
	//private ...
  private Cube[][] cube; //declaring instance variables
  private JPanel[] panel; //declaring instance variables
  int [][] cubes;
  GameState gameState; //declaring instance variables
  GameLogic gameLogic; //declaring instance variables
  private int size;

  public BoardUserInterface(GameState gameState, GameLogic gameLogic) {
	//Your code goes here

  this.gameState = gameState;
  this.gameLogic = gameLogic;

  cube = new Cube[gameState.getSize()][gameState.getSize()]; //Create cubes array
  size = gameState.getSize();

  setLayout(new GridLayout(size, 1)); //Creating overall JFrame layout manager
  
  panel = new JPanel [size]; //Panels array for each row

  for (int i = 0; i<size; i++){
    panel[i] = new JPanel(new FlowLayout()); //Layout manager for each row
    if (i%2==0){
      panel[i].setBorder(BorderFactory.createEmptyBorder(0,5,0,75)); //Indenting every other row
    }
    for (int j = 0; j<size; j++){
      int type = this.gameState.getCurrentStatus(i, j);
      cube[i][j] = new Cube(i, j, type); //FIX
      cube[i][j].setType(type);
      cube[i][j].addActionListener(gameLogic);
      cube[i][j].setMargin(new Insets(0, 0, 0, 0));
      panel[i].add(cube[i][j]); //Creating cube button, setting its type and adding the action listener, then adding it to the row panel
    }
    add(panel[i]); //Adding the row panel to the frame
  }
  //method that shifts line left or right when compared to the previous line
}

  //updates the status of the board's cubes instances following the game state
	//calls setType() from the class Cube, as needed.
    public void update(){ 
      for (int i = 0; i<size; i++){ //Loop through cubes
        for (int j = 0; j<size; j++){
          int type = gameState.getCurrentStatus(i, j); //Check what type of cube it is
          cube[i][j].setType(type); //Update the cube
        }
      }
		//Your code goes here
        }

}

