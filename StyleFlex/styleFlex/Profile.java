package styleFlex;

import java.io.File;

public class Profile {

	String userName = "";
	String password = "";
	String email = "";
	double flexPoints = 0.0;
	String flexTitle = "Nonfactor";
	boolean inCheck = false;
	static File profilePic = new File("../StyleFlexMenu/DefaultProfilePic.jpeg");	
	
	public Profile() {
	}
	
	public Profile(String userName, String password, String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getFlexPoints() {
		return flexPoints;
	}

	public void setFlexPoints(double flexPoints) {
		this.flexPoints = flexPoints;
	}

	public String getFlexTitle() {
		return flexTitle;
	}

	public void setFlexTitle(String flexTitle) {
		this.flexTitle = flexTitle;
	}

	public boolean isInCheck() {
		return inCheck;
	}

	public void setInCheck(boolean inCheck) {
		this.inCheck = inCheck;
	}


	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public static File getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(File profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public String toString() {
		return "Profile [userName=" + userName + ", password=" + password + ", email=" + email + ", flexPoints="
				+ flexPoints + ", flexTitle=" + flexTitle + ", inCheck=" + inCheck + ", profilePic=" + profilePic + "]";
	}
}
