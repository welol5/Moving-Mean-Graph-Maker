import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class deals with calculating information for the graph to use when it is plotting the data.
 * @author William Elliman
 *
 */
public class GraphManager {
	
	private int[] x;
	private int[] y;
	
	private int dataPointCount;
	
	/**
	 * The constructor of the GraphManager starts instantly starts calculating out the values of the graph.
	 * @param data : The file that has the data. The data for this version should have values separated by spaces.
	 * @param x : The values that will be used for the x axis.
	 * @param y : The values that will be used for the y axis.
	 */
	public GraphManager(Graph parent, File data, int xa, int ya)  throws FileNotFoundException{
		
		//make a pass over the file to put the values in x and y into arrays for easier access.
		Scanner in = new Scanner(data);
		
		//while the file has another line, add the lines to an ArrayList of Strings.
		List<String> lines = new ArrayList<String>();
		while(in.hasNextLine()) {
			lines.add(in.nextLine());
		}
		//lines is now a list of the lines of text as Strings
		
		//initialize instance variables
		dataPointCount = lines.size();
		this.x = new int[dataPointCount];
		this.y = new int[dataPointCount];
		
		//populate the arrays
		for(int i = 0; i < dataPointCount; i++) {
			String[] line = lines.get(i).split(" ");
			x[i] = Integer.parseInt(line[xa]);
			y[i] = Integer.parseInt(line[ya]);
		}
		
		//data is loaded into the program
		System.out.println("Data loaded");
		
		//find the maximum x and y values
		int xMax = -1;
		int yMax = -1;
		for(int i = 0; i < dataPointCount; i++) {
			if(xMax < x[i]) {
				xMax = x[i];
			}
			if(yMax < y[i]) {
				yMax = y[i];
			}
		}
		
		//set the x and y max values
		parent.setXMax(xMax);
		parent.setYMax(yMax);
		
		
	}
}
