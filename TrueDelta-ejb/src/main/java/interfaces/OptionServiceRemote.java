package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Option;
import entities.User;

@Remote
public interface OptionServiceRemote  {
	public int addOption(Option option);
	public List<Option> findAllOptions() ;
	public void removeOption(int id) ;
	public Option findOptionById(int id) ;
	public User findUserById(int id) ;
//	public double BlackAndScholes(int id);
    public  double findTaux(int id) ;
    public double loiNormale(double d);
}
