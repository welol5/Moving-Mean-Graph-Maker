package Core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WorkerSelector {
	
	public void display() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Stage window = new Stage();
		window.setResizable(false);
		
		//make the window a priority
		window.initModality(Modality.APPLICATION_MODAL);
		
		//set other default items
		window.setTitle("Edit Worker List");
		window.setWidth(screen.getWidth()/4);
		window.setHeight(screen.getHeight()/2);
		
		//make a root panel to put everthing into
		VBox root = new VBox();
		
		//window is a separate window to be used for getting data
		
		//add text to let the user know what to do
		Text instructions = new Text("Enter the IP address and port seperated by a \":\".");
		root.getChildren().add(instructions);
		
		
		
		//add a confirmation button
		Button confirmButton = new Button("confirm");
		confirmButton.setOnAction(e -> {
			window.close();
		});
		root.getChildren().add(confirmButton);
		
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
	}
	
}
