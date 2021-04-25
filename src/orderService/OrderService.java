package orderService;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;


@Path("/Order")
public class OrderService {
	
	Order orderObj = new Order();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrder()
	 {
		return orderObj.readOrder(); 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("address") String address,@FormParam("mobileno") String mobileno,@FormParam("name") String name,@FormParam("transactionmethod") String transactionmethod,@FormParam("email") String email) {
		
		String output = orderObj.insertOrder(address, mobileno, name, transactionmethod, email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(String orderData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(orderData).getAsJsonObject();
	//Read the values from the JSON object
	
	 String productID = Object.get("productID").getAsString();
	 String address = Object.get("address").getAsString();
	 String mobileno = Object.get("mobileno").getAsString();
	 String name = Object.get("name").getAsString();
	 String transactionmethod = Object.get("transactionmethod").getAsString();
	 String email = Object.get("email").getAsString();
	
	 String output = orderObj.updateOrder(productID, address, mobileno, name, transactionmethod, email);
	
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrder(String orderData)
	{
	//Convert the input string to a JSON object
	JsonObject Object = new JsonParser().parse(orderData).getAsJsonObject();

	//Read the values from the JSON object
	 String productID =Object.get("productID").getAsString();
	 String output = orderObj.deleteOrder(productID);
	return output;
	}
	
	
}
