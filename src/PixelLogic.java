import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class PixelLogic {
	
	private int gridRow;
	private int gridCol;
	private int[][] gridPixel;
	private int changeNumRow;
	private int changeNumCol;
	private int level;
	private String musicFile = "pop.m4a";
    
    private Media sound; 
    private MediaPlayer mediaPlayer; 
	
	
	
	/**getGridRow
	 * 
	 * @return the grids row
	 */
	public int getGridRow() {
		
		return gridRow;
	}
	
	/**getGridCol
	 * 
	 * @return the grids column
	 */
	public int getGridCol() {
		
		return gridCol;
	}
	
	
	/*
	 
	/**getChangeNumRow
	 * 
	 * this is to track how many changes from one grid spot to another in rows.
	 * In order to avoid 101010 which the Y-axis values would read 1-1-1
	 * 
	 * @return the curren
	 *
	public int getChangeNumRow() {
		
		return changeNumRow;
	}
	
	/** getChangeNumCol
	 * this is to track how many changes from one grid spot to another in columns.
	 * In order to avoid 101010 which the X-axis values would read 1-1-1
	 *
	public int getChangeNumCol() {
		
		return changeNumCol ;
	}
	*/
	
	
	
	/**getLevel
	 * 
	 * @return the grid size
	 */
	public int getLevel() {
		return level;
	}
	
	/**setLevel
	 * 
	 * @param lvl- takes in the grid size
	 */
	public void setLevel(int lvl) {
		level = lvl;
	}
	
	
	/**PixelLogic
	 * 
	 * this the DEFAULT CONSTRUCTOR
	 * @param row - row's grid size
	 * @param col - column's grid size
	 */
	public PixelLogic(int row, int col) {
		
		gridRow = row;
		gridCol = col;
		changeNumRow = 3;
		changeNumCol = 3;
		level = 1;
		sound = new Media(new File(musicFile).toURI().toString());
		
		
		this.algorithm();
		
	}
	
	
	/** algorithm
	 * this is the core of the game, creating random patterns for the grid
	 */
	public void algorithm() {
		
		gridPixel = new int[gridRow][gridCol];
		
		gridPixel[0][0] = 0;
		
		
		boolean set = false;
		
		for(int i=0; i<gridRow; i++) {
			
			for(int j=0; j<gridCol; j++) {
				
				int num = (int)(Math.random() * 2);
				
				
				if(j > 0){
					
					if(gridPixel[i][j-1] < 1) {
						//System.out.println("change 00 to 01 at: " + i + j );
						num = 1;
						
					}
					
					
					
					if(set == true) {
						
						
					}
					
				} 
				
				gridPixel[i][j] = num;
			}
			
			set = false;
		}
		
		//comment out
		for(int j=0; j<gridRow; j++) {
			
			for(int i=0; i<gridCol; i++) {
				
				if(i > 0){
					
					if(gridPixel[i-1][j] < 1) {
						gridPixel[i][j] = 1;
					}
				}
				
				
				
				
			}	
		}
		
		
		
		
		
		// removes 101010101
		for(int r=0; r<gridRow; r++) {
			
			changeNumRow = 3;
			
			for(int c=0; c<gridCol-1; c++) {
				
				
				if(changeNumRow > 0) {
					
					if(gridPixel[r][c+1] != gridPixel[r][c]) {
						
						changeNumRow--;
						
					}
					
				} else {
					
					//gridPixel[i][j] = gridPixel[i][j-2];
					gridPixel[r][c+1] = gridPixel[r][c];
				}
				
				
				
			}
			
			
		}
		
		
		for(int c=0; c<gridCol; c++) {
			
			
			for(int r=0; r<gridRow-1; r++) {
				
				
				if(changeNumCol > 0) {
					
					if(gridPixel[r+1][c] != gridPixel[r][c]) {
						
						changeNumCol--;
						
					}
					
				} else {
					
					gridPixel[r+1][c] = gridPixel[r][c];
				}
				
			}
			changeNumCol = 3;
			
		}
		
		
		
		
		
		
		
	}
	
	
	/** printGrid
	 * prints the grid's pattern to the console
	 */
	public void printGrid() {
		
		for(int i=0; i<gridRow; i++) {
			
			for(int j=0; j<gridCol; j++) {
				
				System.out.print(gridPixel[i][j] + " ");
			}
			System.out.println(" ");
		}
		
	}
	
	
	
	/** displayGrid
	 * 
	 * displays the grid's pattern to a Graphical User Interface
	 * 
	 * @param gameBoard - takes in a GridPane
	 * @param play - takes in the player class
	 */
	public void displayGrid(GridPane gameBoard, Player play) {
		
		
		
		try {
	    	
	    	for(int r = 0; r < getGridRow(); r++) {
		    	
		    	for(int c = 0; c < getGridCol(); c++) {
		    		
		    		final int row = r;
		    		final int col = c;
		    		
		    	
			    	Image moonImage = new Image("file:White.jpg");
				    Image shipImage = new Image("file:Blue.jpg");
					
				   
				    
				    ImageView moonIView = new ImageView(moonImage);
				    
				    //String musicFile = "pop.mp3";
				    
				    //Media sound = new Media(new File(musicFile).toString());
				    //MediaPlayer mediaPlayer = new MediaPlayer(sound);
				    
				    
				    //CHANGING IMAGE WHEN CLICKED
				    moonIView.addEventHandler(MouseEvent.MOUSE_CLICKED, Event->{
				    	mediaPlayer = new MediaPlayer(sound);
				    	
				    	if (moonIView.getImage().equals(moonImage)) {
				    		moonIView.setImage(shipImage);
				    		play.setPlayerClicked(row,col,1);
				    		mediaPlayer.play();
				    	}else {
				    		moonIView.setImage(moonImage);
				    		play.setPlayerClicked(row,col,0);
				    		mediaPlayer.play();
				    	}
				    	
				    	
				    	});
				    
				    
				    
				    
				    moonIView.setFitWidth(50);
				    moonIView.setPreserveRatio(true);
				    
				    gameBoard.add(moonIView, c, r);
				    
				    
		    	}
		    }
	    	
	    } catch (Exception e) {System.out.println("errerer"); }
		
		
	}
	
	
	/**getPattern
	 * 
	 * @param row - takes in the row
	 * @param col - takes in the column
	 * @return the pattern of a grid spot 
	 */
	public int getPattern(int row, int col) {
		
		return gridPixel[row][col];
		
	}
	
	
	/** updateLevel
	 * calls gridChange and increases the level
	 * With a new algorithim/Pattern
	 */
	public void updateLevel() {
		gridChange();
		setLevel(this.getLevel()+1);
		this.algorithm();
		
	}
	
	/**
	 * Updates the Grid rows and columns
	 * Increments by 1 at each iteration
	 */
	public void gridChange() {
		gridCol  = this.getGridCol() +1;
		gridRow = this.getGridRow() +1;

		
	}
	
	
	

	
}



