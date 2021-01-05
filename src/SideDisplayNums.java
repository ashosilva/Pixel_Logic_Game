import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SideDisplayNums extends NumTopAndSide {
	
	
	/** SideDisplayNums
	 * 
	 * this is a DEFAULT CONSTRUCTOR
	 * @param value - takes in the PixelLogic class
	 */
	public SideDisplayNums(PixelLogic value) {
		super(value,value.getGridRow(),2);
		
		
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
	 * this calculates and displays the number on the Y-axis to the console
	 */
	@Override
	public void algorithm() {
		// TODO Auto-generated method stub
		
		
		int change = 2;
		
		for(int i=0; i<this.getGridGame().getGridRow(); i++) {
			
			for(int j=0; j<this.getGridGame().getGridCol(); j++) {
				
				if(j > 0 && j < this.getGridGame().getGridCol()) {
					
					if(this.getGridGame().getPattern(i,j-1) != this.getGridGame().getPattern(i,j)) {
					
						change--;
						
					}
					
				}
				
				if(change > 0) {
					
					this.getArray()[i][0] += this.getGridGame().getPattern(i,j);
					
					
				}else {
					this.getArray()[i][1] += this.getGridGame().getPattern(i,j);
					
				}
			}
			//System.out.println(change + " ");
			change = 2;
			
		
		}
		
		
		for(int i=0; i<this.getGridGame().getGridRow(); i++) {
			
			if(this.getArray()[i][1] == 0) {
				
				this.getArray()[i][1] = this.getArray()[i][0];
				this.getArray()[i][0] = 0;
				
			}
		
		}
		
		
		for(int i=0; i<this.getGridGame().getGridRow(); i++) {
			
			for(int j=0; j<2; j++) {
			
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
	 * @param gridpaneSideNums - takes in a GridPane
	 */
	@Override
	public void displayGrid(GridPane gridpaneSideNums) {
		
		
	
		for(int j = 0; j < this.getGridGame().getGridRow(); j++) {
			//s += "	";
			
			for(int i = 0; i < 2; i++) {
				
				
				
				if(getValue(j,i) > 0) {
					Label sideNumIndex = new Label(Integer.toString(getValue(j, i)));
					sideNumIndex.getStyleClass().add(".label");
					gridpaneSideNums.add(sideNumIndex, i, j);
					
					
					
					
				} else {
					
					Label sideNumIndex = new Label("  ");
					gridpaneSideNums.add(sideNumIndex, i, j);
				}
			
				//s += " " + value;
				
			}
		}
	}
	
	
}
