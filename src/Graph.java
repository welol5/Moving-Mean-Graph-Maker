import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

import javafx.scene.text.Font;

/**
 * This class deals with displaying the graph to the user.
 * @author William Elliman
 *
 */
public class Graph extends JPanel{
	
	GraphManager manager;
	int yMax = 0;
	int xMax = 0;
	
	public Graph(File data, int x, int y) {
		
		repaint();
		try {
			manager = new GraphManager(this,data,x,y, (int)this.getSize().getHeight());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setXMax(int newX) {
		xMax = newX;
	}
	
	public void setYMax(int newY) {
		yMax = newY;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		//Font textFont = new Font(10);
		g.drawString("" + yMax, 50, 50);
	}
}
