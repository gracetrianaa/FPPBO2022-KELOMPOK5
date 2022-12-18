package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import application.HighScore;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
import model.ULAT;
import model.UlatBuluButton;
import model.UlatBuluSubScene;
import model.UlatPicker;


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
	private final static String FONT_PATH = "src/model/resources/MadouFutoMaru.ttf";
	
	private UlatBuluSubScene creditsSubscene;
	private UlatBuluSubScene helpSubscene;
	private UlatBuluSubScene scoreSubscene;
	private UlatBuluSubScene UlatChooserSubscene;
	
	private UlatBuluSubScene sceneToHide;
	
	
	List<UlatBuluButton> menuButtons;
	List<UlatPicker> UlatList;
	private ULAT choosenUlat;

	
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
		UlatChooserSubscene.getPane().getChildren().add(createUlatToChoose());
		UlatChooserSubscene.getPane().getChildren().add(createButtonToStart());
	}
	
	private void createScoreSubScene() {
		InfoLabel scoresLabel = new InfoLabel("SCORES");
		scoresLabel.setLayoutX(200);
		scoresLabel.setLayoutY(25);
		scoreSubscene.getPane().getChildren().add(scoresLabel);
		
		String[] HS = new String[3];
		for(int i = 0; i < 3; i++) {
			HS[i] = HighScore.getHighScore(i);
			Text highScore = new Text("TOP " + (i+1) + "\t\t" + HS[i]);
			try {
				highScore.setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 28));
			} catch (FileNotFoundException e) {
				highScore.setFont(Font.font("Verdana", 23));
			}
			highScore.setLayoutY((i*100)+120);
			highScore.setLayoutX(60);
			scoreSubscene.getPane().getChildren().add(highScore);
		}
	}
	
	private void createHelpSubScene() {
		InfoLabel helpLabel = new InfoLabel("TUTORIAL");
		helpLabel.setLayoutX(200);
		helpLabel.setLayoutY(25);
		Text helptext = new Text();
		try {
			helptext.setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 18));
		} catch (FileNotFoundException e1) {
			helptext.setFont(Font.font("Verdana", 40));
		}
		helptext.setLayoutX(100);
		helptext.setLayoutY(110);
		helptext.setTextAlignment(TextAlignment.CENTER);
		helptext.setText("1. Press play button\n\n2. Pick your 'ulat bulu' style\n\n3. Press start button\n\n"
				+ "4. Press 'UP' or 'DOWN' or 'RIGHT' or 'LEFT'\n button on your computer to change\n the direction of your 'ulat bulu'\n\n"
				+ "5. If the 'ulat bulu' crashes the wall or\ncollides it's own body, the game is OVER\n\n"
				+ "6. Press 'Retry' to play again");
		helpSubscene.getPane().getChildren().add(helpLabel);
		helpSubscene.getPane().getChildren().add(helptext);
	}
	
	private void createCreditsSubScene() {
		InfoLabel creditsLabel = new InfoLabel("CREDITS");
		creditsLabel.setLayoutX(200);
		creditsLabel.setLayoutY(25);
		Text creditstext = new Text();
		try {
			creditstext.setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 20));
		} catch (FileNotFoundException e1) {
			creditstext.setFont(Font.font("Verdana", 40));
		}
		creditstext.setLayoutX(80);
		creditstext.setLayoutY(120);
		creditstext.setTextAlignment(TextAlignment.CENTER);
		creditstext.setText("CREATED BY :\n\nWARDATUL AMALIA SAFITRI (5025211006)\n\nGRACETRIANA SURVINTA SEPTINAPUTRI (5025211199)\n\nNADIRA MILHA NAILUL FATH (50252111253)\n\n\n"
				+ "WITH REFERENCE FROM\n\nYOUTUBE MAHMOUD HAMWI");
		creditsSubscene.getPane().getChildren().add(creditsLabel);
		creditsSubscene.getPane().getChildren().add(creditstext);
	}
	
	private HBox createUlatToChoose() {
		HBox box = new HBox();
		box.setSpacing(100);
		UlatList = new ArrayList<>();
		for (ULAT ulat : ULAT.values()) {
			UlatPicker ulatToPick = new UlatPicker(ulat);
			UlatList.add(ulatToPick);
			box.getChildren().add(ulatToPick);
			ulatToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for (UlatPicker ulat : UlatList) {
						ulat.setIsCircleChoosen(false);
					}
					ulatToPick.setIsCircleChoosen(true);
					choosenUlat = ulatToPick.getUlat();
					
				}
			});
		}
		
		box.setLayoutX(300 - (118*2));
		box.setLayoutY(100);
		return box;
	}
	

	private UlatBuluButton createButtonToStart() {
		UlatBuluButton startButton = new UlatBuluButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				GameManager gameManager = new GameManager();
				gameManager.start(choosenUlat);
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
