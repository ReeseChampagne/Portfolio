package application;

import javafx.beans.property.*;

public class ProfileMaster {

	public SimpleIntegerProperty rank = new SimpleIntegerProperty();
	public SimpleStringProperty userName = new SimpleStringProperty();
	public SimpleStringProperty title = new SimpleStringProperty();
	public SimpleStringProperty instagram = new SimpleStringProperty();
	public SimpleDoubleProperty flexPoints = new SimpleDoubleProperty();
	
	public Integer getRank() {
		return rank.get();
	}
	
	public String getUserName() {
		return userName.get();
	}

	public String getTitle() {
		return title.get();
	}

	public String getInstagram() {
		return instagram.get();
	}
	
	public Double getFlexPoints() {
		return flexPoints.get();
	}

	public IntegerProperty rankProperty() {
		return rank;
	}
	
	public StringProperty userNameProperty() {
		return userName;
	}

	public StringProperty titleProperty() {
		return title;
	}

	public StringProperty instagramProperty() {
		return instagram;
	}

	public DoubleProperty flexPointsProperty() {
		return flexPoints;
	}
}
