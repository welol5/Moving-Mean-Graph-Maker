package GraphMakers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionThread implements Runnable {
	
	private Socket socket;
	private PrintWriter out;
	private int[] values;
	private int job;
	
	public ClientConnectionThread(Socket connection, int jobNumber, int[] valuesToSend) throws IOException{
		socket = connection;
		out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
		job = jobNumber;
		values = valuesToSend;
	}

	@Override
	public void run() {
		//make the string to send
		String output = "" + job;
		
		for(int i = 0; i < values.length; i++) {
			output += " " + values[i];
		}
		
		out.println(output);
		out.flush();
		
		
	}

}
