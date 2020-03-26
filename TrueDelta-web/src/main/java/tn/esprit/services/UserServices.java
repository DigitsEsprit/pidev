package tn.esprit.services;


import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.User;
import interfaces.IUserServiceLocal;
@Stateless 
@Path("user")
public class UserServices {
@Any	
@Inject

private IUserServiceLocal user_services;

@POST
@Path("add")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response AddUser(User user) {
	System.out.println("test" );
	int result = user_services.addUser(user);
	//int result=1;
	if (result !=0) {
		return Response.status(Response.Status.OK).entity(user).build();
	}
	return Response.status(Response.Status.BAD_REQUEST).build();
}
}
