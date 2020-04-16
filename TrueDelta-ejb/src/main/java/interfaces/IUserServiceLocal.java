package interfaces;


import javax.ejb.Local;


import java.util.List;


import entities.User;

@Local
public interface IUserServiceLocal {
	public int addUser(User user);
	public int deleteUser(int id_user);
	public String searchUser(String first_name);
	//public void updateUser(int id_user,String email,int phone_number,String password,String adresse,int rib,String bourse);
	public User verifyLoginCredentials(String email, String password);
	public List<User> getAllInvestors() ;
	public List<User> getAllAM();
	public List<User> getAllBrokers();
	public User authenticationBrokers(String email, String password);
	public User authenticationAM(String email, String password);
	public User authenticationInverstors(String email, String password);
	public boolean isValid(String email);
	//mdp oublié
	public void sendMailFP(String email);
	//adresseip location
	public void sendMailSecurity(String email);
	public int updateUser(User user,int id_user);
	public User getUserByName(String email);
	
}
