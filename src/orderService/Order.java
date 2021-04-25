package orderService;
import java.sql.*;

public class Order {
    
	private Connection connect()
	 {
		Connection con = null;
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order", "root", "");
	    }
		
		catch (Exception e)
			{e.printStackTrace();}
				return con;
	 		}
	public String insertOrder(String address, String mobileno, String name, String transactionmethod, String email)
	 {
		String output = "";
		try
		{
			Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
					// create a prepared statement
				String query = "INSERT INTO `order`(`productID`, `address`, `mobileno`, `name`, `transactionmethod` , `email` ) values (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, address);
				preparedStmt.setString(3, mobileno);
				preparedStmt.setString(4, name);
				preparedStmt.setString(5, transactionmethod);
				preparedStmt.setString(6, email);
	// execute the statement
	 
				preparedStmt.execute();
				con.close();
					output = "Order Inserted successfully";
		}
		catch (Exception e)
			{
				output = "Error while inserting the order.";
				System.err.println(e.getMessage());
			}
		return output;
	 }
	
	public String readOrder()
	 	{
			String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Order address</th>" +
					"<th>Name</th>" +
					"<th>Mobile number</th>"+
					"<th>Transaction method</th>" +
					"<th>Email</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "SELECT `productID`, `address`, `mobileno`, `name`, `transactionmethod`, `email` FROM `order`";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
			while (rs.next())
			{
				String productID = Integer.toString(rs.getInt("productID"));
				String address = rs.getString("address");
				String name = rs.getString("name");
				String mobileno = rs.getString("mobileno");
				String transactionmethod = rs.getString("transactionmethod");
				String email = rs.getString("email");
	 
	 // Add into the html table
				output += "<tr><td><form method='post' action='order.jsp'><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + productID + "'>"
						+ address + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + mobileno + "</td>";
				output += "<td>" + transactionmethod + "</td>";
				output += "<td>" + email + "</td>";
	 // buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><form method='post' action='order.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input  name='hidItemIDDelete'  type='hidden' value='" + productID
						+ "'>" + "</form></td></tr>";
			}
			con.close();
	 // Complete the html table
			output += "</table>";
		}
			catch (Exception e)
			{
				output = "Error while reading the orders.";
				System.err.println(e.getMessage());
			}
			return output;
	 	}
	public String updateOrder(String productID, String address, String mobileno, String name, String transactionmethod, String email)
		{
		 	
			String output = "";
		 	try
		 	{
		 		Connection con = connect();
		 		if (con == null)
		 		{return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 		String query = "UPDATE `order` SET `address`=?,`mobileno`=?,`name`=?,`transactionmethod`=?, `email`=? WHERE  `productID`= ? ";
		 		PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 		preparedStmt.setString(1, address);
		 		preparedStmt.setString(3, name);
		 		preparedStmt.setString(2, mobileno);
		 		preparedStmt.setString(4, transactionmethod);
		 		preparedStmt.setString(5, email);
		 		preparedStmt.setInt(6, Integer.parseInt(productID));
		 		
		 // execute the statement
		 		preparedStmt.execute();
		 		con.close();
		 		output = "Order Updated successfully";
		 	}
		 	catch (Exception e)
		 	{
		 		output = "Error while updating the order.";
		 		System.err.println(e.getMessage());
		 	}
		 		return output;
		 }
	public String deleteOrder(String productID)
		 {
		 	String output = "";
		 	try
		 		{
		 			Connection con = connect();
		 			if (con == null)
		 			{return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 			String query = "DELETE FROM `order` WHERE `productID`=?";
		 			PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 			preparedStmt.setInt(1, Integer.parseInt(productID));
		 // execute the statement
		 			preparedStmt.execute();
		 			con.close();
		 			output = "Order Deleted successfully";
		 		}
		 	catch (Exception e)
		 {
		 		output = "Error while deleting the order.";
		 		System.err.println(e.getMessage());
		 }
		 	return output;
		 }
} 
	  
