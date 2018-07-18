package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import styleFlex.testdb;

public class YourProfileController implements Initializable {

	@FXML
	private TableView<UserMaster> tableView;
	@FXML
	private ImageView profilePic = new ImageView();
	@FXML
	private Label userName = new Label();
	@FXML
	private Label title = new Label();
	@FXML
	private Label flexPoints = new Label();
	@FXML
	private Label insta = new Label();
	@FXML
	private Label inCheck = new Label();
	@FXML
	private TableColumn<UserMaster, String> category;
	@FXML
	private TableColumn<UserMaster, String> brandName;
	@FXML
	private TableColumn<UserMaster, String> modelName;
	@FXML
	private TableColumn<UserMaster, Double> price;
	@FXML
	private TableColumn<UserMaster, Image> modelPic;
	@FXML
	private TableColumn<UserMaster, Image> brandPic;

	Connection conn = testdb.getDBConnection();
	
	private ObservableList<UserMaster> data;
	
	public void initialize(URL url, ResourceBundle rb) {

		try {

			buildData(conn);
			populateUserInfo(Main.currUser, conn);
			category.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("category"));
			brandName.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("brandName"));
			modelName.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("modelName"));
			price.setCellValueFactory(new PropertyValueFactory<UserMaster, Double>("price"));
			modelPic.setCellValueFactory(new PropertyValueFactory<UserMaster, Image>("modelPic"));
			brandPic.setCellValueFactory(new PropertyValueFactory<UserMaster, Image>("brandPic"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildData(Connection conn) {

		data = FXCollections.observableArrayList();

		try {

			ResultSet rs = conn.createStatement()
					.executeQuery("SELECT * FROM USER_APPAREL WHERE USERNAME = '" + Main.currUser + "'");

			while (rs.next()) {

				UserMaster userMaster = new UserMaster();
				InputStream brandStream = rs.getBinaryStream("BRAND_PIC");
				Image brandImg = new Image(brandStream);
				ImageView brandImgView = new ImageView(brandImg);
				brandImgView.setFitWidth(75);
				brandImgView.setFitHeight(75);
				userMaster.brandPic.set(brandImgView);
				userMaster.category.setValue(rs.getString("CATEGORY"));
				userMaster.brandName.set(rs.getString("BRAND_NAME"));
				userMaster.modelName.set(rs.getString("MODEL"));
				userMaster.price.set(rs.getDouble("PRICE"));
				InputStream apparelStream = rs.getBinaryStream("APPAREL_PIC");
				Image apparelImg = new Image(apparelStream);
				ImageView apparelImgView = new ImageView(apparelImg);
				apparelImgView.setFitWidth(100);
				apparelImgView.setFitHeight(75);
				userMaster.modelPic.set(apparelImgView);
				data.add(userMaster);

			}

			tableView.getItems().addAll(data);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
	}
	
	public void populateUserInfo(String currUser, Connection conn) {

		try {
			ResultSet rs = conn.createStatement()
					.executeQuery("SELECT * FROM PROFILES WHERE USERNAME = '" + currUser + "' ");
			if (rs.next()) {
				userName.setText(rs.getString("USERNAME"));
				title.setText(rs.getString("FLEX_TITLE"));
				flexPoints.setText(rs.getString("FLEX_POINTS"));
				boolean checkTest = rs.getBoolean("IN_CHECK");
				if (checkTest) {
					inCheck.setVisible(true);
				} else {
					inCheck.setVisible(false);
				}
				
				InputStream profilePicStream = rs.getBinaryStream("PROFILE_PIC");
				Image profileImg = new Image(profilePicStream);
				profilePic.setImage(profileImg);
				profilePic.setFitWidth(609);
				profilePic.setFitHeight(352);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void openMainMenu(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}

	public void openSearchMenu(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/SearchMenu.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
	
	public void openLeaderboards(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/Leaderboards.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}

}
