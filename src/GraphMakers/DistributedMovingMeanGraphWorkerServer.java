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
				double[] allValues = null;
				int range = 0;
				try {
					allValues = (double[]) in.readObject();
					range = in.readInt();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Values received");
				//distribute values to the workers
				Thread[] workerThreads = new Thread[THREADS];
				DistributedMovingMeanGraphWorker[] workers = new DistributedMovingMeanGraphWorker[THREADS];
				for(int i = 0; i < THREADS; i++) {
					double[] values = Arrays.copyOfRange(allValues, i*(THREADS/allValues.length), (i*(THREADS/allValues.length))+range);
					workers[i] = new DistributedMovingMeanGraphWorker(values);
					workerThreads[i] = new Thread(workers[i]);
					workerThreads[i].start();
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

	
}
