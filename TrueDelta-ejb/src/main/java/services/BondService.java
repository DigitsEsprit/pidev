package services;

import java.awt.List;

import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Bond;
import entities.BondType;
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
	public void updateBond(Bond bond) {		
		Bond bond1=em.find(Bond.class, bond.getId_bond());
		bond1.setBond_creditor(bond.getBond_creditor());		
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
		
		List bonds=(List) em.createQuery("fron Bond",Bond.class).getResultList();
		
		return bonds;
	}
	
	

}
