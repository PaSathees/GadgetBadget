package customerService;
import java.sql.*;
public class Review {
	//A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/reviewgb", "root", "");
		} catch (Exception e)
		 {e.printStackTrace();
		}
		
		return con;
	 }
	
	/*Insert Review*/
	public String insertReview(String rname, String desc, String rating)
	 {
		
			String output = "";
			 try{
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 // create a prepared statement
				 String query = " insert into review (`rid`,`rname`,`desc`,`rating`)"
				 + " values (?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, rname);
					 preparedStmt.setString(3, desc);
					 preparedStmt.setDouble(4, Double.parseDouble(rating));
				
				 // execute the statement
				 
				 preparedStmt.execute();
				 con.close();
				 output = "Review Inserted successfully";
				 
			 }catch (Exception e) {
				 output = "Error while inserting the review.";
				 System.err.println(e.getMessage());
				 }
			 return output;
	 }
	/*Read Reviews */
	public String readReviews()
	 {
	 String output = "";
	 try
		 {
		 Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>RName</th><th>Description</th>" +
			 "<th>Rating</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
		
			 String query = "select * from review";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
		 while (rs.next()) {
				 
			 String rid = Integer.toString(rs.getInt("rid"));
			 String rname = rs.getString("rname");
			 String desc = rs.getString("desc");
			 String rating = Double.toString(rs.getDouble("rating"));
			
			 // Add into the html table
			 output += "<tr><td>" +rname + "</td>";
			 output += "<td>" + desc + "</td>";
			 output += "<td>" + rating + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='Review.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			 + "<input name='rid' type='hidden' value='" + rid
			 + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 } catch (Exception e) {
			 output = "Error while reading the reviews.";
			 System.err.println(e.getMessage());
		
		 }
	 	return output;
	 }
	
	/*update Review*/
	public String updateReview(String rid, String rname, String desc, String rating) {
	
		 String output = "";
		 try {
		 Connection con = connect();
			 if (con == null) {
				 return "Error while connecting to the database for updating.";
				 }
			 // create a prepared statement
				 String query = "UPDATE `review` SET `rname`=?,`desc`=?,`rating`=? WHERE `rid`=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, rname);
				 preparedStmt.setString(2, desc);
				 preparedStmt.setDouble(3, Double.parseDouble(rating));
				 preparedStmt.setInt(4, Integer.parseInt(rid));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Review Updated successfully";
			 }
			 catch (Exception e)
				 {
				 output = "Error while updating the review.";
				 System.err.println(e.getMessage());
				 }
		 return output;
		 }
	
			/*Delete Review*/
		public String deleteReview(String rid) {
			
		 String output = "";
		 try{
			 Connection con = connect();
			 if (con == null) {
				 return "Error while connecting to the database for deleting."; 
				 }
				 // create a prepared statement
				 String query = "delete from review where rid=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(rid));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Review Deleted successfully";
			 }
		 catch (Exception e)
			 {
			 output = "Error while deleting the review.";
			 System.err.println(e.getMessage());
			 }
		return output;
		 } 
	
}
