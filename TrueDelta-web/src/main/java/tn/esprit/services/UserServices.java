package tn.esprit.services;




import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.User;
import interfaces.IUserServiceLocal;
import tn.esprit.sec.JWTTokenNeeded;
import tn.esprit.sec.LoginToken;
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
	int result = user_services.addUser(user);
	if (result !=0) {
		return Response.status(Response.Status.OK).entity(user).build();
	}
	return Response.status(Response.Status.BAD_REQUEST).build();
}
@POST
@Path("update")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response UpdateUser(User user) {
	int result = user_services.updateUser(user,user.getId_user());
	if (result !=0) {
		return Response.status(Response.Status.OK).entity(user).build();
	}	
	return Response.status(Response.Status.BAD_REQUEST).build();
}
@DELETE
@Path("delete/{id_user}")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public Response DeleteUser(@PathParam(value="id_user") int id_user) {
	@SuppressWarnings("unused")
	int result =user_services.deleteUser(id_user);
		return Response.status(Response.Status.OK).entity("User is deleted").build();		
}
@GET
@Path("investors")
@JWTTokenNeeded
@Produces(MediaType.APPLICATION_JSON)
public Response GetAllInvestors(){
	return Response.status(Response.Status.OK).entity(user_services.getAllInvestors()).build();
}
@GET
@Path("brokers")
@JWTTokenNeeded
@Produces(MediaType.APPLICATION_JSON)
public Response GetAllBrokers(){
	return Response.status(Response.Status.OK).entity(user_services.getAllBrokers()).build();
}
@GET
@Path("am")
@JWTTokenNeeded
@Produces(MediaType.APPLICATION_JSON)
public Response GetAllAM(){
	return Response.status(Response.Status.OK).entity(user_services.getAllAM()).build();
}
@GET
@Path("mailFP")
//@Produces(MediaType.APPLICATION_JSON)
public Response sendMail(@QueryParam(value = "email") String email){
	user_services.sendMailFP(email);	
	return Response.status(Response.Status.OK).build();
}
@GET
@Path("mailsec")
//@Produces(MediaType.APPLICATION_JSON)
public Response sendMailsec(@QueryParam(value = "email") String email){
	user_services.sendMailSecurity(email);;	
	return Response.status(Response.Status.OK).build();
}
@POST
@Path("/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response doLogin(User user) {
	User us = user_services.verifyLoginCredentials(user.getEmail(), user.getPassword());
	if(us == null)
		return Response.status(Response.Status.BAD_REQUEST).build();
	if(!us.isValid())
		return Response.ok("please verify your Email First!").build();
	
	
	String token = LoginToken.createJWT("TrueDelta", user.getFirst_name(), 0);
	us.setVerifToken(token);
	user_services.updateUser(us, us.getId_user());
	return Response.ok(us).header("AUTHORIZATION", "Bearer " + token).build();	
}
}
