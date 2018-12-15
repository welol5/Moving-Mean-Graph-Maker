package Core;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
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
	
	public void paintGraph(Color[][] graph) {
		
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
		setCenter(canvas);
	}
}


