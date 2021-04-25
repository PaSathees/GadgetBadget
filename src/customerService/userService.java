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

@Path("/User")
public class userService {
	
	User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser(){
		
		return userObj.readUser(); 
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("name") String name,
							 @FormParam("email") String email,
							 @FormParam("tel") String tel,
							 @FormParam("uname") String uname,
						     @FormParam("pwd") String pwd){
		
			String output = userObj.insertUser(name,email,tel,uname,pwd);
			return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateUser(String userData){
	//Convert the input string to a JSON object
			 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
			//Read the values from the JSON object
			 String cid = userObject.get("cid").getAsString();
			 String name = userObject.get("name").getAsString();
			 String email = userObject.get("email").getAsString();
			 String tel =userObject.get("tel").getAsString();
			 String uname =userObject.get("uname").getAsString();
			 String pwd =userObject.get("pwd").getAsString();
			 
			 String output = userObj.updateUser(cid,name,email,tel,uname,pwd);
			 
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteUser(String userData)
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		
	//Read the value from the element <itemID>
		 String cid = userObject.get("cid").getAsString();
		 String output = userObj.deleteUser(cid);
		 
		 return output;
	}

	


}
