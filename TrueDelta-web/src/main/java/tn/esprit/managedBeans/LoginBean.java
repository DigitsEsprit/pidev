package tn.esprit.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;

import javax.enterprise.inject.Specializes;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.github.adminfaces.template.session.AdminSession;

import entities.User;
import services.UserService;



@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private String login; 
	private String password; 
	private User user; 
	private Boolean loggedIn;
	
	
	@EJB
	UserService userService;
	
	public String doLogin() {
		
		String navigateTo = "null";
		FacesContext context = FacesContext.getCurrentInstance();
		user = userService.verifyLoginCredentials(login, password);
		if (user != null /*&& user.getRole() == Role.administrateur*/) {
	        //Messages.addGlobalInfo("Logged in successfully!");
			System.out.println("user : " + user.getEmail() + " " + user.getRole()); loggedIn = true;
			context.getExternalContext().getSessionMap().put("user", user);
			
			navigateTo = "/index?faces-redirect=true"; 
		}
		else {
		FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
		}
		return navigateTo; 
	}
	
	public String doLogout() {
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	loggedIn = false;
	return "/login.xhtml?faces-redirect=true"; 
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setEmploye(User user) {
		this.user = user;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setEmployeService(UserService userService) {
		this.userService = userService;
	}
}

