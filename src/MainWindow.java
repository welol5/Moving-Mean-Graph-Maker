import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
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
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
