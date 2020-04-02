package services;

import java.awt.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Bond;
import entities.BondType;
import entities.MarketType;
import interfaces.BondServiceLocal;
import interfaces.BondServiceRemote;

@Stateful
public class BondService implements BondServiceLocal, BondServiceRemote {
	
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	@Override
	public int addBond(Bond bond) {		 
		em.persist(bond);
		return bond.getId_bond();
	}

	@Override
	public void deleteBond(int id) {		
		em.remove(em.find(Bond.class, id));	
	}
	
	@Override
	public double CoupnCalcul(Bond bond) {		
		double cc,c;
		c=(bond.getNominal_value()*bond.getNominal_rate()*(bond.getDays_next_coupn()+3))/356;
		cc=(c/bond.getNominal_value())*100;
		return cc;
	}

	@Override
	public double actualRateOfReturnBond(Bond bond, double coursBoursier) {
		
		//double cours_boursier = 10 ;
		double rendement_coupon, actualReturn=0,issuePrice=0;
		issuePrice=(bond.getIssue_price()*100)/bond.getNominal_value();
		
		rendement_coupon= (CoupnCalcul(bond)/coursBoursier)*100;
		
		
		if(bond.getBond_type()==BondType.with_coupns) {
			
			if(issuePrice>100) {
				
				actualReturn=rendement_coupon-(issuePrice-100)/bond.getMaturity();
				
			}
			else
			{actualReturn=rendement_coupon+(100-issuePrice)/bond.getMaturity();}
		}
		
		return actualReturn;
		
	}

	@Override
	public Bond findBondById(int id) {
		
		Bond bond=em.find(Bond.class, id);
		return bond;
	}

	@Override
	public List findAllBonds() {
		
		List bonds=(List) em.createQuery("from Bond",Bond.class).getResultList();
		
		return bonds;
	}

	@Override
	public void updateBond(int IdBond, String creditor) {
		
		Bond bond = em.find(Bond.class, IdBond);
		bond.setBond_creditor(creditor);
		
	}

	@Override
	public double BondYieldCalcul(Bond bond) {
		
		double p=0;
		double cf=bond.getNominal_rate()/2;
		if(bond.getMarket_type()==MarketType.primary_market) {
		for (int i = 1; i < bond.getBond_nsemesters()-1; i++) {
			p=cf/Math.pow((1+(bond.getBond_maturity_yield()/200)),i);
			
		}
		p=p+((cf+100)/Math.pow((1+(bond.getBond_maturity_yield()/200)),bond.getBond_nsemesters()));
		}
		else
			for (int i = 1; i < bond.getBond_nsemesters(); i++) {
				p=cf/Math.pow((1+(bond.getBond_maturity_yield()/200)),i);
				
			}
			
		
		return p;
	}
	
	

}
