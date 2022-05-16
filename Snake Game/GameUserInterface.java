import javax.swing.*;
import java.awt.*;

 /*the class GameUserInterface provides the user interface of the Game. It extends
 JFrame and lays out an instance of BoardUserInterface and  two instances of JButton: one to reset and the second the quit the game at any time.*/

public class GameUserInterface extends JFrame {

	private JButton reset;
	private JButton quit;
	// ADD YOUR INSTANCE VARIABLES HERE
	//ALL PRIVATE

	GameState state; //declaring instance variables
	GameLogic gameLogic; //declaring instance variables
	BoardUserInterface BUI; //declaring instance variables
 
    /* Constructor 
	 * initializes the board
     * takes as parameters the state of the game and the gameLogic */

    public GameUserInterface(GameState state, GameLogic gameLogic) {
		//Your code here

		this.state = state;
		this.gameLogic = gameLogic;
		BUI = new BoardUserInterface(state, gameLogic);		

		quit = new JButton(); //creates a butto
		quit.setText("Quit"); //sets text of the button
		quit.addActionListener(gameLogic); //add gamelogic to it
		validate();
	
		reset = new JButton(); //creates a button
		reset.setText("Reset"); //sets text of the button
		reset.addActionListener(gameLogic);
		validate();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 1)); //setting panel for the two buttons
		add(BUI);

		JPanel sizePanel = new JPanel(); //will reduce the size of the quit and reset button
		sizePanel.add(quit);
		sizePanel.add(reset);
		
		buttonPanel.add(sizePanel);
		add(buttonPanel, BorderLayout.SOUTH); //adding button panel to bottom of frame

		setVisible(true); //makes the frame visible
		pack();
    }

	public JButton getReset(){
		return reset;
	}
	public JButton getQuit(){
		return quit;
	}
    public BoardUserInterface getBoardUserInterface(){
		//YOUR CODE HERE
		return BUI;
		
   }

}
