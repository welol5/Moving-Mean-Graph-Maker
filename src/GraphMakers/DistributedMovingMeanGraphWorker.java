package GraphMakers;

public class DistributedMovingMeanGraphWorker implements Runnable {
	
	private double[] yValues;
	private int[] xValues;
	private double maxYVal;
	private int height;
	
	public DistributedMovingMeanGraphWorker(int[] xValues, double[] yValues, int height, double maxYVal) {
		this.xValues = xValues;
		this.yValues = yValues;
		this.maxYVal= maxYVal;
		this.height = height;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < xValues.length; i++) {
			int y = (int)map(yValues[i], maxYVal, 0, height, 0);
			System.out.println("(" + xValues[i] + "," + yValues[i] + "):(" + xValues[i] + "," + y + ")");
//			System.out.println("Max: " + maxYVal);
//			System.out.println("(" + yValues[i] + "," + y + ")");
			DistributedMovingMeanGraphWorkerServer.setGraphPos(xValues[i], y);
		}
		
	}
	
	private double map(double value, double oldHigh, double oldLow, double newHigh, double newLow) {
		return (((value-oldLow)/(oldHigh-oldLow))*(newHigh-newLow))+newLow;
	}

}
