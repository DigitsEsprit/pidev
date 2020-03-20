package services_locals;

import javax.ejb.Local;
import java.util.List;


import entities.User;


@Local
public interface IUserServiceLocal {
	public int addUser(User user);
	public void deleteUser(int Id_user);
	public String searchUser(String first_name);
	public void updateUser(int id_user,String email,int phone_number,String password,String adresse,int rib,String bourse);
	public User verifyLoginCredentials(String email, String password);
	public List<User> getAllInvestors() ;
	public List<User> getAllAM();
	public List<User> getAllBrokers();
	public User authenticationBrokers(String email, String password);
	public User authenticationAM(String email, String password);
	public User authenticationInverstors(String email, String password);
	public boolean isValid(String email);
}
