package services;

import java.util.List;
import java.util.Random;



import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Bond;
import entities.Contract;
import entities.Option;
import entities.User;
import interfaces.OptionServiceLocal;
import interfaces.OptionServiceRemote;

@Stateless
public class OptionService implements OptionServiceLocal,OptionServiceRemote {
	@PersistenceContext(unitName = "TrueDelta-ejb")
EntityManager em;

	@Override
	public int addOption(Option option) {
		
		em.persist( option);
		System.out.println("Out of addUser" + option.getId_Option());
		return option.getId_Option();

	}

	@Override
	public List<Option> findAllOptions() {
		System.out.println("In findOptions : ");
		List<Option> options = em.createQuery(" SELECT o from Option o", Option.class).getResultList();
		System.out.println("Out of findAllUsers : ");
		return options;

	}

	@Override
	public void removeOption(int id) {
		em.remove(em.find(Option.class, id));
		}
	

	



	@Override
	public Option findOptionById(int id) {
		Option option = em.find(Option.class, id);
		return option;
	}

 @Override
	public double findTaux(int id){
		
	//User user=findUserById(id);
		Contract c = em.createQuery(" select a from Contract a where a.user.id_user = 1", Contract.class).getSingleResult();
		//query.setParameter("user", id);
		//Contract c = query.getSingleResult();
		double taux=c.getGain();
		return taux;
	
	}


	@Override
public User findUserById(int id) {
	User user = em.find(User.class, id);
	return user;
}
//Loi Normale
	@Override
	public double loiNormale(double d) {
		float z=(float) (1/(Math.sqrt(2*Math.PI)));
		float r=(float) Math.exp(-(d*d)/2);
		float f=z*r;
	//	Math.pow(a, b);  
		return f;
	}
	

}
