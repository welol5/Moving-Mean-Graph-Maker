package GraphMakers;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Sender implements Runnable {
	
	private PrintWriter out;
	
	public Sender(OutputStream stream) {
		out = new PrintWriter(new OutputStreamWriter(stream));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
