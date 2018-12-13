package Core;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
	
	public String[] getWorkers() {
		ArrayList<String> workerAddresses = new ArrayList<String>();
		for(Node w: getChildren()) {
			try {
				HBox work = (HBox)w;
				workerAddresses.add(((Text)(work.getChildren().get(0))).getText());
			} catch (ClassCastException e) {}
		}
		String[] temp = new String[workerAddresses.size()];
		return workerAddresses.toArray(temp);
	}
}
