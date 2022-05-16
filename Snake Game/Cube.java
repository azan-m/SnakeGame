import javax.swing.JButton;
import javax.swing.ImageIcon;


/********************************************************************
 * The Cube is a type of JButton that represents a cube in the game 
*********************************************************************/

public class Cube extends JButton {

    // ADD YOUR INSTANCE VARIABLES HERE
    private int row;
    private int column;
    int type;
    ImageIcon icon;
    JButton button;
    

    /**
     * Constructor*/

    public Cube(int row, int column, int type) {
    //YOUR CODE HERE
      this.row = row;
      this.column = column;
      this.type = type;
      ImageIcon icon = new ImageIcon("icons/square-"+type+".png"); //Setting icon depending on what the type is
      this.setIcon(icon); //Setting icon to the button
    }

     //Sets the type of a square. 
	 //sets the icon of the square.
	
    public void setType(int type) {
    //Your code here
      if (type==0){
        ImageIcon icon = new ImageIcon("icons/square-0.png"); 
        this.setIcon(icon); //Setting icon to the button depending on what the type is
      }

      if (type==1){
        ImageIcon icon = new ImageIcon ("icons/square-1.png");
        this.setIcon(icon); //Setting icon to the button depending on what the type is
      }

      if (type==2){
        ImageIcon icon = new ImageIcon ("icons/square-2.png");
        this.setIcon(icon); //Setting icon to the button depending on what the type is
      }
      this.type = type;
    }

    public JButton getButton() {
      //Your code here
        return this.button;
      }
 
    // Getter method for the attribute row.
    public int getRow() {
		return this.row;//REPLACE THIS LINE WITH YOUR CODE 
    }

    //Getter method for the attribute column.
    public int getColumn() {
		return this.column;//REPLACE THIS LINE WITH YOUR CODE 
    }

    public int getType(){
      return this.type;
    }

    
}
