package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import model.ULAT;
import model.UlatBuluButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
	
	public final static String FONT_PATH = "src/model/resources/LoveGlitch.ttf";
	public final static String FONT_STYLE = "src/model/resources/MadouFutoMaru.ttf";
	
	public GameOverManager() {
		gameOverPane = new AnchorPane();
		gameOverScene = new Scene(gameOverPane, 450, 450);
		gameOverStage = new Stage();
		gameOverStage.setTitle("Ulat Bulu");
		gameOverStage.setScene(gameOverScene);
		createBackground();
	}

	public void createGOScene(Stage primaryStage, int score, ULAT ulatChoosen) {
		
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
		
		UlatBuluButton retry = new UlatBuluButton("Retry");
		retry.setLayoutX(20);
		retry.setLayoutY(270);
		
		retry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GameManager restart = new GameManager();
				try {
					gameOverStage.close();
					primaryStage.close();
					restart.start(ulatChoosen);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		UlatBuluButton menu = new UlatBuluButton("Main Menu");
		menu.setLayoutX(230);
		menu.setLayoutY(270);
		
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuManager Menu = new MenuManager();
				gameOverStage.close();
				primaryStage.close();
				Stage menuStage = Menu.getMainStage();
				menuStage.show();
			}
		});
		
		gameOverPane.getChildren().addAll(retry, menu);
		
		gameOverStage.show();
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("model/resources/bg_ulatbulu.png", 450, 450, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		gameOverPane.setBackground(new Background(background));
	}
}
