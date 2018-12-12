package GraphMakers;
import java.awt.Dimension;
import java.io.File;

import Core.GraphStyle;

public class DistributedMovingMeanGraphSupervisor extends GraphStyle{

	public DistributedMovingMeanGraphSupervisor(File file, String regex, int xCol, int yCol, Dimension s) throws IllegalArgumentException {
		super(file, regex, xCol, yCol, s);
	}
	
	@Override
	public void run() {
		
	}
}
