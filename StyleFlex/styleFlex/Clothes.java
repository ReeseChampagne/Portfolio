package styleFlex;

import javafx.scene.image.Image;

public class Clothes {
	String category = "";
	String brandName = "0";
	String modelName = "";
	double price = 0;
	Image brandPic = null;
	Image apparelPic = null;

	public Clothes() {
		this.category = "Default";
		this.brandName = "0";
		this.modelName = "Default";
	}

	public Clothes(String category, String brandName, String modelName, double price) {
		this.category = category;
		this.brandName = brandName;
		this.modelName = modelName;
		this.price = price;
	}

	public String getBrandName() {
		return brandName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public String getModelName() {
		return modelName;
	}

	@Override
	public String toString() {
		return "Clothes [Category = " + category + ", Brand Name = " + brandName + ", Model = " + modelName
				+ ", Price = " + price + "]";
	}
}
