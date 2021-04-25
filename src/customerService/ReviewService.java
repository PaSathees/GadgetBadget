package customerService;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Review")
public class ReviewService {

	Review reviewObj = new Review();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readReviews(){
	 
	  return reviewObj.readReviews(); 
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("rname") String rname,
							@FormParam("desc") String desc,
							@FormParam("rating") String rating){
		String output = reviewObj.insertReview(rname,desc,rating);
		
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateReview(String reviewData)
	{
	//Convert the input string to a JSON object
			JsonObject reviewObject = new JsonParser().parse(reviewData).getAsJsonObject();
		//Read the values from the JSON object
			 String rid = reviewObject.get("rid").getAsString();
			 String rname = reviewObject.get("rname").getAsString();
			 String desc = reviewObject.get("desc").getAsString();
			 String rating =reviewObject.get("rating").getAsString();
			 
			 String output = reviewObj.updateReview(rid,rname,desc,rating);
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteReview(String reviewData)
	{
			//Convert the input string to a JSON object
		JsonObject reviewObject = new JsonParser().parse(reviewData).getAsJsonObject();
		
		 //Read the value from the element <itemID>
		 	String rid = reviewObject.get("rid").getAsString();
		 	String output = reviewObj.deleteReview(rid);
		 	
		 	return output;
	}

	
}



