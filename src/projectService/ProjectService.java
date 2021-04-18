package projectService;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

@Path("Projects")
public class ProjectService {
	
	Project project = new Project();
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String readProject(String projectData) {
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
		
		String projectID = projectObject.get("ProjectID").getAsString();
		
		String output = project.readProject(projectID);
		
		return output;
	}
	
	@GET
	@Path("/Inventor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String readProjects(String InventorData) {
		JsonObject invetorObject = new JsonParser().parse(InventorData).getAsJsonObject();
		
		String username = invetorObject.get("Username").getAsString();
		String password = invetorObject.get("Password").getAsString();
		
		String output = project.readProjects(username, password);
		
		return output;
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(String projectData) {
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
		
		String projectTitle = projectObject.get("ProjectTitle").getAsString();
		String projectType = projectObject.get("ProjectType").getAsString();
		String projectDesc = projectObject.get("ProjectDesc").getAsString();
		String projectBudget = projectObject.get("ProjectBudget").getAsString();
		String unitCost = projectObject.get("UnitCost").getAsString();
		String username = projectObject.get("Username").getAsString();
		String password = projectObject.get("Password").getAsString();
		
		String output = project.createProject(projectTitle, projectType, projectDesc, 
				projectBudget, unitCost, username, password);
		
		return output;		
	}

}
