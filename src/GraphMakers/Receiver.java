package GraphMakers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.paint.Color;

public class Receiver implements Runnable {
	
	private BufferedReader in;
	private boolean shutdown = false;
	
	private Color[] values;
	
	public Receiver(InputStream stream) {
		in = new BufferedReader(new InputStreamReader(stream));
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
		}
	}

}
