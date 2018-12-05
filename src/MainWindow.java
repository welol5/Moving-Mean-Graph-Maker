import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the main widow for the program. It holds the highest level panels and controls the exit conditions.
 * It is also responsible for passing information to the logical classes.
 * @author William Elliman
 *
 */
public class MainWindow extends JFrame{
	
	private JPanel contentPane;
	
	public MainWindow() {
		contentPane = new JPanel(new BorderLayout());
	}
}
