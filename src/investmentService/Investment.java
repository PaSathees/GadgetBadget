package investmentService;
import java.sql.*;

public class Investment {
    
	private Connection connect()
	 {
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//connecting with the database by giving correct username nane and pwd //
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
		}
		
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	 }
	
	public String insertInvestment(String iname, String phone, String amount, String description)
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			
			String query = "INSERT INTO `investment`(`iID`, `iname`, `phone`, `amount`, `description`) values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, iname);
			preparedStmt.setString(3, phone);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setString(5, description);
			// execute the statement
	 
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
	 }
	
	catch (Exception e)
	 {
			output = "Error while inserting the investment details.";
			System.err.println(e.getMessage());
	 }
		return output;
	}
	
	
	public String readInvestment()
	 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Investor name</th>" +
						"<th>Investor phoneNo</th>" +
						"<th>Investing amount</th>"+
						"<th>description</th>" +
						"<th>Update</th><th>Remove</th></tr>";

				String query = "SELECT `iID`, `iname`, `phone`, `amount`, `description` FROM `investment`";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
	
				// iterate through the rows in the result set
				
				while (rs.next())
				{
						String iID = Integer.toString(rs.getInt("iID"));
						String iname = rs.getString("iname");
						String phone = rs.getString("phone");
						String amount = Double.toString(rs.getDouble("amount"));
						String description = rs.getString("description");
	 
						// Add into the html table
						
						output += "<tr><td><form method='post' action='investment.jsp'><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + iID + "'>"
								+ iname + "</td>";
						output += "<td>" + phone + "</td>";
						output += "<td>" + amount + "</td>";
						output += "<td>" + description + "</td>";
	
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><form method='post' action='investment.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
								+ "<input  name='hidItemIDDelete'  type='hidden' value='" + iID
								+ "'>" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			}
			
			catch (Exception e)
			{
				output = "Error while reading the investment.";
				System.err.println(e.getMessage());
			}
	 
			return output;
	 }
	
	public String updateInvestment(String iID, String iname, String phone, String amount, String description)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
		
			 // create a prepared statement
			 String query = "UPDATE `investment` SET `iname`=?,`phone`=?,`amount`=?,`description`=? WHERE  `iID`= ? ";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		
			 // binding values
			 preparedStmt.setString(1, iname);
			 preparedStmt.setDouble(3, Double.parseDouble(amount));
			 preparedStmt.setString(2, phone);
			 preparedStmt.setString(4, description);
			 preparedStmt.setInt(5, Integer.parseInt(iID));
		
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
		
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the investment.";
			 System.err.println(e.getMessage());
		 }
		
		 return output;
	 }
	
	public String deleteInvestment(String iID)
	 {
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
		
			 // create a prepared statement
			 String query = "DELETE FROM `investment` WHERE `iID`=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(iID));
		
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
		
			 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the investment.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
} 
	
