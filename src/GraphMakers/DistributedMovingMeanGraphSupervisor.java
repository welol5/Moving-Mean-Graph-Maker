package GraphMakers;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

import Core.GraphStyle;

public class DistributedMovingMeanGraphSupervisor extends GraphStyle{
	
	private String[] connections;
	private Socket[] workers;
	private int range;

	public DistributedMovingMeanGraphSupervisor(File file, String regex, int xCol, int yCol, int range, Dimension s, String[] workerAddresses) throws IllegalArgumentException {
		super(file, regex, xCol, yCol, s);
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
		
		//make connections to handle data
		System.out.println("Make connections");
		Thread[] handlerThreads = new Thread[workers.length];
		ClientConnectionThread[] handlers = new ClientConnectionThread[workers.length];
		for(int i = 0; i < workers.length; i++) {
			System.out.println((i*(getyValues().length/workers.length)) + " : " + ((i+1)*(getyValues().length/workers.length)-1));
			double[] arrayToSend = Arrays.copyOfRange(getyValues(), i*(getyValues().length/workers.length), (i+1)*(getyValues().length/workers.length)-1);
			handlers[i] = new ClientConnectionThread(workers[i],i,arrayToSend, range);
			handlerThreads[i] = new Thread(handlers[i]);
			handlerThreads[i].start();
			
		}
	}
}
