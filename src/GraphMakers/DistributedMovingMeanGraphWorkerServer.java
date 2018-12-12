package GraphMakers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class DistributedMovingMeanGraphWorkerServer {

	private static ArrayBlockingQueue<Socket> connections;
	public static final int DEFAULT_PORT = 40998;

	private static volatile boolean shutdown = false;

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
		while(!shutdown) {
			Socket connection = null;
			try {
				connection = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(connection != null) {
				addConnection(connection);
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

	public static Socket getConnection() {
		synchronized (connections){
			return connections.remove();
		}
	}

	public static void addConnection(Socket conn) {
		synchronized (connections) {
			connections.add(conn);
		}
	}
}
