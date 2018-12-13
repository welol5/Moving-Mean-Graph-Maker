package GraphMakers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionThread implements Runnable {
	
	private Socket socket;
	private double[] values;
	private int job;
	private int range;
	private int[] yMap;
	private int height;
	private double maxYVal;
	
	public ClientConnectionThread(Socket connection, int jobNumber, double[] valuesToSend, int[] yMap, int range, int height, double MaxYVal){
		socket = connection;
		job = jobNumber;
		values = valuesToSend;
		this.range = range;
		this.yMap = yMap;
		this.height = height;
		this.maxYVal = MaxYVal;
	}

	@Override
	public void run() {
		
		//construct streams
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			//sleep the thread so the the worker has time to setup and wait for stream key
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		System.out.println("Connection made");
		
		//objects are ready to send and receive
		try {
			out.writeObject(values);
			out.flush();
			out.writeObject(yMap);
			out.flush();
			out.writeInt(range);
			out.flush();
			out.writeInt(height);
			out.flush();
			out.writeDouble(maxYVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//receive results
		
	}

}
