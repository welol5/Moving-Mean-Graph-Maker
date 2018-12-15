package GraphMakers;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import Core.GraphStyle;
import javafx.scene.paint.Color;

public class DistributedMovingMeanGraphSupervisor extends GraphStyle{

	private String[] connections;
	private Socket[] workers;
	private int range;

	public DistributedMovingMeanGraphSupervisor(File file, String regex, int xCol, int yCol, Color c, int range, Dimension s, String[] workerAddresses) throws IllegalArgumentException {
		super(file, regex, xCol, yCol, s, c);
		connections = workerAddresses;
		this.range = range;
	}

	@Override
	public void run() {

		//make the connections
		workers = new Socket[connections.length];
		for(int i = 0; i < connections.length; i++) {
			try {
				workers[i] = new Socket(connections[i].split(":")[0], Integer.parseInt(connections[i].split(":")[1]));
			} catch (NumberFormatException | IOException e) {
				System.out.println("Error occured. Aborting operation.");
				e.printStackTrace();
				return;
			}
		}

		//calculate the values that the y values will be mapped to
		int[] yMap = new int[getxValues().length];
		for(int i = 0; i < yMap.length; i++) {
			yMap[i] = i/getSize().width;
		}

		//make connections to handle data
		//System.out.println("Make connections");
		Thread[] handlerThreads = new Thread[workers.length];
		ClientConnectionThread[] handlers = new ClientConnectionThread[workers.length];
		for(int i = 0; i < workers.length; i++) {
			//System.out.println((i*(getyValues().length/workers.length)) + " : " + ((i+1)*(getyValues().length/workers.length)-1));
			double[] arrayToSend = Arrays.copyOfRange(getyValues(), i*(getyValues().length/workers.length), (i+1)*(getyValues().length/workers.length));
			int[] yMapToSend = Arrays.copyOfRange(yMap, i*(yMap.length/workers.length), (i+1)*(yMap.length/workers.length));
			
			System.out.println("Arrays.copyOfRange (high,low) : (" + yMapToSend[yMapToSend.length-1]+ "," + yMapToSend[0] + ") " + yMap[yMap.length-1] + "," + yMap[0]);
			
			handlers[i] = new ClientConnectionThread(workers[i],i,arrayToSend,yMapToSend, range, getSize().height, getMaxYVal());
			handlerThreads[i] = new Thread(handlers[i]);
			handlerThreads[i].start();

		}

		//wait for the data to be received
		for(int i = 0; i < workers.length; i++) {
			try {
				handlerThreads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//threads are done
		//data is received
		//stitch data together
		//TODO
		System.out.println("Stitch");
		for(int i = 0; i < workers.length; i++) {
			//go through workers
			for(int x = 0; x < handlers[i].getGraphSeg().length; x++) {
				//go through x values
				for(int y = 0; y < handlers[i].getGraphSeg()[x].length; y++) {
					//set points
					if(handlers[i].getGraphSeg()[x][y]) {
						setGraphPoint(x, y);
					}
				}
			}
		}
		done = true;
	}
}
