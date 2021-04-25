package investmentService;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;


@Path("/Investment")
public class InvestmentService {
	
	Investment InvestmentObj = new Investment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInvestment()
	 {
		return InvestmentObj.readInvestment(); 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInvestment(@FormParam("iname") String iname,@FormParam("date") String date,@FormParam("amount") String amount,@FormParam("description") String description) {
		
		String output = InvestmentObj.insertInvestment(iname, date, amount, description);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(String InvestmentData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(InvestmentData).getAsJsonObject();
	//Read the values from the JSON object
	
	 String iID = Object.get("iID").getAsString();
	 String iname = Object.get("iname").getAsString();
	 String date = Object.get("date").getAsString();
	 String amount = Object.get("amount").getAsString();
	 String description = Object.get("description").getAsString();
	
	 String output = InvestmentObj.updateInvestment(iID, iname, date, amount, description);
	
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInvestment(String InvestmentData)
	{
	//Convert the input string to a JSON object
	JsonObject Object = new JsonParser().parse(InvestmentData).getAsJsonObject();

	//Read the values from the JSON object
	 String iID =Object.get("iID").getAsString();
	 String output = InvestmentObj.deleteInvestment(iID);
	return output;
	}
	
	
}
