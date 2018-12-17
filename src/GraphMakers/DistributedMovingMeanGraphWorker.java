package GraphMakers;

public class DistributedMovingMeanGraphWorker implements Runnable {
	
	private double[] yValues;
	private int[] xValues;
	private double maxYVal;
	private int height;
	private int range;
	
	public DistributedMovingMeanGraphWorker(int[] xValues, double[] yValues, int height, double maxYVal, int range) {
		this.xValues = xValues;
		this.yValues = yValues;
		this.maxYVal= maxYVal;
		this.height = height;
		this.range = range;
	}

	@Override
	public void run() {
		
		//calculate differences in time
		double[] deltaY = new double[yValues.length];
		//calculate the largest difference
		double maxTimeDiff = -1;
		for(int i = 0; i < deltaY.length-range; i++) {
			double avgOverRange = 0;
			for(int k = 0; k < range; k++) {
				avgOverRange += yValues[i+k+1]-yValues[i+k];
			}
			avgOverRange /= range;
			if(avgOverRange > maxTimeDiff)maxTimeDiff = avgOverRange;
			deltaY[i] = avgOverRange;
		}
		
		System.out.println("height: " + height + " :MaxTimeDiff: " + maxTimeDiff);
		
		for(int i = 0; i < xValues.length; i++) {
			
			//map the values to the graph
			int y = (int)map(deltaY[i], maxTimeDiff, 0, height-1, 0);
			//System.out.println("(" + xValues[i] + "," + deltaY[i] + "):(" + xValues[i] + "," + y + ")");
			if(y > 500) {
				System.out.println("(" + xValues[i] + "," + y + ") : ("  + xValues[i] + "," + deltaY[i] + ")");
			}
			
			DistributedMovingMeanGraphWorkerServer.setGraphPos(xValues[i], y);
		}
		
	}
	
	private double map(double value, double oldHigh, double oldLow, double newHigh, double newLow) {
		return (((value-oldLow)/(oldHigh-oldLow))*(newHigh-newLow))+newLow;
	}

}
