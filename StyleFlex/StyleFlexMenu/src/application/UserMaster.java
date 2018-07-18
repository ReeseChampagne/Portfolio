package application;

import javafx.beans.property.*;

public class UserMaster {

	public SimpleStringProperty category = new SimpleStringProperty();
	public SimpleObjectProperty<Object> brandPic = new SimpleObjectProperty<Object>();
	public SimpleStringProperty brandName = new SimpleStringProperty();
	public SimpleStringProperty modelName = new SimpleStringProperty();
	public SimpleDoubleProperty price = new SimpleDoubleProperty();
	public SimpleObjectProperty<Object> modelPic = new SimpleObjectProperty<Object>();

	public String getCategory() {
		return category.get();
	}

	@Override
	public String toString() {
		return "category = " + category + ", brandPic = " + brandPic + ", brandName = " + brandName + ", modelName = " + modelName + ", price = " + price + ", modelPic = " + modelPic;
	}

	public Object getBrandPic() {
		return brandPic.get();
	}

	public String getBrandName() {
		return brandName.get();
	}

	public String getModelName() {
		return modelName.get();
	}

	public Double getPrice() {
		return price.get();
	}

	public Object getModelPic() {
		return modelPic.get();
	}

	public StringProperty categoryProperty() {
		return category;
	}

	public ObjectProperty<Object> brandPicProperty() {
		return brandPic;
	}

	public StringProperty brandNameProperty() {
		return brandName;
	}

	public StringProperty modelNameProperty() {
		return modelName;
	}

	public DoubleProperty priceProperty() {
		return price;
	}

	public ObjectProperty<Object> modelPicProperty() {
		return modelPic;
	}

}
