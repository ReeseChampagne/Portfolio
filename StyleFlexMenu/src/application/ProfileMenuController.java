package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileMenuController {

	public void openMainMenu(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
}
