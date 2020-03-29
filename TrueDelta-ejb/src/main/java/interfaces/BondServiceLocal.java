package interfaces;

import java.awt.List;

import javax.ejb.Local;

import entities.Bond;

@Local
public interface BondServiceLocal {
	public int addBond(Bond bond);
	public void deleteBond(int id);
	public void updateBond(int IdBond, String creditor );
	public double CoupnCalcul(Bond bond);
	public double actualRateOfReturnBond(Bond bond , double coursBoursier);
	public Bond findBondById(int id);
	public List findAllBonds();
	public double BondYieldCalcul (Bond bond);
	
	

}
