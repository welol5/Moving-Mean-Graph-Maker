import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is the main widow for the program. It holds the highest level panels and controls the exit conditions.
 * It is also responsible for passing information to the logical classes.
 * @author William Elliman
 *
 */
public class MainWindow extends Application{

	private File data;
	private int x, y;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//this is the entry point for the application
		primaryStage.setTitle("Data");
		primaryStage.show();
		
		//make a scene
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Group root = new Group();
		Canvas canvas = new Canvas(screen.width/2,screen.height/2);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		fillGraph(gc);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public void fillGraph(GraphicsContext gc) {
		gc.setFill(Color.BLUEVIOLET);
		gc.fillText("Hello", 50, 50);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
