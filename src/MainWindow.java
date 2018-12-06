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

	/**
	 * Makes a new Moving Mean Graph with an info panel
	 * @param data : The data file that will be used to make the graph.
	 * @param x : The index in each line for the data that will be on the x axis.
	 * @param y : The index in each line for the data that will be on the y axis.
	 */
	public MainWindow(File data, int x, int y) {
		this.data = data;
		this.x = x;
		this.y = y;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//this is the entry point for the application
		
		Parameters args = getParameters();
		File data = null;
		int x = -1,y = -1;

		//check args for the values needed.
		for(int i = 0; i < args.length; i++) {
			if(!args[i].startsWith("-")) {
				continue;
			} else if(args[i].equalsIgnoreCase("-d")){
				//the -d command is for a data file that follows
				data = new File(args[i+1]);
			} else if(args[i].equalsIgnoreCase("--x")) {
				x = Integer.parseInt(args[i+1]);
			} else if(args[i].equalsIgnoreCase("--y")) {
				y = Integer.parseInt(args[i+1]);
			}
		}

		primaryStage.setTitle(data.getName());
	}

	public static void main(String[] args) {

		if(data != null && x != -1 && y != -1) {
			MainWindow window = new MainWindow(data,x,y);
			window.launch(args);
		}

	}
}
