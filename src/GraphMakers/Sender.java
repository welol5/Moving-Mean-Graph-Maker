package GraphMakers;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Sender implements Runnable {
	
	private PrintWriter out;
	private int[] values;
	private int job;
	
	public Sender(OutputStream stream, int jobNumber, int[] valuesToSend) {
		out = new PrintWriter(new OutputStreamWriter(stream));
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
