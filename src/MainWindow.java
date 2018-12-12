import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is the main widow for the program. It holds the highest level panels and controls the exit conditions.
 * It is also responsible for passing information to the logical classes.
 * @author William Elliman
 *
 */
public class MainWindow extends Application{

	private Dimension screen; //size of the screen
	
	private File dataFile; //the data file that will have a function run on it
	private GridPane details = new GridPane(); //this will hold data that is discovered about the file
	
	//GraphManager manager = new GraphManager();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//this is the entry point for the application
		primaryStage.setTitle("Moving Mean Graph Maker");
		primaryStage.show();
		
		//make a scene
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		BorderPane root = new BorderPane();
		
		//make a colorful canvas for testing
		Canvas canvas = new Canvas(screen.getWidth()/2,screen.getHeight()/2);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//set the background color
		canvas.getStyleClass().add("graphPanel");
		
		//add canvas to the root panel
		root.setCenter(canvas);
		
		//add the info pane to the root
		//System.out.println(screen.getHeight());
		root.setRight(makeInfoPanel(screen.getHeight()/2.0, primaryStage));
		
		//add the root pane to the application
		Scene scene = new Scene(root);
		scene.getStylesheets().add("Style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private VBox makeInfoPanel(double height, Stage s) {
		VBox holder = new VBox();
		
		//upper half (data details)
		details.setPrefHeight(height/2);
		details.getStyleClass().add("basicPanel");
		
		//add items to display details about the upper half of the info panel
		//file info
		Text fileTitleLabel = new Text("File: ");
		fileTitleLabel.getStyleClass().add("subtitleFont");
		details.add(fileTitleLabel, 0, 0);
		Text fileTitle = new Text("No File Selected");
		fileTitle.getStyleClass().add("basicFont");
		details.add(fileTitle, 1, 0);
		
		//finish upper half
		holder.getChildren().add(details);
		
		//lower half (select file button)
		VBox data = new VBox();
		data.getStyleClass().add("basicPanel");
		data.setPrefHeight(height/2);
		data.setAlignment(Pos.BOTTOM_CENTER);
		
		//make the file select button
		Button dataButton = new Button("Select File");
		dataButton.getStyleClass().add("basicFont");
		dataButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
				dataFile = fileChooser.showOpenDialog(s);
				if(dataFile != null) {
					fileTitle.setText(dataFile.getName());
				}
			}
		});
		
		//add the button to the bottom half of the panel
		data.getChildren().add(dataButton);
		
		//finish lower half
		holder.getChildren().add(data);
		
		return holder;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
