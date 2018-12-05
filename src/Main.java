import java.io.File;

/**
 * This class is just made to run the program.
 * @author William Elliman
 *
 */
public class Main {
	public static void main(String[] args) {
		
		File data = null;
		int x = -1,y = -1;
		
		//check args for the values needed.
		for(int i = 0; i < args.length; i++) {
			if(!args[i].startsWith("-")) {
				continue;
			} else if(args[i].equalsIgnoreCase("-d")){
				//the -d command is for a data file that follows
				data = new File(args[i+1]);
			} else if(args[i].equalsIgnoreCase("--x")) {
				x = Integer.parseInt(args[i+1]);
			} else if(args[i].equalsIgnoreCase("--y")) {
				y = Integer.parseInt(args[i+1]);
			}
		}
		
		if(data != null && x != -1 && y != -1) {
			MainWindow window = new MainWindow(data,x,y);
			window.setVisible(true);
		}
		
	}
}
