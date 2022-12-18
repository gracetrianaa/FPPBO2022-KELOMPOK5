package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOverManager {
	private AnchorPane gameOverPane;
	private Scene gameOverScene;
	private Stage gameOverStage;
	
	public final static String FONT_PATH = "application/resources/LoveGlitch.ttf";
	public final static String FONT_STYLE = "application/resources/MadouFutoMaru.ttf";
	
	public GameOverManager() {
		gameOverPane = new AnchorPane();
		gameOverScene = new Scene(gameOverPane, 450, 450);
		gameOverStage = new Stage();
		gameOverStage.setTitle("Ulat Bulu");
		gameOverStage.setScene(gameOverScene);
	}

	public void createGOScene(Stage primaryStage, int score) {
		
		Label endScore = new Label(Integer.toString(score));
		endScore.setPrefWidth(450);
		endScore.setLayoutY(100);
		endScore.setAlignment(Pos.CENTER);
		try {
			endScore.setFont(Font.loadFont(new FileInputStream(new File(FONT_STYLE)), 50));
		} catch (FileNotFoundException e1) {
			endScore.setFont(Font.font("Verdana", 40));
		}
		
		Label gameOver = new Label("GAME OVER");
		gameOver.setPrefWidth(450);
		gameOver.setLayoutY(40);
		gameOver.setAlignment(Pos.CENTER);
		try {
			gameOver.setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 50));
		} catch (FileNotFoundException e1) {
			gameOver.setFont(Font.font("Verdana", 40));
		}
		
		gameOverPane.getChildren().addAll(endScore, gameOver);
		
		Button retry = new Button("Retry");
		retry.setLayoutX(20);
		retry.setLayoutY(270);
		
		Button menu = new Button("Main Menu");
		menu.setLayoutX(230);
		menu.setLayoutY(270);
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameOverStage.close();
				primaryStage.close();
			}
		});
		
		gameOverPane.getChildren().addAll(retry, menu);
		
		gameOverStage.show();
	}
	
}