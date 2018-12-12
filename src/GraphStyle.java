import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;

public abstract class GraphStyle {

	private File data;
	private Color[][] graph;

	private int[] xValues;
	private int[] yValues;

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
		xValues = new int[xStrings.size()];
		yValues = new int[yStrings.size()];
		for(int i = 0; i < xValues.length && i < yValues.length; i++) {
			xValues[i] = Integer.parseInt(xStrings.get(i));
			yValues[i] = Integer.parseInt(yStrings.get(i));
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
		
		//dataProcessing can now occur
	}

	//getters
	protected File getData() {
		return data;
	}

	public Color[][] getGraph() {
		return graph;
	}

	protected int[] getxValues() {
		return xValues;
	}

	protected int[] getyValues() {
		return yValues;
	}
	
	//setters
	public void setGraphPoint(int x, int y, Color c) {
		
	}
}
