package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

public class Main extends Application {

	public static String currUser = "Default";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();

	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void changeUser(String newUser) {
		currUser = newUser;
	}
	
	public static String getCurrUser() {
		return currUser;
	}
	
}
