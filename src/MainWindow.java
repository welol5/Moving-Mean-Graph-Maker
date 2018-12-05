import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the main widow for the program. It holds the highest level panels and controls the exit conditions.
 * It is also responsible for passing information to the logical classes.
 * @author William Elliman
 *
 */
public class MainWindow extends JFrame{
	
	private JPanel contentPane;
	
	/**
	 * Makes a new Moving Mean Graph with an info panel
	 * @param data : The data file that will be used to make the graph.
	 * @param x : The index in each line for the data that will be on the x axis.
	 * @param y : The index in each line for the data that will be on the y axis.
	 */
	public MainWindow(File data, int x, int y) {
		//window setup
		contentPane = new JPanel(new BorderLayout());
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//create a new Graph to display
		Graph graph = new Graph(data,x,y);
		contentPane.add(graph);
	}
	
	
}
