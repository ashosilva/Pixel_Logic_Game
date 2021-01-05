import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;


public class Player {

	private int row;
	private int col;
	private int[][] playerClick;
	private int level;

	/** Player
	 *  
	 * this the DEFAULT CONSTRUCTOR
	 * 
	 * @param row - row's size
	 * @param col - column's size
	 */
	public Player(int row, int col) {
		this.row = row;
		this.col = col;
		level = 1;
		playerClick = new int[this.row][this.col];
		
	}
	
	
	/** playerClicked
	 * 
	 * sets all spot in the array to 0
	 * 
	 */
	public void playerClicked() {
		
		
		for(int r = 0; r < row; r++) {
	    	
	    	for(int c = 0; c < col; c++) {
	    		
	    		
	    		playerClick[r][c] = 0;
	    		
	    		
	    	}
	    	
		}
	
	}
	

	/** setPlayerClicked
	 * 
	 * this sets the spot that is been clicked on
	 * 
	 * @param r - row
	 * @param c - column
	 * @param value - takes in 1 or 0
	 */
	public void setPlayerClicked(int r, int c, int value) {
		
		playerClick[r][c] = value;
			
	}
	
	
	/** getPlayerClicked
	 * 
	 * @param r - row
	 * @param c - column
	 * @return the spot that is been clicked
	 */
	public int getPlayerClicked(int r, int c) {
		
		return playerClick[r][c];
			
	}
	
	/** setPlayerLevel
	 * 
	 * sets the current player level
	 * 
	 * @param level - takes in the grid size
	 */
	public void setPlayerLevel(int level) {
		
		this.level = level - 2;
	}
	
	/** getPlayerLevel
	 * 
	 * @return the players's current level
	 */
	public int getPlayerLevel() {
		
		return level;
	}
	
	
	/** saveGame
	 * 
	 * this saves the current of the game into a file
	 * 
	 * @param file - takes in a text file
	 */
	public void saveGame(File file){
		
		try{
			PrintWriter outputStream = new PrintWriter(file);
		
			outputStream.println("SAVED: Level " + level);
			outputStream.close();

		}
		catch(FileNotFoundException e){
			//e.printStackTrace();
			System.out.println("Game has not been saved to file.");
		}
	}
	
	
	/** loadGame
	 * 
	 * loads the saved level of the game from a file
	 * 
	 * @param file - takes in a file
	 */
	public void loadGame(File file){
		
		try{
			Scanner input = new Scanner (file);
			
			while (input.hasNext()) {
				System.out.println(input.next());
				
				if (input.hasNextInt()) {
		            level = input.nextInt();
		         }
	           
			}
			
			input.close();
			System.out.println(level);
		}
		catch (Exception e){
		System.out.println("ERROR!!!!!!!!!");
		//e.printStackTrace();
		
		}
		
	}
	
}
