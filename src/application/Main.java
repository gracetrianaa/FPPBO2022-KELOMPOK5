package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.MenuManager;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage){
		try {
			MenuManager manager = new MenuManager();
			primaryStage = manager.getMainStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
