package Core;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WorkerData extends VBox {
	
	public void addWorker(String address) {
		
		HBox worker = new HBox();
		
		Button removeButton = new Button("Remove");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getChildren().remove(worker);
			}
		});
		
		//add info to a HBox to be displayed
		worker.getChildren().addAll(new Text(address), removeButton);
		
		//add the worker info to the list
		getChildren().add(worker);
	}
}
