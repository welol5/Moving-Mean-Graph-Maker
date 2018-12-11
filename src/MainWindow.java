import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is the main widow for the program. It holds the highest level panels and controls the exit conditions.
 * It is also responsible for passing information to the logical classes.
 * @author William Elliman
 *
 */
public class MainWindow extends Application{

	Dimension screen;
	int xMax = 0;
	int yMax = 0;
	
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
		//System.out.println(canvas.getHeight());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		//add canvas to the root panel
		root.setCenter(canvas);
		
		//add the info pane to the root
		//System.out.println(screen.getHeight());
		root.setRight(makeInfoPanel(screen.getHeight()/2.0));
		
		//add the root pane to the application
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	private VBox makeInfoPanel(double height) {
		VBox holder = new VBox();
		
		//upper half (data details)
		VBox details = new VBox();
		details.setPrefHeight(height/2);
		details.setStyle("-fx-background-color: #666666;-fx-border-color: #000000;-fx-border-thickness: 5;");
		
		//finish uppper half
		holder.getChildren().add(details);
		
		//lower half (select file button)
		VBox data = new VBox();
		data.setPrefHeight(height/2);
		data.setAlignment(Pos.BOTTOM_CENTER);
		
		Button dataButton = new Button("Select File");
		
		data.getChildren().add(dataButton);
		
		//finish lower half
		holder.getChildren().add(data);
		
		return holder;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
