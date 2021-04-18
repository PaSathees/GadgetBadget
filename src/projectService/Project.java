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
//		String output = "";
//		
//		try {
//			//connecting to project database
//			Connection connection = this.connect();
//			
//			if (connection == null) {
//				return "Error connecting to project database";
//			}
//			
//			//get userID for the username
//			
//			
//			//creating prepared statement for insert
//			String query = "insert into Project (projectTitle, projectType, projectDesc, "
//					+ "projectBudget, unitPrice, inventorID) values (?, ?, ?, ?, ?, ?)";
//			
//		} catch (Exception e) {
//			output = "Error inserting project";
//			System.err.println(e.getMessage());
//		}
//		
//		return output;
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
