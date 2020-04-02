package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import entities.Role;
import entities.User;
import interfaces.IUserServiceLocal;


@Stateless
public class UserService implements IUserServiceLocal {
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;

	@Override
	public User verifyLoginCredentials(String email, String password) {
	System.out.println("from ejb : "+email + " "+password);
	Query query = em.createQuery("select u from User u where u.email=:email AND u.password=:password").setParameter("email",email).setParameter("password", password);
	if(!query.getResultList().isEmpty()) {
		User user = (User) query.getResultList().get(0);
		System.out.println("from ejb, user found, authenticating user with id :"+user.getId_user());
		return user;
	}
	System.out.println("user not found...");
	return null;}
    @Override	
    public int addUser(User user) {
    	System.out.println("adding:"+user);
		em.persist(user);
		return user.getId_user();
	}
	@Override
	public int deleteUser(int id_user) {
		User user = em.find(User.class,id_user);
		if (user !=null) {
			em.remove(user);
		}
		return 1;
		}
	@Override
	public String searchUser(String first_name) {
		User user = em.createQuery("select u from User where u.first_name=first_name", User.class).getSingleResult();
		return user.getFirst_name();
	}
			 	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllInvestors() {
		Query query = em.createQuery("Select u from User u where u.role=:role",User.class);		
		query.setParameter("role", Role.investor);
		return (List<User>)query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllAM() {
		Query query = em.createQuery("Select u from User u where u.role=:role",User.class);		
		query.setParameter("role", Role.asset_manager);
		return (List<User>)query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllBrokers() {
		Query query = em.createQuery("Select u from User u where u.role=:role",User.class);		
		query.setParameter("role", Role.broker);
		return (List<User>)query.getResultList();
	}
	@SuppressWarnings("unused")
	@Override
	public User authenticationBrokers(String email, String password) {
		List<User> brokers = getAllBrokers();
		List<String> namebro = new ArrayList<String>();
		List<User> bro = new ArrayList<User>();
		for (int i = 0; i < brokers.size(); i++) {
			if(brokers.get(i).getEmail().equals(email)&&brokers.get(i).getPassword().equals(password)) {
				String FirstName = brokers.get(i).getFirst_name();
				String LastName = brokers.get(i).getLast_name();
                bro.add(brokers.get(i));
				namebro.add(FirstName);
				namebro.add(LastName);
			}return brokers.get(i);
			}
		return null;}
	@SuppressWarnings("unused")
	@Override
	public User authenticationAM(String email, String password) {
		List<User> am = getAllAM();
		List<String> nameam = new ArrayList<String>();
		List<User> assetm = new ArrayList<User>();
		for (int i = 0; i < am.size(); i++) {
			if(am.get(i).getEmail().equals(email)&&am.get(i).getPassword().equals(password)) {
				String FirstName = am.get(i).getFirst_name();
				String LastName = am.get(i).getLast_name();
                assetm.add(am.get(i));
				nameam.add(FirstName);
				nameam.add(LastName);
			}return am.get(i);
			}
		return null;
	}
	@SuppressWarnings("unused")
	@Override
	public User authenticationInverstors(String email, String password) {
		List<User> investors = getAllInvestors();
		List<String> nameinv = new ArrayList<String>();
		List<User> inv = new ArrayList<User>();
		for (int i = 0; i < investors.size(); i++) {
			if(investors.get(i).getEmail().equals(email)&&investors.get(i).getPassword().equals(password)) {
				String FirstName = investors.get(i).getFirst_name();
				String LastName = investors.get(i).getLast_name();
				inv.add(investors.get(i));
                nameinv.add(FirstName);
                nameinv.add(LastName);
			}return investors.get(i);
			}
		return null;
	}
	@Override
	public boolean isValid(String email) {
		//Query query = em.createQuery("select u from User u where u.email = :email").setParameter("email",email);
		System.out.println("enter your e-mail:");
		if (email!=null && email.trim().length()>0) {
			return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
			
		}else 
			System.out.println("incorrect e-mail");
		
		return false;
	}
	@Override
	public void sendMailFP(String email) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    	@Override
	    	   protected PasswordAuthentication getPasswordAuthentication() {
	    	    return new PasswordAuthentication("mohamedwejih.sbai@esprit.tn", "WEJIHSBAI96");
	    	   }
	    });

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        
	        msg.setFrom(new InternetAddress("mohamedwejih.sbai@esprit.tn"));
	        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
	        msg.setSubject("Forgetten password");
	        msg.setSentDate(new Date());
	        msg.setText("Change your password here: http://localhost:9080/TrueDelta-web/truedelta/authentication?token=xxxxxxxxxxxxxxxxxxxxx&email=imencherifw@gmail.com");
	        Transport.send(msg);
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
		
	}
	@Override
	public void sendMailSecurity(String email) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    	@Override
	    	   protected PasswordAuthentication getPasswordAuthentication() {
	    	    return new PasswordAuthentication("mohamedwejih.sbai@esprit.tn", "WEJIHSBAI96");
	    	   }
	    });

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        
	        msg.setFrom(new InternetAddress("mohamedwejih.sbai@esprit.tn"));
	        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
	        msg.setSubject("Secure your account");
	        msg.setSentDate(new Date());
	        msg.setText("There is an unknown connexion with this IPAdress and Location");
	        Transport.send(msg);
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
		
	}	
}
	@Override
	public int updateUser(User user, int id_user) {
		Query query =  em.createQuery("update User u set email=:email , phone_number=:phone_number , password=:password , adresse=:adresse , rib=:rib , bourse=:bourse where u.id_user=:id_user ");
		query.setParameter("email", user.getEmail());
		query.setParameter("phone_number", user.getPhone_number());
		query.setParameter("password", user.getPassword());
		query.setParameter("adresse", user.getAdresse());
		query.setParameter("rib", user.getRib());
		query.setParameter("bourse", user.getBourse());
		query.setParameter("id_user", id_user);
		int m=query.executeUpdate();
	   return m;
	    }
	@Override
	public User getUserByName(String email) {
		return (User) em.createQuery("select u from User u where u.email=:email").setParameter("email", email).getResultList().get(0);
	}
	
	
}	
	
	
	

