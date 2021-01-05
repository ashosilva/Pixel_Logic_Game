//Group #4
//Allan Williams
//Anuoluwa Oyetibo
//Abayomi Shosilva


import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import java.io.File;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class gameRunner extends Application{
	
	
	static PixelLogic games;
	static TopDisplayNums tops;
	static SideDisplayNums sides;
	static Player play;
	
	
	private int gridlvl;
	
	//Stage and scene
	private Stage window;
	private Scene scene, scene1;
	
	//Menus 
	private MenuBar menuBar;
	private Menu fileMenu;
	private MenuItem exitItem;
	private MenuItem saveItem;
	private MenuItem loadItem;
	
 	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		launch(args);
	}

	
	
	//Start Button
	private Button startButton;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		try { 
			//Creating a Start Button
			startButton = new Button("Start");
			startButton.getStyleClass().add(".button");
			startButton.setOnAction(new StartButtonHandler()); //StartButton Event handler
			VBox vboxStartButton = new VBox(startButton);
			vboxStartButton.setAlignment(Pos.CENTER);
			
			
			//setting the gridSize
			gridlvl = 3;
			
			//Instantiating player
			play = new Player(gridlvl, gridlvl);
			
			//stage
			window = primaryStage;
			
			
			// Creating the File menu.
		    StartFileMenu(window);
			// Creating the MENU BAR.
		    menuBar = new MenuBar();
			menuBar.getMenus().add(fileMenu);
			VBox vboxMenu = new VBox(menuBar);
			//vboxMenu.setAlignment(Pos.TOP_LEFT);
		
			
			//TITLE
			Label title = new Label("PIXEL LOGIC");
			title.getStylesheets().add("title.css");
			VBox vboxTitle= new VBox(title);
			vboxTitle.setAlignment(Pos.CENTER);
			
			VBox vboxEmpty= new VBox();// Make it look neat
			
			VBox vboxLoad = new VBox(85,vboxMenu, vboxTitle, vboxStartButton, vboxEmpty);
			vboxLoad.getStylesheets().add("back.css");
			//vboxLoad.setPadding(new Insets(10));
			//vboxLoad.setAlignment(Pos.TOP_CENTER);
			
			
			//Scene 
			scene1 = new Scene(vboxLoad,400,400); 
			scene1.getStylesheets().add("button.css");
 
			window.setScene(scene1); 

			window.show(); 
		} 

		catch (Exception e) { 

			System.out.println(e.getMessage()); 
		} 

	}
	
	
	
	/** StartFileMenu
	 * This method builds the File menu which has exit, save and load as an item.
	 * @param primaryStage - takes in a stage
	 */
	private void StartFileMenu(Stage primaryStage){
		
		// Creating the File Menu object.
	    fileMenu = new Menu("Menu");
	    
	      
	    // Creating the Exit MenuItem object.
	    exitItem = new MenuItem("Exit");
	    loadItem = new MenuItem("Load");
	    saveItem = new MenuItem("Save");
	      
	    // Registering an event handler for the Exit item.
	    exitItem.setOnAction(event ->
	    {
	       primaryStage.close();
	    });
	    
	    
	    // Registering an event handler for the Load item.
	    loadItem.setOnAction(event ->{
	    	FileChooser fileChooser = new FileChooser();
	    	File file = fileChooser.showOpenDialog(primaryStage);
	    	play.loadGame(file);
	    	gridlvl = play.getPlayerLevel() + 2;
	    	System.out.println(gridlvl + "wtf");
	    	//setComplete(true);
	    	
	    	StartButtonHandler loadGame = new StartButtonHandler();
	    	loadGame.handle(event);
	    	
	    	
	    });
	    
	    // Registering an event handler for the Save item.
	    saveItem.setOnAction(event ->{
	    	
	    	FileChooser fileChooser = new FileChooser();
			 
	        //Set extension filter for text files
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	        fileChooser.getExtensionFilters().add(extFilter);

	        //Show save file dialog
	        File file = fileChooser.showSaveDialog(primaryStage);
	    	play.setPlayerLevel(gridlvl);
	    	play.saveGame(file);
	    	
	    });
	    
	    // Add the items to the File menu.
	    fileMenu.getItems().add(exitItem);
	    fileMenu.getItems().add(loadItem);
	    fileMenu.getItems().add(saveItem);
	    
	    
	}
	
	
	
	
	
	
	private BorderPane borderPane;
	
	private GridPane gridpaneBoard = new GridPane();
	private GridPane gridpaneTopNums = new GridPane();
	private GridPane gridpaneSideNums = new GridPane();
	private GridPane gridpaneGame = new GridPane();
	
	
	/** StartButtonHandler
	 * when the start button is clicked the game starts
	 */
	class StartButtonHandler implements EventHandler<ActionEvent> {
		
	    @Override
	    public void handle(ActionEvent event) {
	    	
	    	//clears it up
	    	gridpaneGame.getChildren().clear();
	    	
	    	
	    	//GRIDCREATION
	    	GridCreation(gridlvl);
	    	
	    	
			MenuItem helpItem = new MenuItem("Find the pattern based on the numbers given on the X and Y axis.\n"
					+ "If two values are given for example (1 1) or (3 2), it means there is one or two empty spaces between the grid spots.\n"
					+ "There is no limit to the number of tries you have. Upon completion, a new map or set of tiles is generated, with a new pattern.\n"
					+ "You have the option to quit mid game or save your current progress if you wish to continue later.\n"
					+ "Any progress would be saved by level progression (i.e. – level 1-10). At each level the grid size changes.\n"
					+ "A new pattern would be generated when you load from previous files but will retain your current level.");
	    	
	    	Menu helpMenu = new Menu("Help");
	    	
	    	helpMenu.getItems().add(helpItem);
	    	
	    	menuBar.getMenus().clear();
	    	menuBar.getMenus().add(fileMenu);
	    	menuBar.getMenus().add(helpMenu);
	    	
	    	//Update LABEL LEVEL
	    	String s = "LEVEL ";
	    	s+= play.getPlayerLevel();
			Label lvl = new Label(s);
		    borderPane = new BorderPane();
		    borderPane.setTop(menuBar);
		    borderPane.setCenter(lvl);
		    borderPane.getStylesheets().add("lvl.css");
		    
		  
			//THE X AXIS-NUMS
		    gridpaneTopNums.setVgap(5);
		    gridpaneTopNums.setHgap(40);
		    gridpaneTopNums.setAlignment(Pos.CENTER);
			
		
		    //THE Y AXIS-NUMS
		    gridpaneSideNums.setVgap(23);
		    gridpaneSideNums.setHgap(5);
		    gridpaneSideNums.setPadding(new Insets(5));
		    gridpaneSideNums.setAlignment(Pos.CENTER_LEFT);
			
			
		    //GridPaneBoard
		    gridpaneBoard.setOnMouseClicked(new GridHandler());
		    gridpaneBoard.setVgap(5);
		    gridpaneBoard.setHgap(5);
		    gridpaneBoard.setPadding(new Insets(5));
		    gridpaneBoard.setAlignment(Pos.CENTER);
		    
		    
		    //Just to format
		    VBox Empty = new VBox();
		    
		    // leftside on the gridpane(0,0)
		    VBox vboxLeftGrid = new VBox(80,Empty,gridpaneSideNums);
		    
		    //rightside on the gridpane(1,0)
			VBox vboxRightGrid = new VBox(gridpaneTopNums, gridpaneBoard);
			
			//GRIDPANEGAME
			gridpaneGame.add(vboxLeftGrid, 0, 0);
			gridpaneGame.add(vboxRightGrid, 1, 0);
			gridpaneGame.getStylesheets().add("style.css");
			
			//VBox
			VBox vboxLoad = new VBox(borderPane, gridpaneGame); 
			vboxLoad.setAlignment(Pos.CENTER);
			vboxLoad.getStyleClass().add(".root");
			
		    
			//Scene
			scene = new Scene(vboxLoad);
			scene.getStylesheets().add("back.css");
			
			//Stage
			window.setScene(scene);
	  	}  
	}
	
	private String musicFile = "levelup.m4a";
    
	private Media sound = new Media(new File(musicFile).toURI().toString());
	private MediaPlayer mediaPlayer;
	
	
	/** GridHandler
	 * 
	 * when level solved moves to the next level
	 */
	class GridHandler implements EventHandler<Event> {
		
	    @Override
	    public void handle(Event event) {
	    	
	    	boolean complete = true;
	    	
	    	int x = 0;
	    	while(x < games.getGridRow()) {
	    		
	    		int y = 0;
	    		while(y < games.getGridCol() && complete == true) {
	    			
	    			if(play.getPlayerClicked(x,y) != games.getPattern(x,y)) {
	    				complete = false;
	    				
	    			}
	    			y++;
	    		}
	    		x++;
	    	}
	    	setComplete(complete);
	    	
	    	//UPON COMPLETION
	    	if (getComplete() == true) {
	    		mediaPlayer = new MediaPlayer(sound);
			    
			    
			    mediaPlayer.play();
	    		
	    		System.out.println("COMPLETED LEVEL " + play.getPlayerLevel());
	    	
	    		games.updateLevel();
	    		gridlvl = games.getGridRow();
	    		
	    		
	    		
	    		GridCreation(gridlvl);
	    		
	    		String s = "LEVEL ";
		    	s+= play.getPlayerLevel();
		    	Label lvl = new Label(s);
		    	borderPane.setCenter(lvl);
		    	borderPane.getStylesheets().add("lvl.css");
	    		
	    		window.sizeToScene();
	    	}
	    }
	}
	
	
	
	/** GridCreation
	 * 
	 * this sets up the grid and displays it
	 * 
	 * @param size - takes in the grid size
	 */
	public void GridCreation(int size) {
		
		setComplete(false);
		gridpaneBoard.getChildren().clear();
		gridpaneTopNums.getChildren().clear();
		gridpaneSideNums.getChildren().clear();
		
		
		games = new PixelLogic(size,size);
		games.printGrid();//prints in console
		
		tops = new TopDisplayNums(games);
		System.out.println(" ");//prints in console
		
		sides = new SideDisplayNums(games);
		System.out.println(" ");//prints in console
		
		tops.algorithm();
		System.out.println(" ");//prints in console
		sides.algorithm();
		System.out.println(" ");//prints in console
		
		play = new Player(size, size);
		play.setPlayerLevel(size);
		
		//display on GUI
		games.displayGrid(gridpaneBoard, play);
		tops.displayGrid(gridpaneTopNums);
		sides.displayGrid(gridpaneSideNums);
		
		
		
	}
	
	
	//is game solved?
	boolean comp;
	
	/** setComplete
	 * 
	 * setting true or false if its solved
	 * 
	 * @param value - takes in a bool
	 */
	public void setComplete(boolean value) {
    	
    	 comp = value;
    }
	
	/** getComplete
	 * 
	 * @return true or false
	 */
	public boolean getComplete() {
    	
   	 return comp;
   }
	
	
	
}
