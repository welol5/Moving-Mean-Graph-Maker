package Core;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InfoPanel extends VBox {

	private File dataFile;

	public InfoPanel(Stage stage, double prefHeight) {
		super();
		this.setPrefHeight(prefHeight);

		//make the file details area
		GridPane details = new GridPane();
		details.setPrefHeight(this.getPrefHeight()/2);
		details.getStyleClass().add("basicPanel");

		//add items to display details about the upper half of the info panel
		//file info
		Text fileTitleLabel = new Text("File: ");
		fileTitleLabel.getStyleClass().add("subtitleFont");
		details.add(fileTitleLabel, 0, 0);
		Text fileTitle = new Text("No File Selected");
		fileTitle.getStyleClass().add("basicFont");
		details.add(fileTitle, 1, 0);
		
		//add details to this
		getChildren().add(details);
		
		//File IO and other user input
		GridPane filePane = new GridPane();
		filePane.setPrefHeight(this.getPrefHeight()/2);
		filePane.getStyleClass().add("basicPanel");
		filePane.setAlignment(Pos.BOTTOM_CENTER);
		
		//add select file button
		Button selectFileButton = new Button("Select File");
		selectFileButton.getStyleClass().add("basicFont");
		selectFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
				dataFile = fileChooser.showOpenDialog(stage);
				if(dataFile != null) {
					fileTitle.setText(dataFile.getName());
				}
			}
		});
		filePane.getChildren().add(selectFileButton);
		
		//add GraphStyle select
		ComboBox styleSelect = new ComboBox();
		
		//add the user input to this
		this.getChildren().add(filePane);
		
	}

	public File getDataFile() {
		return dataFile;
	}
}
