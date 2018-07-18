package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import styleFlex.testdb;

public class SearchMenuController implements Initializable {

	@FXML
	private FilteredTableView<UserMaster> tableView;
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
	@FXML
	private ChoiceBox<String> categoryChoicesFilter;
	@FXML
	private ChoiceBox<String> brandChoicesFilter;
	@FXML
	private ChoiceBox<String> modelChoicesFilter;
	@FXML
	private TextField minPriceFilter = new TextField();
	@FXML
	private TextField maxPriceFilter = new TextField();

	Connection conn = testdb.getDBConnection();

	private ObservableList<UserMaster> data;
	private ObservableList<String> categoryData;
	private ObservableList<String> brandData;
	private ObservableList<String> modelData;

	public void initialize(URL url, ResourceBundle rb) {

		try {

			buildData(conn);
			buildComboData(conn);
			category.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("category"));
			brandName.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("brandName"));
			modelName.setCellValueFactory(new PropertyValueFactory<UserMaster, String>("modelName"));
			price.setCellValueFactory(new PropertyValueFactory<UserMaster, Double>("price"));
			modelPic.setCellValueFactory(new PropertyValueFactory<UserMaster, Image>("modelPic"));
			brandPic.setCellValueFactory(new PropertyValueFactory<UserMaster, Image>("brandPic"));
			addListeners();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildData(Connection conn) {

		data = FXCollections.observableArrayList();

		try {

			ResultSet rs = conn.createStatement().executeQuery("SELECT BRAND_PIC, CATEGORY, BRAND_NAME, MODEL, PRICE, APPAREL_PIC FROM APPAREL");

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

			tableView.setDataList(data);
			tableView.getItems().addAll();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
	}

	public void buildComboData(Connection conn) {

		categoryData = FXCollections.observableArrayList();
		brandData = FXCollections.observableArrayList();
		modelData = FXCollections.observableArrayList();

		categoryData.add(null);
		brandData.add(null);
		modelData.add(null);
		
		try {

			ResultSet rs = conn.createStatement().executeQuery("SELECT DISTINCT CATEGORY FROM APPAREL");

			while (rs.next()) {

				String category = rs.getString("CATEGORY");
				categoryData.add(category);
			}
			
			categoryChoicesFilter.setItems(categoryData);

			rs = conn.createStatement().executeQuery("SELECT DISTINCT BRAND_NAME FROM APPAREL");

			while (rs.next()) {

				String brandName = rs.getString("BRAND_NAME");
				brandData.add(brandName);
			}

			brandChoicesFilter.setItems(brandData);

			rs = conn.createStatement().executeQuery("SELECT DISTINCT MODEL FROM APPAREL");

			while (rs.next()) {

				String modelName = rs.getString("MODEL");
				modelData.add(modelName);
			}

			modelChoicesFilter.setItems(modelData);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

	}

	private void addListeners() {

		minPriceFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			tableView.setPredicate(0, minPrice -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				Double minPriceText = Double.parseDouble(newValue);
				if (minPrice.getPrice() > minPriceText) {
					return true;
				}
				return false;
			});
		});

		maxPriceFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			tableView.setPredicate(1, maxPrice -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				Double maxPriceText = Double.parseDouble(newValue);
				if (maxPrice.getPrice() < maxPriceText) {
					return true;
				}
				return false;
			});
		});

		categoryChoicesFilter.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					tableView.setPredicate(2, category -> {
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						String categoryText = newValue;
						if (categoryText.equals(category.getCategory())) {
							return true;
						}
						return false;
					});
				});

		brandChoicesFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			tableView.setPredicate(3, brand -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String brandText = newValue;
				if (brandText.equals(brand.getBrandName())) {
					return true;
				}
				return false;
			});
		});

		modelChoicesFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			tableView.setPredicate(4, model -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String modelText = newValue;
				if (modelText.equals(model.getModelName())) {
					return true;
				}
				return false;
			});
		});
	}

	public void openYourProfile(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/YourProfile.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}

	public void addToApparel(ActionEvent event) {
		
		String category = tableView.getSelectionModel().getSelectedItem().getCategory();
		String brand = tableView.getSelectionModel().getSelectedItem().getBrandName();
		String model = tableView.getSelectionModel().getSelectedItem().getModelName();
		Double price = tableView.getSelectionModel().getSelectedItem().getPrice();
		testdb.addUserApparel(Main.getCurrUser(), category, brand, model, testdb.getBrandPic(model, price), testdb.getModelPic(model, price), price);
	}
}
