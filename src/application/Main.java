package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.GameManager;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GameManager manager = new GameManager();
			manager.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
