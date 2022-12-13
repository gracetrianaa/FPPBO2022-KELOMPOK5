package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DeadScene {
	private AnchorPane gameOverPane;
	private Scene gameOverScene;
	private Stage gameOverStage;
	//private Stage gameStage;
	
	public DeadScene() {
		gameOverPane = new AnchorPane();
		gameOverScene = new Scene(gameOverPane, 400, 400);
		gameOverStage = new Stage();
		gameOverStage.setScene(gameOverScene);
	}

	public void createGOScene(Stage primaryStage, int score) {
		
		InfoLabel endScore = new InfoLabel(Integer.toString(score));
		endScore.setLayoutX(100);
		endScore.setLayoutY(100);
		
		InfoLabel gameOver = new InfoLabel("GAME OVER");
		gameOver.setLayoutX(100);
		gameOver.setLayoutY(50);
		
		gameOverPane.getChildren().addAll(endScore, gameOver);
		
		UlatBuluButton retry = new UlatBuluButton("Retry");
		retry.setLayoutX(35);
		retry.setLayoutY(250);
		
		retry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main restart = new Main();
				try {
					gameOverStage.close();
					restart.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		UlatBuluButton menu = new UlatBuluButton("Main Menu");
		menu.setLayoutX(235);
		menu.setLayoutY(250);
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameOverStage.close();
			}
		});
		
		gameOverPane.getChildren().addAll(retry, menu);
		
		gameOverStage.show();
	}
}
