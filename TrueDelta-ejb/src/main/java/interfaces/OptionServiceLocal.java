package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Option;
import entities.User;

@Local
public interface OptionServiceLocal {
	public int addOption(Option option);
	public List<Option> findAllOptions() ;
	public void removeOption(int id) ;
	public User findUserById(int id) ;
	public  double findTaux(int id) ;
	//public double BlackAndScholes(int id);

}
