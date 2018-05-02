//Reese Champagne CS211 4/20/2018 Chapter 7 Project
package proj7;

public class Characters {

	String name = "";
	int location;

	public Characters() {
		name = "Default";
		location = 0;
	}

	public Characters(String name) {
		this.name = name;
		location = 0;
	}

	public int getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public void setLocation(int location) {
		this.location = location;
	}

}
