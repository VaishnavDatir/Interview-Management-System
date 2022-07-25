package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
	private Connection con;

	public Connection getCon() {
		return con;
	}

	public SqlConnection() {
		makeConnection();
	}

	private void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims", "root",
					"password");

//			System.out.println("Connection successfull");
		} catch (Exception e) {
			System.out.println("Connection unsuccessfull due to: " + e.toString());
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			System.out.println("error at closing: " + e.toString());
		}
	}
}