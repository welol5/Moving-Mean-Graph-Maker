package Core;
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
		InfoPanel info = new InfoPanel(primaryStage, screen.getHeight()/2);
		root.setRight(info);
		
		//add the root pane to the application
		Scene scene = new Scene(root);
		scene.getStylesheets().add("Style.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
