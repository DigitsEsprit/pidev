package tn.esprit.services;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Bond;
import entities.User;
import interfaces.BondServiceLocal;

@Path("Bond")
@RequestScoped
@Stateful
public class BondService {
	
	@EJB
	BondServiceLocal metier;
	
	@GET
	@Path("allBond")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response getBond() {
		
		//return Response.ok(metier.findAllBonds()).build();
		return Response.status(Response.Status.OK).entity(metier.findAllBonds()).build();
	}
	
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AddBond(Bond bond) {
		System.out.println("test" );
		int result = metier.addBond(bond);
		//int result=1;
		if (result !=0) {
			return Response.status(Response.Status.OK).entity(bond).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	

}
