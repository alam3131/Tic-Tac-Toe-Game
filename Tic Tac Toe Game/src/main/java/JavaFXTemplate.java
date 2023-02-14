import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;


public class JavaFXTemplate extends Application {
	
	private int playerTurn = 0;
	private EventHandler<ActionEvent> ticTacToeHandler;
	private EventHandler<ActionEvent> startOverHandler;
	private GridPane grid;
	private ListView<String> listView;

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Tic Tac Toe");
		
		// Create a new menu bar, menu and two menu items for start over and exit
		MenuBar m = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem startOver = new MenuItem("Start Over");
		MenuItem exit = new MenuItem("Exit");
		
		// Sets action of menu item start over
		startOverHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				grid.getChildren().clear();
				addGrid(grid);
				playerTurn = 0;
				listView.getItems().clear();
				listView.setStyle("-fx-font-size: 20;");
				listView.getItems().add("New Game Started");
				listView.getItems().add("It's player 1's turn!");
			}
		};
		
		// Sets actions for menu items startOver and exit
		startOver.setOnAction(startOverHandler);
		exit.setOnAction(e -> Platform.exit());
		
		// Add the menu items to menu and add menu to menu bar
		menu.getItems().add(startOver);
		menu.getItems().add(exit);
		m.getMenus().addAll(menu);
		
		// Sets action of tic tac toe buttons
		ticTacToeHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button button = (Button)e.getSource();
				if(playerTurn % 2 == 0) {
					listView.getItems().clear();
					listView.getItems().add("It's player 2's turn!");
					button.setDisable(true);
					button.setStyle("-fx-font-size: 40; -fx-text-fill: red;");
					button.setText("X");
					playerTurn++;
				} else {
					listView.getItems().clear();
					listView.getItems().add("It's player 1's turn!");
					button.setDisable(true);
					button.setStyle("-fx-font-size: 40; -fx-text-fill: green;");
					button.setText("O");
					playerTurn++;
				}
			}
		};
		
		// Create tic tac toe grid
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		addGrid(grid);
		
		// Create a list view to display game information
		listView = new ListView<String>();
		listView.setStyle("-fx-font-size: 20;");
		listView.getItems().add("New Game Started");
		listView.getItems().add("It's player 1's turn!");
		listView.setPrefHeight(200);
		
		// Creates a border pane and adds children
	    BorderPane root = new BorderPane();
	    root.setTop(m);
	    root.setCenter(grid);
	    root.setBottom(listView);
	    root.setStyle("-fx-background-color: paleturquoise;");
	    
	    Scene scene = new Scene(root, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Function to add buttons to grid
	public void addGrid(GridPane grid) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Button temp = new Button("    ");
				temp.setPrefSize(100, 100);
				temp.setOnAction(ticTacToeHandler);
				grid.add(temp, i, j);
			}
		}
	}

}





