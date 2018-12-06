/**
 * The Worker class is responsible for calculating the values that will be plotted on the graph.
 * It calculates one of the columns of pixels on the graph.
 * @author William Elliman
 *
 */
public class Worker {
	
	/**
	 * 
	 * @param set
	 * @param range
	 */
	public static boolean[] consructColums(int[] set, int range, int maxHeight) {
		boolean[] colPoints = new boolean[maxHeight];
		
		//initialize the array to 0
		for(int i = 0; i < maxHeight; i++) {
			colPoints[i] = false;
		}
		
		//the height is the pixel height of the graph
		//the set should be used to calculate the height value
		
		for(int i = 0; i < set.length-range; i++) {
			int height = 0;
			
			for(int k = 0; k < range; k++) {
				height =+ set[i+k];
			}
			
			colPoints[height] = true;
		}
		
		return colPoints;
	}
	
}
