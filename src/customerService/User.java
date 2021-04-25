package customerService;
import java.sql.*;

public class User {
	public Connection connectForProjectManagement() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//not necessary as this way is deprecated
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/GB_Customer", "root", 
					"root");
			System.out.println("Connection successfully established with User DB");
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
				if (dbPassword.equals(password)) {
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
	
	public String getUserRole(String username) {
		String output = null;
		 
		 try {
			Connection con = this.connectForProjectManagement();
			
			if (con == null) {
				return null;
			}
			
			//creating prepared statement to find customer
			String query = "select role from User where username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				output = rs.getString("role");
			} else {
				output = null;
			}
			
		} catch (Exception e) {
			output = null;
			System.err.println(e.getMessage());
		}
		 
		return output;
	}
	
	
	//A common method to connect to the DB
	private Connection connect() {
		 
			Connection con = null;
		 try {
		 
			 Class.forName("com.mysql.jdbc.Driver");
	
			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reviewgb", "root", "");
			 }
			catch (Exception e)
			 	{e.printStackTrace();}
			 return con;
			 }
	/*Insert user*/
	public String insertUser(String name, String email, String tel,String uname,String pwd) {
		 
			String output = "";
		 try
		 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 // create a prepared statement
				 String query = " insert into user (`cid`,`name`,`email`,`tel`,`uname`,`pwd`)"
				 + " values (?, ?, ?, ?,?,?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, email);
				 preparedStmt.setDouble(4, Double.parseDouble(tel));
				 preparedStmt.setString(5, uname);
				 preparedStmt.setString(6, pwd);
				
				
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 output = "User Inserted successfully ";
				 }
		 catch (Exception e) {
			 output = "Error while inserting the user.";
			 System.err.println(e.getMessage());
			 }
		 return output;
		 }
		
		/*read User*/
		public String readUser(){
		 
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Name</th><th>Email</th>" +
			 "<th>Telphone</th><th>UserName</th><th>Password</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
	
			 String query = "select * from user";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
		 while (rs.next()) {
		 
				 String cid = Integer.toString(rs.getInt("cid"));
				 String name = rs.getString("name");
				 String email = rs.getString("email");
				 String uname = rs.getString("uname");
				 String pwd = rs.getString("pwd");
				 String tel = Double.toString(rs.getDouble("tel"));
				
				 // Add into the html table
				 output += "<tr><td>" +name + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + uname + "</td>";
				 output += "<td>" + pwd + "</td>";
				 output += "<td>" + tel + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='User.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='cid' type='hidden' value='" + cid
				 + "'>" + "</form></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
		 }
		 catch (Exception e) {
			 output = "Error while reading the users.";
			 System.err.println(e.getMessage());
			 }
		
		 return output;
		 }
		
		/*update User*/
		public String updateUser(String cid, String name, String email,String tel, String uname,String pwd)
		{
			 String output = "";
			 try {
			 
			 Connection con = connect();
			 if (con == null){
				 return "Error while connecting to the database for updating.";
				 }
				 // create a prepared statement
				 String query = "UPDATE `user` SET `name`=?,`email`=?,`tel`=?,`uname`=?,`pwd`=? WHERE `cid`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, name);
				 preparedStmt.setString(2, email);
				 preparedStmt.setDouble(3, Double.parseDouble(tel));
				 preparedStmt.setString(4, uname);
				 preparedStmt.setString(5, pwd);
				 preparedStmt.setInt(6, Integer.parseInt(cid));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "User Updated successfully";
				 }
			 catch (Exception e) {
				 output = "Error while updating the user.";
				 System.err.println(e.getMessage());
			 }
		 return output;
		 }
		
		/*Delete User*/
		public String deleteUser(String cid) {
			 String output = "";
			try{
			  Connection con = connect();
			  if (con == null){
				  return "Error while connecting to the database for deleting."; 
				  }
					 // create a prepared statement
					 String query = "delete from user where cid=?";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(cid));
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 output = "User Deleted successfully";
			 }
			 catch (Exception e) {
				 output = "Error while deleting the user.";
				 System.err.println(e.getMessage());

						 }
			 return output;
			 } 
		
	
	
	
}
