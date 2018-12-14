package GraphMakers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class DistributedMovingMeanGraphWorkerServer {

	public static final int DEFAULT_PORT = 40998;
	private static volatile boolean shutdown = false;
	
	public static final int THREADS = 4;
	private static boolean[][] graph;

	public static void main(String[] args) {

		ServerSocket server = null;

		try {
			//get port form args
			if(args.length > 0) {
				boolean hasPort = false;
				for(int i = 0; i < args.length; i++) {
					if(args[i].equalsIgnoreCase("-p")) {
						server = new ServerSocket(Integer.parseInt(args[i+1]));
						hasPort = true;
					}
				}
				
				//make a server with the default port if none was made
				if(!hasPort) {
					server = new ServerSocket(DEFAULT_PORT);
				}
			} else {
				//not enough args specified
				server = new ServerSocket(DEFAULT_PORT);
			}
		} catch (IOException e) {
			System.out.println("Error occured. Server aborted.");
			e.printStackTrace();
		}
		
		//a server has been created and can start accepting connections
		System.out.println("Server listening on: " + server.getLocalPort());
		while(!shutdown) {
			Socket connection = null;
			try {
				connection = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(connection != null) {
				
				//make worker objects here and have them do work
				ObjectInputStream in = null;
				ObjectOutputStream out = null;
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Connection made");
				
				//objects are ready to send and receive
				//get the arrays of values
				double[] allYValues = null;
				int[] allXValues = null;
				int range = -1;
				int height = -1;
				double maxYVal = -1;
				try {
					allYValues = (double[]) in.readObject();
					//x values correspond to what column of pixels the y values map to
					allXValues = (int[]) in.readObject();
					System.out.println("Read arrays");
					range = in.readInt();
					System.out.println("Read range");
					height = in.readInt();
					System.out.println("Read height");
					maxYVal = in.readDouble();
					System.out.println("Read all values");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//setup graph segment
				graph = new boolean[allXValues[allXValues.length-1]][height];
				
				System.out.println("Values received");
				System.out.println("# of values: " + allYValues.length);
				//distribute values to the workers
				Thread[] workerThreads = new Thread[THREADS];
				DistributedMovingMeanGraphWorker[] workers = new DistributedMovingMeanGraphWorker[THREADS];
				for(int i = 0; i < THREADS; i++) {
					int low = i*(allYValues.length/THREADS);
					int high = ((i+1)*(allYValues.length/THREADS))+range;
					//prevent out of bounds
					if(high > allYValues.length) {
						high = allYValues.length;
					}
					//debug
					System.out.println("Low: " + low);
					System.out.println("High: " + high);
					
					//get the subsets
					double[] yValues = Arrays.copyOfRange(allYValues,low, high);
					int[] xValues = Arrays.copyOfRange(allXValues,low, high);
					
					//give the workers the subsets
					workers[i] = new DistributedMovingMeanGraphWorker(xValues, yValues, height, maxYVal);
					workerThreads[i] = new Thread(workers[i]);
					workerThreads[i].start();
				}
				
				//wait for threads to complete
				for(int i = 0; i < THREADS; i++) {
					try {
						workerThreads[i].join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//send back values calculated
				try {
					out.writeObject(graph);
					out.flush();
					System.out.println("Calculations complete and data sent.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		shutdown = true;
	}

	public static void setGraphPos(int x, int y) {
		synchronized(graph) {
			graph[x][y] = true;
		}
	}
}
