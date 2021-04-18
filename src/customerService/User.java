package customerService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
	public Connection connectForProjectManagement() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//not necessary as this way is deprecated
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/GB_Customer", "root", 
					"root");
			System.out.println("Connection successfully established with Project DB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public boolean isAuthenticated(String username, String password) {
		 boolean output = false;
		 
		 try {
			Connection con = this.connectForProjectManagement();
			
			if (con == null) {
				return false;
			}
			
			//creating prepared statement to find customer
			String query = "select username, password from User where username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				String dbPassword = rs.getString("password");
				if (dbPassword == password) {
					output = true;
				} else {
					output = false;
				}
			}
			
		} catch (Exception e) {
			output = false;
			System.err.println(e.getMessage());
		}
		 
		return output;
	}
	
	public int getUserID(String username) {
		int output = -1;
		 
		 try {
			Connection con = this.connectForProjectManagement();
			
			if (con == null) {
				return -1;
			}
			
			//creating prepared statement to find customer
			String query = "select userID from User where username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				output = rs.getInt("userID");
			} else {
				output = -1;
			}
			
		} catch (Exception e) {
			output = -1;
			System.err.println(e.getMessage());
		}
		 
		return output;
	}
}
