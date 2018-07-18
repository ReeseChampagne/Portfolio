package styleFlex;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class testdb { // Instantiate H2 DB tables and populate generated apparel.
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost/~/test";
	private static String DB_USER = "admin";
	private static String DB_PASSWORD = "admin";

	public static void main(String[] args) {
		// initializeProgram();
		// dropAllTables();
		// create301Profiles();
	}

	public static Connection getDBConnection() {

		Connection conn = null;

		try {

			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	private static void initializeProgram() {
		createProfilesTable();
		createApparelTable();
		createUserApparelTable();
		Clothes[] clothing = CreateApparel.createApparel();
		System.out.println(populateApparel(clothing) + " rows inserted.");
	}

	private static void dropAllTables() {
		dropProfilesTable();
		dropApparelTable();
		dropUserApparelTable();
	}

	private static void create301Profiles() {

		int userName = 1;
		int password = 1;
		int email = 1;
		
		String sql = "INSERT INTO PROFILES ( USERNAME, PASSWORD, EMAIL, PROFILE_PIC, FLEX_POINTS) VALUES (?,?,?,?,?)";
		
		try (Connection conn = getDBConnection()) {

			PreparedStatement crAC = conn.prepareStatement(sql);
			for (int i = 0; i < 301; i++) {
			crAC.setInt(1, userName);
			crAC.setInt(2, password);
			crAC.setInt(3, email);
			File image = new File("../StyleFlexMenu/DefaultProfilePic.jpeg");
			FileInputStream fis = new FileInputStream(image);
			crAC.setBinaryStream(4, fis, (int) image.length());
			crAC.setDouble(5, Math.random() * 150);

			crAC.execute();
			userName++;
			email++;
			}
			conn.commit();

		} catch (Exception e) {
			System.out.println("Account not created.");
		}

	}
	
	private static void createProfilesTable() {

		try (Connection conn = getDBConnection()) {

			System.out.println("Successfully connected in createProfilesTable method.");

			Statement crPF = conn.createStatement();
			crPF.executeUpdate(
					"CREATE TABLE PROFILES ( USERNAME VARCHAR2(15) NOT NULL PRIMARY KEY, PASSWORD VARCHAR2(15) NOT NULL, EMAIL VARCHAR2(40) UNIQUE NOT NULL, FLEX_POINTS NUMBER(7) DEFAULT '0', FLEX_TITLE VARCHAR2(255) DEFAULT 'Non-Factor', INSTAGRAM VARCHAR(20), IN_CHECK BOOL DEFAULT '0', PROFILE_PIC BLOB )");
			conn.commit();
			System.out.println("PROFILES table created.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createUserApparelTable() {

		try (Connection conn = getDBConnection()) {

			System.out.println("Successfully connected in createUserApparelTable method.");

			Statement crUA = conn.createStatement();
			crUA.executeUpdate(
					"CREATE TABLE USER_APPAREL ( USERNAME VARCHAR2(15) NOT NULL REFERENCES PROFILES (USERNAME), BRAND_NAME VARCHAR2(50) NOT NULL, MODEL VARCHAR2(50) NOT NULL, CATEGORY VARCHAR2(15) NOT NULL, PRICE NUMBER(9,2) NOT NULL, BRAND_PIC BLOB, APPAREL_PIC BLOB )");
			conn.commit();
			System.out.println("USER_APPAREL table created.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createApparelTable() {

		try (Connection conn = getDBConnection()) {

			System.out.println("Successfully connected in createApparelTable method.");

			Statement crAP = conn.createStatement();
			crAP.executeUpdate(
					"CREATE TABLE APPAREL ( BRAND_NAME VARCHAR2(50) NOT NULL, MODEL VARCHAR2(50) NOT NULL, CATEGORY VARCHAR2(15) NOT NULL, PRICE NUMBER(9,2) NOT NULL, BRAND_PIC BLOB, APPAREL_PIC BLOB )");
			System.out.println("APPAREL table created.");
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void dropProfilesTable() {

		try (Connection conn = getDBConnection()) {

			Statement drPF = conn.createStatement();
			drPF.executeUpdate("DROP TABLE PROFILES");
			System.out.println("PROFILES table dropped successfully.");
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void dropApparelTable() {

		try (Connection conn = getDBConnection()) {

			Statement drAP = conn.createStatement();
			drAP.executeUpdate("DROP TABLE APPAREL");
			System.out.println("APPAREL table dropped successfully.");
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void dropUserApparelTable() {

		try (Connection conn = getDBConnection()) {

			Statement drUP = conn.createStatement();
			drUP.executeUpdate("DROP TABLE USER_APPAREL");
			System.out.println("USER_APPAREL table dropped successfully.");
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static int populateApparel(Clothes[] clothing) {

		int count = 0;

		String sql = "INSERT INTO APPAREL VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = getDBConnection()) {

			PreparedStatement plAP = conn.prepareStatement(sql);
			for (int i = 0; i < clothing.length; i++) {

				plAP.setString(1, clothing[i].getBrandName());
				plAP.setString(2, clothing[i].getModelName());
				plAP.setString(3, clothing[i].getCategory());
				plAP.setDouble(4, clothing[i].getPrice());
				File image = new File("../StyleFlexMenu/DefaultProfilePic.jpeg");
				FileInputStream fis = new FileInputStream(image);
				FileInputStream fis2 = new FileInputStream(image);
				plAP.setBinaryStream(5, fis, (int) image.length());
				plAP.setBinaryStream(6, fis2, (int) image.length());

				plAP.execute();

				count++;
			}

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public static void addUserApparel(String currUser, String category, String brand, String model, byte[] brandPic,
			byte[] modelPic, Double price) {

		String sql = "INSERT INTO USER_APPAREL VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		ByteArrayInputStream brandStream = new ByteArrayInputStream(brandPic);
		ByteArrayInputStream modelStream = new ByteArrayInputStream(modelPic);
		
		try (Connection conn = getDBConnection()) {

			PreparedStatement plAP = conn.prepareStatement(sql);

			plAP.setString(1, currUser);
			plAP.setString(2, brand);
			plAP.setString(3, model);
			plAP.setString(4, category);
			plAP.setDouble(5, price);
			plAP.setBinaryStream(6, brandStream, brandPic.length);
			plAP.setBinaryStream(7, modelStream, modelPic.length);

			plAP.execute();
			
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getDB_USER() {
		return DB_USER;
	}

	public static String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public static void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}

	public static void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
	}

	public static void createProfile(String userName, String password, String email) {

		String sql = "INSERT INTO PROFILES ( USERNAME, PASSWORD, EMAIL, PROFILE_PIC) VALUES (?,?,?,?)";

		try (Connection conn = getDBConnection()) {

			PreparedStatement crAC = conn.prepareStatement(sql);

			crAC.setString(1, userName);
			crAC.setString(2, password);
			crAC.setString(3, email);
			File image = new File("../StyleFlexMenu/DefaultProfilePic.jpeg");
			FileInputStream fis = new FileInputStream(image);
			crAC.setBinaryStream(4, fis, (int) image.length());

			crAC.execute();

			conn.commit();

		} catch (Exception e) {
			System.out.println("Account not created.");
		}

	}

	public static void updateFlexPoints(String userName) {

		Double result = 0.0;

		try (Connection conn = getDBConnection()) {

			Statement upFP = conn.createStatement();
			ResultSet rs = upFP.executeQuery("SELECT PRICE FROM USER_APPAREL WHERE USERNAME = '" + userName + "'");
			while (rs.next()) {
				result += rs.getDouble(1);
			}

			upFP.executeUpdate(
					"UPDATE FLEX_POINTS SET FLEX_POINTS = " + result + " WHERE USERNAME = '" + userName + "'");
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean checkIfUserExists(String userName) {
		boolean result = false;

		try (Connection conn = getDBConnection()) {

			Statement checkUserName = conn.createStatement();
			ResultSet rs = checkUserName
					.executeQuery("SELECT USERNAME FROM PROFILES WHERE USERNAME = '" + userName + "'");

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean checkIfEmailAvailable(String email) {
		boolean result = false;

		try (Connection conn = getDBConnection()) {

			Statement checkEmail = conn.createStatement();
			ResultSet rs = checkEmail.executeQuery("SELECT EMAIL FROM PROFILES WHERE EMAIL = '" + email + "'");

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean validateEmail(String email) {

		EmailValidator validator = EmailValidator.getInstance();

		return validator.isValid(email);
	}

	public static boolean validateLoginCredentials(String userName, String password) {
		boolean result = false;

		try (Connection conn = getDBConnection()) {

			Statement checkCreds = conn.createStatement();
			ResultSet rs = checkCreds
					.executeQuery("SELECT USERNAME, PASSWORD FROM PROFILES WHERE USERNAME = '" + userName + "'");

			while (rs.next()) {
				if (userName.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					result = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static byte[] getBrandPic(String model, Double price) {
		byte[] brandByteArray = null;
		
		try (Connection conn = getDBConnection()) {
			
			Statement getBPic = conn.createStatement();
			
			ResultSet rs = getBPic.executeQuery("SELECT BRAND_PIC FROM APPAREL WHERE MODEL = '" + model + "' AND PRICE = " + price);
			if (rs.next()) {
				Blob brandBlob = rs.getBlob(1);
				InputStream brandPic = brandBlob.getBinaryStream();
				brandByteArray = IOUtils.toByteArray(brandPic);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return brandByteArray;
	}
	
	public static byte[] getModelPic(String model, Double price) {
		byte[] modelByteArray = null;
		
		try (Connection conn = getDBConnection()) {
			
			Statement getMPic = conn.createStatement();
			
			ResultSet rs = getMPic.executeQuery("SELECT APPAREL_PIC FROM APPAREL WHERE MODEL = '" + model + "' AND PRICE = " + price);
			
			if (rs.next()) {
				Blob modelBlob = rs.getBlob(1);
				InputStream modPic = modelBlob.getBinaryStream();
				modelByteArray = IOUtils.toByteArray(modPic);
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return modelByteArray;
	}
}
