import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		primaryStage.setTitle("Data");
		primaryStage.show();
		
		//make a scene
		screen = Toolkit.getDefaultToolkit().getScreenSize();
		BorderPane root = new BorderPane();
		Canvas canvas = new Canvas(screen.width/2,screen.height/2);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//get a file
		JFileChooser chooser = new JFileChooser();
		File data = null;
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			data = chooser.getSelectedFile();
		} else {
			System.exit(0);
		}
		
		//pass the file to the graph manager
		GraphManager manager = new GraphManager(data, 0, 1, screen.height/2);
		
		fillGraph(gc);
		root.setCenter(canvas);
		
		GridPane text = new GridPane();
		Text max = new Text("" + yMax);
		text.add(max, 0, 0);
		root.setLeft(text);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public void fillGraph(GraphicsContext gc) {
		int textStartX = screen.width/10;
		int textHeight = screen.height/20;
		
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
