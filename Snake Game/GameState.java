import java.util.Random;


public class GameState {


		//static final variables public
		public static final int FREE_CUBE = 0;
		public static final int SELECTED = 1;
		public static final int RED_CUBE = 2;
		public static final int MAX_SELECTED = 5;
		
		//non-final variables private
		private int boardSize;
		private Point redCube;
    // YOUR INSTANCE VARIABLES HERE
    int [][] cubes;


    /**
     * Constructor 
	 * initializes the state to the size of board
     *  The parameter size is the size of the board
     */
    public GameState(int size) {
  // YOUR CODE HERE
      boardSize = size;

      redCube = new Point(0,0);

      cubes = new int[boardSize][boardSize];

      for (int i = 0; i<boardSize; i++){ 
        for (int j = 0; j<boardSize; j++){
          cubes[i][j] = FREE_CUBE; //Declare every cube as a free cube for now
        }
      }
      
      if (boardSize%2==0){ //find the middle of the board and spawn the red cube there if size is even
        int x = boardSize/2;
        Random temp = new Random();
        int randomY = temp.nextInt(x)+x/2;
        redCube.reset(x, randomY);
        cubes[x][randomY] = RED_CUBE;
      }

      else{
        int x = Math.round((boardSize/2));
        Random temp = new Random();
        int randomY = temp.nextInt(boardSize);
        redCube.reset(Math.round((boardSize/2)),randomY); //find the middle of the board and spawn the red cube there if size is odd
        cubes[Math.round((boardSize/2))][randomY] = RED_CUBE;
      }

      Random temp = new Random();

      for (int i = 0; i<MAX_SELECTED; i++){  //setting randomly selected snakes
        int tempOne = temp.nextInt(boardSize);
        int tempTwo = temp.nextInt(boardSize);
        if (cubes[tempOne][tempTwo]!=RED_CUBE){
          cubes[tempOne][tempTwo] = SELECTED;
        }
      }

    }

    public  int getSize(){
	//YOUR CODE HERE
		return boardSize;
   }

    /**
     * returns the current status (FREE_CUBE, SELECTED or RED_CUBE) of a given cube in the game
     * 
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     * return the status of the cube at location (i,j)
     */   
    public int getCurrentStatus(int i, int j){
      if (i<0 || i>boardSize-1){ 
        return -1;
      }
      if (j<0 || j>boardSize-1){
        return -1;
      }
      if (cubes[i][j]==FREE_CUBE){
        return FREE_CUBE; //return the type of the cube
      }
      else if (cubes[i][j]==SELECTED){
        return SELECTED;//return the type of the cube
      }
      return RED_CUBE;//return the type of the cube
      //REPLACE THIS LINE WITH YOUR CODE 
    }


    /**
     * Sets the status of the cube at coordinate (i,j) to SELECTED
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */   
    public void select(int i, int j){
      cubes[i][j] = SELECTED;

	//YOUR CODE HERE
    }

    /**
     * Puts the red cube at coordinate (i,j). Clears the previous location 
     * of the red cube.
     *
      * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */   
    public void setCube(int i, int j){
      cubes[redCube.getX()][redCube.getY()] = FREE_CUBE;
      redCube.reset(i, j);
      cubes[i][j] = RED_CUBE;
   }

   public Point mouseChange(){
      Random temp = new Random(); 
      int x = getCurrentCube().getX();
      int y = getCurrentCube().getY();
      int tempOne = temp.nextInt(boardSize);
      int tempTwo = temp.nextInt(boardSize); //make two random numbers
      //setCube(tempOne, tempTwo); //set redcube to those random numbers
      while (this.getCurrentStatus(tempOne, tempTwo)==SELECTED || (tempOne==x && tempTwo==y)){ //if those numbers are coordinates of snakes, do it again
        tempOne = temp.nextInt(boardSize);
        tempTwo = temp.nextInt(boardSize);
      }
      setCube(tempOne, tempTwo);
      return redCube;
      
    }
   

    /* Getter method for the current red cube
     * return the location of the curent red cube */   
    public Point getCurrentCube(){
		return redCube;//REPLACE THIS LINE WITH YOUR CODE 
    }

}
