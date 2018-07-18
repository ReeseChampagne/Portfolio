package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import styleFlex.testdb;

public class MainMenuController {

	@FXML
	TextField userName = new TextField();
	@FXML
	PasswordField password = new PasswordField();
	@FXML
	Label errorMessage = new Label();
	
	public void openYourProfile(ActionEvent event) throws IOException {

		if (testdb.validateLoginCredentials(userName.getText().trim(), password.getText().trim())) {
			
			Main.changeUser(userName.getText().trim());
			Node source = (Node) event.getSource();
			Stage stage1 = (Stage) source.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/YourProfile.fxml"));
			Scene scene = new Scene(root);
			stage1.setScene(scene);

		} else {
			errorMessage.setVisible(true);
		}
		
	}
	
	public void openMainMenu(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
	
	public void openProfileMenu(ActionEvent event) throws IOException {
		
		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileMenu.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
	
	public void openForgotPassword(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/ForgotPassword.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
	
	public void openForgotUser(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/ForgotUser.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
	
	public void openCreateAccount(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/CreateAccount.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
	
}
