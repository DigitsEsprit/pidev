package services;

import java.sql.Date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Calendar;



import java.util.Random;



import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.math3.distribution.NormalDistribution;

import entities.Bond;
import entities.Contract;
import entities.Option;
import entities.User;
import interfaces.OptionServiceLocal;
import interfaces.OptionServiceRemote;

@Stateless
public class OptionService implements OptionServiceLocal,OptionServiceRemote   {
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
	public double findTaux(int Id){
		Contract c= em.createQuery(" select a from Contract a where a.user.id_user ='"+ Id+"'", Contract.class).getSingleResult();
	double taux=c.getGain();
	return taux;
	
	}


	@Override
public User findUserById(int id) {
	User user = em.find(User.class, id);
	return user;
}
//Black and Scholes 
	public double BlackAndScholes(int id,int id2) {
		Option p=findOptionById(id2)	;
		double d1 =0;
		double d2=0;
		double y=0;
		
		//time
	int gg=p.getEnd_day().getYear()+1900;
	Calendar cal = Calendar.getInstance();
	cal.setTime(p.getStart_day());
	int b = cal.get(Calendar.MONTH)+1;
	int i=cal.get(Calendar.DAY_OF_MONTH);
	Calendar cal2 = Calendar.getInstance();
	cal2.setTime(p.getEnd_day());
	int t=cal2.get(Calendar.MONTH)+1;
	int bb=cal2.get(Calendar.DAY_OF_MONTH);
	int bbb=p.getEnd_day().getHours();
	int bbbb= p.getEnd_day().getMinutes();
		LocalDateTime newDate = LocalDateTime.of(gg, t, bb, bbb,bbbb);
	LocalDateTime newDate2 = LocalDateTime.of(p.getStart_day().getYear()+1900, b,i, p.getStart_day().getHours(), p.getStart_day().getMinutes());
	
	Duration duration=	Duration.between( newDate2,newDate );
	    
		  long diff = Math.abs(duration.toDays());	
		double x=(double)diff/365;
			//
		double  z=(double ) Math.log(p.getPrice()/p.getStrike());
		double  s=(double ) ((double ) (findTaux(id)+(p.getVolatility()*p.getVolatility())/2)*x);
		double  s2=(double ) ((double ) (findTaux(id)-(p.getVolatility()*p.getVolatility())/2)*x);
		double r=(double) ((double) p.getVolatility()*Math.sqrt((x)));
		d1=( double)(z+s)/r;
		d2=(z+s2)/r;
		System.out.println(d1);
		System.out.println(d2);
		NormalDistribution normalDistribution = new NormalDistribution();
		if(p.getType().toString().equals("european"))
		   {if(p.getTtype().toString().equals("call"))
		{
	double m=normalDistribution.cumulativeProbability(d1);
		double n=normalDistribution.cumulativeProbability(d2);
		y=(p.getPrice()*m)-(p.getStrike()*n*Math.exp(-(findTaux(id)*x)));
		
		}
		   else {
			
				double m=normalDistribution.cumulativeProbability(-d1);
				double n=normalDistribution.cumulativeProbability(-d2);
			   
			   y=(p.getStrike()*n*Math.exp(-(findTaux(id)*x)))-(p.getPrice()*m);
		   }
			
		} 
		  return y;	
		  }

	
	
	
	}
	
		
		

		  
		
	
	
	 
	
	
