package projectService;

import java.sql.Connection;
import java.sql.DriverManager;

public class Project {
	public Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//not necessary as this way is deprecated
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/GB_Project", "root", 
					"root");
			System.out.println("Connection successfully established with Project DB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
//	public String readProject(String projectID) {
//		
//	}
//	
//	public String readProjects(String username) {
//		
//	}
//	
//	public String createProject(String projectTitle, String projectType, 
//								String projectDesc, String projectBudget, 
//								String unitPrice, String username) {
//				
//	}
//	
//	public String updateProject(String projectID, String projectTitle, 
//								String projectType, String projectDesc, 
//								String projectBudget, String unitPrice, String username) {
//
//	}
//	
//	public String deleteProject(String projectID, String username) {
//
//	}
}
