import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;

//This class implements the interface ActionListener so that it is called when the player makes a move. 
//It calculates the next step of the game
//It updates state and userInterface.


public class GameLogic implements ActionListener {

 // ADD YOUR INSTANCE VARIABLES HERE
BoardUserInterface board;
JButton reset;
JButton quit;
GameState state;
GameLogic gameLogic;
int [][] cubes;
GameUserInterface gameBoard;
int size;
 
    public GameLogic(int size) { //The parameter size is the size of the board on which the game will be played
    // YOUR CODE HERE
    this.size = size;
    state = new GameState(size);
    gameBoard = new GameUserInterface(state, this);
	// It creates the game's userInterface and the game's state instances
    }
  
    /**
     * Starts the game
     */
    public void start(){
        gameBoard.setTitle("Game - Catch the Mouse!"); //setting up the frame
        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoard.setResizable(false);
        gameBoard.setVisible(true);
	// YOUR CODE HERE
    }

    public int getSize(){
        return size;
    }

    /**
     * resets the game
     */
    public void reset(){
    // YOUR CODE HERE
    gameBoard.dispose(); //removing old frame
    state = new GameState(size); //declaring new game state
    gameBoard = new GameUserInterface(state, this); //creating new board
    gameBoard.getBoardUserInterface().update(); //updating blank gameboard with the brand new one
    
    }


    public void actionPerformed(ActionEvent e) {
	//the logic of the game goes in this method        
    // YOUR CODE HERE
    Cube clicked;
    if (e.getSource()==gameBoard.getQuit()){
        gameBoard.dispose();
        System.exit(0); //Quit screen
    }
    else if (e.getSource()==gameBoard.getReset()){
        reset(); //Reset screen

    }
    else{
        if (e.getSource() instanceof Cube){

            clicked = (Cube) e.getSource();
            int row = clicked.getRow();
            int column = clicked.getColumn(); //getting row and column of cube that was clicked


            if (state.getCurrentStatus(row, column)==GameState.FREE_CUBE){
                if (row%2!=0){ //If the row index is even and a free cube is clicked
                    if ((state.getCurrentStatus(row+1, column)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column)==GameState.SELECTED) || (state.getCurrentStatus(row,column+1)==GameState.SELECTED) || (state.getCurrentStatus(row,column-1)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column+1)==GameState.SELECTED) || (state.getCurrentStatus(row+1,column+1)==GameState.SELECTED)){
                        //setting the conditions for the surrounding six cubes
                        state.select(row, column);
                        clicked.setType(GameState.SELECTED); //change free cube to snake
                        state.mouseChange(); //move the mouse
                        while (state.getCurrentStatus(state.getCurrentCube().getX(), state.getCurrentCube().getY())==GameState.SELECTED){
                            state.mouseChange(); //if mouse takes the place of a snake, move it again
                        }
                    } 
                }
                else{ //If the row index is odd and a free cube is clicked
                    if ((state.getCurrentStatus(row+1, column)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column)==GameState.SELECTED) || (state.getCurrentStatus(row,column+1)==GameState.SELECTED) || (state.getCurrentStatus(row,column-1)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column-1)==GameState.SELECTED) || (state.getCurrentStatus(row+1,column-1)==GameState.SELECTED)){
                        //setting the conditions for the surrounding six cubes
                        state.select(row, column);
                        clicked.setType(GameState.SELECTED); //change free cube to snake
                        state.mouseChange(); //move the mouse
                        while (state.getCurrentStatus(state.getCurrentCube().getX(), state.getCurrentCube().getY())==GameState.SELECTED){
                            state.mouseChange(); //if mouse takes the place of a snake, move it again
                        }
                    } 
                } 
            } 
            else if (state.getCurrentStatus(row,column)==GameState.RED_CUBE){ //if red cube is clicked
                if (row%2==0){ //setting win conditions if row index is even
                    if ((state.getCurrentStatus(row+1, column)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column)==GameState.SELECTED) || (state.getCurrentStatus(row,column+1)==GameState.SELECTED) || (state.getCurrentStatus(row,column-1)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column-1)==GameState.SELECTED) || (state.getCurrentStatus(row+1,column-1)==GameState.SELECTED)){
                        JOptionPane.showMessageDialog(null, "You win!");  //show victory message
                        reset(); //restart
                        return;
                    }
                }
                else{ // setting win conditions if row index is odd
                    if ((state.getCurrentStatus(row+1, column)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column)==GameState.SELECTED) || (state.getCurrentStatus(row,column+1)==GameState.SELECTED) || (state.getCurrentStatus(row,column-1)==GameState.SELECTED) || (state.getCurrentStatus(row-1,column+1)==GameState.SELECTED) || (state.getCurrentStatus(row+1,column+1)==GameState.SELECTED)){
                        JOptionPane.showMessageDialog(null, "You win!");   //show victory message
                        reset(); //restart
                        return;
                    }
                }
            }
            else if (state.getCurrentStatus(row, column)==GameState.SELECTED){
            }
            
            gameBoard.getBoardUserInterface().update(); //Update the board
            gameBoard.repaint(); //redraw with updated board

            

            Point redCube = state.getCurrentCube(); //get current redcube
            int redCubeX = redCube.getX();
            int redCubeY = redCube.getY();
            state.setCube(redCubeX, redCubeY);  //set current redcube to the proper icon

            gameBoard.getBoardUserInterface().update(); //update board again with the moved mouse
            gameBoard.repaint(); //repaint window
        
            if (state.getCurrentCube().getX()==0 || state.getCurrentCube().getX()==size-1 || state.getCurrentCube().getY()==0 || state.getCurrentCube().getY()==size-1){
                JOptionPane.showMessageDialog(null, "You lose!"); // setting lose condition (if mouse reaches edge)
                reset(); //restart
                return;
            }
        }
    }
    
    
    }
 
}

