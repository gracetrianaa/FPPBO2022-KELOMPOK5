package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.InfoLabel;
import model.UlatBuluButton;
import model.UlatBuluSubScene;


public class MenuManager {
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	private static final String LOGO = new String("model/resources/logo_ulatbulu.png");
	private ImageView LogoUlat = new ImageView(LOGO);
	private final static String FONT_PATH = "src/model/resources/LoveGlitch.ttf";
	
	private UlatBuluSubScene creditsSubscene;
	private UlatBuluSubScene helpSubscene;
	private UlatBuluSubScene scoreSubscene;
	private UlatBuluSubScene UlatChooserSubscene;
	
	private UlatBuluSubScene sceneToHide;
	
	
	List<UlatBuluButton> menuButtons;
	

	
	public MenuManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT );
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		mainStage.setTitle("ULAT BULU");
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
		
		
	}
	
	private void showSubScene(UlatBuluSubScene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	

	private void createSubScenes() {
		
		creditsSubscene = new UlatBuluSubScene();
		mainPane.getChildren().add(creditsSubscene);
		helpSubscene = new UlatBuluSubScene();
		mainPane.getChildren().add(helpSubscene);
		scoreSubscene = new UlatBuluSubScene();
		mainPane.getChildren().add(scoreSubscene);
		UlatChooserSubscene = new UlatBuluSubScene();
		mainPane.getChildren().add(UlatChooserSubscene);
		
		createUlatChooserSubScene();
		createScoreSubScene();
		createHelpSubScene();
		createCreditsSubScene();
	
	}

	private void createUlatChooserSubScene() {
		UlatChooserSubscene = new UlatBuluSubScene();
		mainPane.getChildren().add(UlatChooserSubscene);
		
		InfoLabel chooseUlatLabel = new InfoLabel("PICK ULAT BULU");
		chooseUlatLabel.setLayoutX(200);
		chooseUlatLabel.setLayoutY(25);
		UlatChooserSubscene.getPane().getChildren().add(chooseUlatLabel);
		UlatChooserSubscene.getPane().getChildren().add(createButtonToStart());
	}
	
	private void createScoreSubScene() {
		InfoLabel scoresLabel = new InfoLabel("SCORES");
		scoresLabel.setLayoutX(200);
		scoresLabel.setLayoutY(25);
		
		
		scoreSubscene.getPane().getChildren().add(scoresLabel);
	}
	
	private void createHelpSubScene() {
		InfoLabel helpLabel = new InfoLabel("HELP");
		helpLabel.setLayoutX(200);
		helpLabel.setLayoutY(25);
		
		helpSubscene.getPane().getChildren().add(helpLabel);
		
	}
	
	private void createCreditsSubScene() {
		InfoLabel creditsLabel = new InfoLabel("CREDITS");
		creditsLabel.setLayoutX(200);
		creditsLabel.setLayoutY(25);
		
		creditsSubscene.getPane().getChildren().add(creditsLabel);
		
	}
	

	private UlatBuluButton createButtonToStart() {
		UlatBuluButton startButton = new UlatBuluButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					GameManager gameManager = new GameManager();
					gameManager.start();
					mainStage.close();
				
			}
			
		});
		
		return startButton;
	}
		


	public Stage getMainStage() {
		return mainStage;
	}
	
	private void AddMenuButtons(UlatBuluButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	
	
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createStartButton() {
		UlatBuluButton startButton = new UlatBuluButton("PLAY");
		AddMenuButtons(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(UlatChooserSubscene);
				
			}
		}); 
	}
	
	private void createScoresButton() {
		UlatBuluButton scoresButton = new UlatBuluButton("SCORES");
		AddMenuButtons(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubscene);
				
			}
		}); 
	}
	
	private void createHelpButton() {
		UlatBuluButton helpButton = new UlatBuluButton("TUTORIAL");
		AddMenuButtons(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubscene);
				
			}
		}); 
	}
	
	private void createCreditsButton() {
		
		UlatBuluButton creditsButton = new UlatBuluButton("CREDITS");
		AddMenuButtons(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubscene);
				
				
			}
		}); 
	}
	
	private void createExitButton() {
		UlatBuluButton exitButton = new UlatBuluButton("EXIT");
		AddMenuButtons(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
		}); 
		
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("model/resources/bg_ulatbulu.png", 1024, 768, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		LogoUlat.setFitWidth(400);
		LogoUlat.setFitHeight(110);
		LogoUlat.setLayoutX(380);
		LogoUlat.setLayoutY(50);
		
		LogoUlat.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				LogoUlat.setEffect(new DropShadow());
				
			}
		});
		
		LogoUlat.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				LogoUlat.setEffect(null);
				
			}
		});
		
		mainPane.getChildren().add(LogoUlat);
		
	}
	
	

}
