package styleFlex;

public class CreateApparel {
	
	public static Clothes[] createApparel() {

		Clothes[] clothing = new Clothes[100]; // Instantiate apparel arrays
		String[] brandName = { "Hanes", "Ralph Lauren", "Nike", "Great Value" };
		String[] model = { "Stylemark", "Extra Sleek", "Low Town", "Kool Klub", "Club Baller Tee", "ValuePlus", "S7v7n",
				"PoloBasic", "Clean Life", "MostWanted"};

		for (int p = 80; p < clothing.length; p++) { // Generate shoes
			Clothes shoe = new Clothes("Shoes", brandName[p % 4], model[p % 10], Math.round(Math.random() * 4500));
			clothing[p] = shoe;
		}

		for (int j = 60; j < 80; j++) { // Generate underwear
			Clothes underwear = new Clothes("Underwear", brandName[j % 4], model[j % 10], Math.round(Math.random() * 4500));
			clothing[j] = underwear;
		}

		for (int l = 40; l < 60; l++) { // Generate socks
			Clothes socks = new Clothes("Socks", brandName[l % 4], model[l % 10], Math.round(Math.random() * 4500));
			clothing[l] = socks;
		}

		for (int k = 20; k < 40; k++) { // Generate shirts
			Clothes shirts = new Clothes("Shirts", brandName[k % 4], model[k % 10], Math.round(Math.random() * 4500));
			clothing[k] = shirts;
		}

		for (int i = 0; i < 20; i++) { // Generate pants
			Clothes pants = new Clothes("Pants", brandName[i % 4], model[i % 10], Math.round(Math.random() * 4500));
			clothing[i] = pants;
		}

		return clothing;
	}
	
}
