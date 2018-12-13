package Core;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;

/**
 * The GraphStyle abstract class deals with parsing data into arrays to make data processing easier. It also sets up a 2D array to be used for color
 * values. The array will be the plot drawn in the program. The data processing should occur in the run method so that the program can make use
 * of possible distributed processing. Any subclasses should super() variables. 
 * @author William
 *
 */
public abstract class GraphStyle implements Runnable{

	private File data;
	private Color[][] graph;

	private double[] xValues;
	private double[] yValues;

	public GraphStyle(File file, String regex, int xCol, int yCol, Dimension s) throws IllegalArgumentException{
		if(file.isFile()) {
			data = file;
		} else {
			throw new IllegalArgumentException("File cannot be a directory");
		}

		//get vars for file parsing
		ArrayList<String> xStrings = new ArrayList<String>();
		ArrayList<String> yStrings = new ArrayList<String>();
		//load the file
		Scanner fileInput;
		try {
			fileInput = new Scanner(data);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found");
		}
		while(fileInput.hasNextLine()) {
			String[] line = fileInput.nextLine().split(regex);
			xStrings.add(line[xCol]);
			yStrings.add(line[yCol]);
		}
		fileInput.close();
		//convert ArrayLists into arrays for easier handling
		xValues = new double[xStrings.size()];
		yValues = new double[yStrings.size()];
		for(int i = 0; i < xValues.length && i < yValues.length; i++) {
			xValues[i] = Double.parseDouble(xStrings.get(i));
			yValues[i] = Double.parseDouble(yStrings.get(i));
		}
		//Values are now loaded into the program

		//make the grid for the graph
		graph = new Color[(int) s.getWidth()][(int) s.getHeight()];
		//make the grid white
		for(int x = 0; x < graph.length; x++) {
			for(int y = 0; y < graph[x].length; y++) {
				graph[x][y] = Color.WHITE;
			}
		}
	}

	//getters
	protected File getData() {
		return data;
	}

	public Color[][] getGraph() {
		return graph;
	}

	protected double[] getxValues() {
		return xValues;
	}

	protected double[] getyValues() {
		return yValues;
	}
	
	//setters
	public void setGraphPoint(int x, int y, Color c) {
		
	}
}
