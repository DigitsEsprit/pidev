package tn.esprit.rest.utilities;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


import interfaces.IUserServiceLocal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Path("authentication")
public class AuthenticationEndPoint{
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	@Context
	private UriInfo uriInfo;
	
	@EJB
	IUserServiceLocal userservice;
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		try {
		// Authenticate the user using the credentials provided
		int found = authenticate(username, password);
		if(found == 1) {
			// Issue a token for the user
			Token token = issueToken(username);
			
			
			// Return the token on the response
			return Response.ok(token).build();
		}
		return Response.status(Status.UNAUTHORIZED).build();

	} catch (Exception e) {
		e.printStackTrace();
		return Response.status(Response.Status.FORBIDDEN).build();
	}
		
	}
		
	private int authenticate(String username, String password) {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
		System.out.println("From WS Authenticating user...");
		if(userservice.verifyLoginCredentials(username, password) != null) {
						return 1;
		}
		System.out.println("Auth failed...");
		return 0;

	}

	private Token issueToken(String first_name) {
		// Issue a token (can be a random String persisted to a database or a JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("the key is : " + key.hashCode());

		System.out.println("uriInfo.getAbsolutePath().toString() : " + uriInfo.getAbsolutePath().toString());
		System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(120L)));

		String jwtToken = Jwts.builder().setSubject(first_name).setIssuer(uriInfo.getAbsolutePath().toString())
				.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(120L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();
		Token result = new Token(jwtToken, toDate(LocalDateTime.now().plusMinutes(120L)), userservice.getUserByName(first_name));
		System.out.println("the returned token is : " + jwtToken);
		System.out.println("the fully updated token is "+result);
		
		return result;
	}

	// ======================================
	// = Private methods =
	// ======================================

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}		
		
		

}