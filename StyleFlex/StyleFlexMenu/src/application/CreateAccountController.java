package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import styleFlex.testdb;

public class CreateAccountController {

	@FXML
	TextField userName = new TextField();
	@FXML
	PasswordField password = new PasswordField();
	@FXML
	PasswordField confirmPassword = new PasswordField();
	@FXML
	TextField email = new TextField();
	@FXML
	Label errorMessage = new Label();
	
	public void openMainMenu(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
		
	}
	
	public void createNewAccount(ActionEvent event) {
		
		try {
		String userNameText = userName.getText().trim();
		String passwordText = password.getText().trim();
		String confirmPasswordText = confirmPassword.getText().trim();
		String emailText = email.getText().trim();
		errorMessage.setVisible(false);
		
		System.out.println(userNameText + " " + passwordText + " " + confirmPasswordText + " " + emailText);
			
		if (userName.getText().trim().isEmpty() || password.getText().trim().isEmpty() || confirmPassword.getText().trim().isEmpty() || email.getText().trim().isEmpty()) {		
			errorMessage.setVisible(true);
			errorMessage.setText("All fields must be filled!");
			throw new IOException("All fields must be filled!");
		}
		else if (testdb.checkIfUserExists(userNameText)) {
			errorMessage.setVisible(true);
			errorMessage.setText("Username is taken!");
			password.clear();
			confirmPassword.clear();
			email.clear();
			throw new IOException("Username is taken!");
		}
		else if (testdb.checkIfEmailAvailable(emailText)) {
			errorMessage.setVisible(true);
			errorMessage.setText("Email is taken!");
			email.clear();
			throw new IOException("Email is taken!");
		}
		else if (!testdb.validateEmail(emailText)) {
			errorMessage.setVisible(true);
			errorMessage.setText("Email is not valid!");
			email.clear();
			throw new IOException("Email is not valid!");
		}
		else if (!passwordText.equals(confirmPasswordText)) {
			errorMessage.setVisible(true);
			errorMessage.setText("Password fields must match exactly!");
			password.clear();
			confirmPassword.clear();
			throw new IOException("Password fields must match exactly!");
		}
		errorMessage.setVisible(true);
		errorMessage.setText("Account creation successful!");
		errorMessage.setTextFill(Color.MEDIUMVIOLETRED);
		testdb.createProfile(userNameText, passwordText, emailText);
		
		} catch (IOException e) {
			e.toString();
		}

	}
	
}
