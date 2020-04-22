package tn.esprit.services;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Bond;
import interfaces.BondServiceLocal;

@Stateless 
@Path("bond")
public class BondService {
	
	@EJB
	BondServiceLocal bond_services;
	

@POST
@Path("add")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response AddBond(Bond bond) {
	int result = bond_services.addBond(bond);
	if (result !=0) {
		return Response.status(Response.Status.OK).entity(bond).build();
	}
	return Response.status(Response.Status.BAD_REQUEST).build();
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("findbonds")
public Response findBond() {
	return Response.status(Response.Status.OK).entity(bond_services.findAllBonds()).build();
}

@DELETE
@Path("delete/{id_bond}")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public Response DeleteUser(@PathParam(value="id_bond") int id_bond) {
	@SuppressWarnings("unused")
	int result =bond_services.deleteBond(id_bond);
		return Response.status(Response.Status.OK).entity("Bond is deleted").build();		
}


 

}






	


