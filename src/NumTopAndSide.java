import javafx.scene.layout.GridPane;

public abstract class NumTopAndSide {

	private PixelLogic gridGame;
	
	private int[][] arr;
	
	/**NumTopAndSide
	 * 
	 * this is a DEFAULT CONSTRUCTOR
	 * @param value - takes in the PixelLogic class
	 * @param x - the row
	 * @param y - the column
	 */
	public NumTopAndSide(PixelLogic value, int x, int y) {
		gridGame = value;
		
		arr = new int[x][y];
		
	}
	
	/**getGridGame
	 * 
	 * @return a PixelLogic
	 */
	public PixelLogic getGridGame() {
		
		return gridGame;
	}
	
	/**
	 * 
	 * @return the axis's Values
	 */
	public int[][] getArray() {
		
		return arr;
	}
	
	public abstract void updateLevel();
	
	public abstract void algorithm();
	
	public abstract int getValue(int i, int j);
	
	public abstract void displayGrid(GridPane value);
	
	

	
}
