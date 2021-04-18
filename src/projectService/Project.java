package projectService;

import customerService.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public String readProject(String projectID) {
		String output = "";
		
		try {
			//connecting to project database
			Connection connection = this.connect();
			
			if (connection == null) {
				return "Error connecting to project database";
			}
								
			//creating prepared statement for reading
			String query = "select * from Project where projectID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			//binding values to prepared statement
			preparedStatement.setInt(1, Integer.parseInt(projectID));
			ResultSet rs = preparedStatement.executeQuery();			
			
			if (rs.next()) {
				String dbProjectID = Integer.toString(rs.getInt("projectID"));
				String projectTitle = rs.getString("projectTitle");
				String projectType = rs.getString("projectType");
				String projectDesc = rs.getString("projectDesc");
				String projectBudget = Double.toString(rs.getDouble("projectBudget"));
				String unitCost = Integer.toString(rs.getInt("unitCost"));
				
				
				output += "{\"ProjectID\":\"" + dbProjectID + "\", \"ProjectTitle\":\"" + projectTitle +"\", "
						+ "\"ProjectType\":\"" + projectType + "\", \"ProjectDesc\":\"" + projectDesc + "\", "
						+ "\"ProjectBudget\":\"" + projectBudget + "\", \"UnitCost\":\"" + unitCost + "\"}";
			}			
				
			connection.close();
			
		} catch (Exception e) {
			output = "Error reading project";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
//	
//	public String readProjects(String username, String password) {
//		
//	}
	
	public String createProject(String projectTitle, String projectType, 
								String projectDesc, String projectBudget, 
								String unitCost, String username, String password) {
		
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
							+ "projectBudget, unitCost, inventorID) values (?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					
					//binding values to prepared statement
					preparedStatement.setString(1, projectTitle);
					preparedStatement.setString(2, projectType);
					preparedStatement.setString(3, projectDesc);
					preparedStatement.setDouble(4, Double.parseDouble(projectBudget));
					preparedStatement.setFloat(5, Float.parseFloat(unitCost));
					preparedStatement.setInt(6, userID);
					
					
					
					preparedStatement.execute();
					output = "Project inserted successfully";					
					
				} else {
					output = "You are not authorized to insert Project";
				}
				
			} else {
				output = "you are not authenticated";
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
