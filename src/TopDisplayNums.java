import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TopDisplayNums extends NumTopAndSide{
	
	
	
	/** TopDisplayNums
	 * 
	 * this is a DEFAULT CONSTRUCTOR
	 * @param value - takes in the PixelLogic class
	 */
	public TopDisplayNums(PixelLogic value) {
		super(value,2,value.getGridCol());
		
	}
	
	
	/** updateLevel
	 * 
	 * when its called its refreshes runs the algorithm based on the current PixelLoigic  
	 */
	@Override
	public void updateLevel() {
		this.algorithm();
		
	}
	
	/** algorithm
	 * 
	 * this calculates and displays the number on the X-axis to the console
	 */
	@Override
	public void algorithm() {
		
		//System.out.println(this.getGridGame().getGridCol() + " ");
		
		int change = 2;
		
		for(int j=0; j<this.getGridGame().getGridCol(); j++) {
			
			for(int i=0; i<this.getGridGame().getGridRow(); i++) {
				
				if(i > 0 && i < this.getGridGame().getGridRow()) {
					
					if(this.getGridGame().getPattern(i-1,j) != this.getGridGame().getPattern(i,j)) {
					
						change--;
						
					}
					
				}
				
				if(change > 0) {
					
					this.getArray()[0][j] += this.getGridGame().getPattern(i,j);
					
					
				}else {
					this.getArray()[1][j] += this.getGridGame().getPattern(i,j);
					
				}
			}
			//System.out.println(change + " ");
			change = 2;
			
		
		}
		
		
		
		for(int j=0; j<this.getGridGame().getGridCol(); j++) {
			
			if(this.getArray()[1][j] == 0) {
				
				this.getArray()[1][j] = this.getArray()[0][j];
				this.getArray()[0][j] = 0;
				
			}
		
		}
		
		
		for(int i=0; i<2; i++) {
			
			for(int j=0; j<this.getGridGame().getGridCol(); j++) {
			
				System.out.print(this.getArray()[i][j] + " ");
			
			}
			System.out.println(" ");
			
		}
		
		
	}
	
	
	/** getValue
	 * 
	 * @param i - takes in int
	 * @param j - takes in int
	 * @return the value in the spot
	 */
	@Override
	public int getValue(int i, int j) {
		
		return this.getArray()[i][j];
	}
	
	
	/** displayGrid
	 * 
	 * displays the grid's pattern to a Graphical User Interface
	 * 
	 * @param gridpaneTopNums - takes in a GridPane
	 */
	@Override
	public void displayGrid(GridPane gridpaneTopNums) {
		
		
		for(int i = 0; i < 2; i++) {
			//t += "	";
			
			for(int j = 0; j < this.getGridGame().getGridCol(); j++) {
				
				
				if(getValue(i, j) > 0) {
					
					Label topNumIndex = new Label(Integer.toString(getValue(i, j)));
					topNumIndex.getStyleClass().add(".label");
					gridpaneTopNums.add(topNumIndex, j, i);
					
				} else {
					
					Label topNumIndex = new Label("  ");
					gridpaneTopNums.add(topNumIndex, j, i);
				}
				
			}
			
			
			
		}
		
	}
	

}
