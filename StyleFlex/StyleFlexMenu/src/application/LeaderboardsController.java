package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import styleFlex.testdb;

public class LeaderboardsController implements Initializable {

	@FXML
	private TableView<ProfileMaster> tableView;
	@FXML
	private TableColumn<ProfileMaster, Integer> rank;
	@FXML
	private TableColumn<ProfileMaster, String> userName;
	@FXML
	private TableColumn<ProfileMaster, String> title;
	@FXML
	private TableColumn<ProfileMaster, Double> flexPoints;
	@FXML
	private TableColumn<ProfileMaster, String> instagram;
	@FXML
	private Pagination pagination;

	Connection conn = testdb.getDBConnection();
	
	public void initialize(URL url, ResourceBundle rb) {

		try {

			buildData(conn, pagination);
			rank.setCellValueFactory(new PropertyValueFactory<ProfileMaster, Integer>("rank"));
			userName.setCellValueFactory(new PropertyValueFactory<ProfileMaster, String>("userName"));
			title.setCellValueFactory(new PropertyValueFactory<ProfileMaster, String>("title"));
			flexPoints.setCellValueFactory(new PropertyValueFactory<ProfileMaster, Double>("flexPoints"));
			instagram.setCellValueFactory(new PropertyValueFactory<ProfileMaster, String>("instagram"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildData(Connection conn, Pagination pagination) {

		pagination.setPageFactory(page -> {

			int rankGen = (page * 100) + 1;
			List<ProfileMaster> data = new ArrayList<>();

			try {

				int rowOffset = page * 100;
				ResultSet rs = conn.createStatement().executeQuery(
						"SELECT USERNAME, FLEX_TITLE, FLEX_POINTS, INSTAGRAM FROM PROFILES ORDER BY FLEX_POINTS DESC OFFSET "
								+ rowOffset + " ROWS FETCH NEXT 100 ROWS ONLY");

				while (rs.next()) {

					ProfileMaster profileMaster = new ProfileMaster();
					profileMaster.rank.setValue(rankGen);
					profileMaster.userName.set(rs.getString("USERNAME"));
					profileMaster.title.set(rs.getString("FLEX_TITLE"));
					profileMaster.flexPoints.set(rs.getDouble("FLEX_POINTS"));
					if (rs.getString("INSTAGRAM") == null) {
						profileMaster.instagram.set("No Gram");
					} else {
						profileMaster.instagram.set(rs.getString("INSTAGRAM"));
					}
					data.add(profileMaster);
					rankGen++;
				}

				tableView.getItems().setAll(data);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error on Building Data");
			}
			
			return tableView;
		});
	}
	
	public void openYourProfile(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();
		Stage stage1 = (Stage) source.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/YourProfile.fxml"));
		Scene scene = new Scene(root);
		stage1.setScene(scene);
	}
}
