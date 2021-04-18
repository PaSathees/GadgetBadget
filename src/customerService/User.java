package customerService;

import java.sql.Connection;
import java.sql.DriverManager;

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
	
//	public boolean isAuthenticated(String username) {
//		
//	}
//	
//	public String getUserID(String username) {
//		
//	}
}
