package GraphMakers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * Receives a block of values from an InputStream and assigns the corresponding values in the array to be true.
 * @author William
 *
 */
public class Receiver implements Runnable {
	
	private BufferedReader in;
	private boolean shutdown = false;
	
	private boolean[] values;
	
	public Receiver(InputStream stream, int height) {
		in = new BufferedReader(new InputStreamReader(stream));
		values = new boolean[height];
	}

	@Override
	public void run() {
		
		while(!shutdown) {
			String data = null;
			try {
				data = in.readLine();
			} catch (IOException e) {
				continue;
			}
			
			//check if done
			if(data == null || data.equalsIgnoreCase("done")) {
				shutdown();
			}
			
			//add data to the list
			values[Integer.parseInt(data)] = true;
		}
	}

	public void shutdown() {
		shutdown = true;
	}
}
