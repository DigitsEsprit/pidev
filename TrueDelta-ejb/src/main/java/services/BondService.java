package services;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Bond;
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

}
