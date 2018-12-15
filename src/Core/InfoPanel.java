package Core;

import java.awt.Dimension;
import java.io.File;

import GraphMakers.DistributedMovingMeanGraphSupervisor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InfoPanel extends VBox {

	private File dataFile;
	private ComboBox<String> styleSelect;
	private Dimension graphDimension;
	private String[] workerData;
	private Thread graphThread;
	private WorkerSelector selector = new WorkerSelector();

	public InfoPanel(Stage stage, double prefHeight, Dimension graphDim, GraphPanel panel) {
		super();
		this.setPrefHeight(prefHeight);
		graphDimension = graphDim;

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

		//keep go button at the bottom by setting the y value to yPos
		int yPos = 0;

		//File IO and other user input
		GridPane filePane = new GridPane();
		filePane.setPrefHeight(this.getPrefHeight()/2);
		filePane.getStyleClass().add("basicPanel");
		filePane.setAlignment(Pos.BOTTOM_CENTER);
		filePane.setVgap(prefHeight/100);
		//filePane.setPadding();

		//this is needed later
		Button workerSelector = new Button("Edit Worker List");

		//add GraphStyle select
		styleSelect = new ComboBox<String>();
		styleSelect.setPromptText("Select Graph Type");
		styleSelect.getStyleClass().add("basicFont");
		for(int i = 0; i < MainWindow.GRAPH_STYLES.length; i++) {
			styleSelect.getItems().add(MainWindow.GRAPH_STYLES[i]);
		}
		styleSelect.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.startsWith("Distributed")) {
					workerSelector.setDisable(false);
				} else {
					workerSelector.setDisable(true);
				}
			}
		});
		filePane.add(styleSelect,0,yPos);
		yPos++;

		//add distributed computer select
		workerSelector.getStyleClass().add("basicFont");
		workerSelector.setOnAction(e -> {
			selector.display();
			workerData = selector.getData();
		});
		filePane.add(workerSelector, 0, yPos);
		yPos++;
		workerSelector.setDisable(true);

		//add select file button
		Button selectFileButton = new Button("Select File");
		selectFileButton.getStyleClass().add("basicFont");
		//selectFileButton.setAlignment(Pos.CENTER);
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
		filePane.add(selectFileButton,0,yPos);
		yPos++;

		Button goButton = new Button("Go");
		goButton.getStyleClass().add("basicFont");
		goButton.setOnAction(e -> {
			String graphType = styleSelect.getValue();
			GraphStyle graph;

			//make the correct type of graph
			if(graphType.equalsIgnoreCase("Distributed Moving Mean")) {
				//System.out.println("Make Graph");
				graph = new DistributedMovingMeanGraphSupervisor(dataFile, " ", 0,1, Color.BLUEVIOLET, /*TODO range*/20,graphDimension, workerData);
				graphThread = new Thread(graph);
				graphThread.start();

				panel.setLoadingScreen();
				//wait for the data
				//use a loop so that the program does not hang
				while(!graph.isDone()) {}

				panel.paintGraph(graph.getGraph());

			}
		});
		filePane.add(goButton, 0, yPos);
		yPos++;

		//add the user input to this
		this.getChildren().add(filePane);
	}

	public File getDataFile() {
		return dataFile;
	}
}
