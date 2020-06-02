package tn.esprit.services;


import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Complain;
import services.ComplainService;

@Path("complain")
@RequestScoped
public class ComplainServiceWS {
	@EJB
	ComplainService complain;
	
	private final String status = "{\"status\":\"ok\"}";
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allcomp")
	public Response getComplaints() {
		return Response.ok(complain.GetAllComplaint()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("complaintsbystate/{state}")
	public List<Complain> GetComplaintsByState(@PathParam("state") String state) {
		return complain.GetComplaintsByState(state);
	}

	@DELETE
	@Path("delete/{id_Reclamation}")
	public Response deleteComplain(@PathParam("id_Reclamation") int id_Reclamation) {
		complain.deleteComplain(id_Reclamation);
		return Response.status(200).entity("complain is deleted").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("searchcomplaint/{motcle}")
	public List<Complain> SearchComplaint(@PathParam("motcle") String motcle) {
		return complain.SearchComplain(motcle);
	}
	
	 
}
