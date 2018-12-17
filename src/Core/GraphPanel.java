package Core;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GraphPanel extends BorderPane {
	
	private Canvas canvas;
	private GraphicsContext gc;
	
	public GraphPanel() {
		super();
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();
	}
	
	public void setCenterPane(Node node) {
		setCenter(node);
	}
	
	public Node getCenterPane() {
		return this.getCenter();
	}
	
	public void setLoadingScreen() {
		Text loadingText = new Text("Loading");
		loadingText.getStyleClass().add("subtitleFont");
		setCenter(loadingText);
	}
	
	public void paintGraph(Color[][] graph, double maxYValue, double maxXValue) {
		
		//debug
//		System.out.println("x: " + this.getWidth());
//		System.out.println("y: " + this.getHeight());
		
		canvas.setHeight(this.getPrefHeight());
		canvas.setWidth(this.getPrefWidth());
		for(int x = 0; x < graph.length; x++) {
			for(int y = 0; y < graph[x].length; y++) {
				gc.setFill(graph[x][y]);
				gc.fillRect(x, y, 1, 1);
			}
		}
		this.setCenter(canvas);
		
		//give the graph y-axis units
		VBox yUnitBox = new VBox();
		VBox yUpperHalf = new VBox();
		VBox yLowerHalf = new VBox();
		Text ymin = new Text("0");
		//this is the wrong value, it is the maximum time, not the maximum change in time
		Text ymax = new Text("Not Calculated yet");
		yUpperHalf.getChildren().add(ymax);
		yLowerHalf.getChildren().add(ymin);
		yLowerHalf.setAlignment(Pos.BOTTOM_CENTER);
		yUpperHalf.setPrefHeight(this.getPrefHeight()/2);
		yLowerHalf.setPrefHeight(this.getPrefHeight()/2);
		yUnitBox.getChildren().add(yUpperHalf);
		yUnitBox.getChildren().add(yLowerHalf);
		//add to this
		//the y-axis labels cut off some of the graph
		this.setLeft(yUnitBox);
		
		//give the graph x-axis units
		HBox xUnitBox = new HBox();
		HBox xLeftHalf = new HBox();
		HBox xRightHalf = new HBox();
		Text xmin = new Text("0");
		Text xmax = new Text(String.valueOf(maxXValue));
		xLeftHalf.getChildren().add(xmin);
		xRightHalf.getChildren().add(xmax);
		xRightHalf.setAlignment(Pos.CENTER_RIGHT);
		xLeftHalf.setPrefWidth(this.getPrefWidth()/2);
		xRightHalf.setPrefWidth(this.getPrefWidth()/2);
		xUnitBox.getChildren().add(xLeftHalf);
		xUnitBox.getChildren().add(xRightHalf);
		//add to this
		//this does not appear at the moment because the canvas is taking up the entire area
		//this.setBottom(xUnitBox);
	}
}


