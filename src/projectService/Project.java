package projectService;

import customerService.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Project {
	
	User user = new User();
	
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
//	public String readProjects(String username, String password) {
//		
//	}
	
	public String createProject(String projectTitle, String projectType, 
								String projectDesc, String projectBudget, 
								String unitPrice, String username, String password) {
		
		String output = "";
		
		try {
			//connecting to project database
			Connection connection = this.connect();
			
			if (connection == null) {
				return "Error connecting to project database";
			}
			
			//check if it authenticated
			if (user.isAuthenticated(username, password)) {
				
				if (user.getUserRole(username).equalsIgnoreCase("Inventor")) {
					
					//get userID for the username
					int userID = user.getUserID(username);
					
					//creating prepared statement for insert
					String query = "insert into Project (projectTitle, projectType, projectDesc, "
							+ "projectBudget, unitPrice, inventorID) values (?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					//binding values to prepared statement
					preparedStatement.setString(1, projectTitle);
					preparedStatement.setString(2, projectType);
					preparedStatement.setString(3, projectDesc);
					preparedStatement.setDouble(4, Double.parseDouble(projectBudget));
					preparedStatement.setFloat(5, Float.parseFloat(unitPrice));
					preparedStatement.setInt(6, userID);
					
					if (preparedStatement.execute()) {
						output = "Project inserted successfully";
					}
					
				}
				
			}	
			
			connection.close();
			
		} catch (Exception e) {
			output = "Error inserting project";
			System.err.println(e.getMessage());
		}
		
		return output;
				
	}
	
//	public String updateProject(String projectID, String projectTitle, 
//								String projectType, String projectDesc, 
//								String projectBudget, String unitPrice, 
//								String username, String password) {
//
//	}
//	
//	public String deleteProject(String projectID, String username, String password) {
//
//	}
}
