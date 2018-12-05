import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

/**
 * This class deals with displaying the graph to the user.
 * @author William Elliman
 *
 */
public class Graph extends JPanel{
	
	GraphManager manager;
	
	public Graph(File data, int x, int y) {
		try {
			manager = new GraphManager(this,data,x,y);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
