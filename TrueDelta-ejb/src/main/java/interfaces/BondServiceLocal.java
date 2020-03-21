package interfaces;

import javax.ejb.Local;

import entities.Bond;

@Local
public interface BondServiceLocal {
	public int addBond(Bond bond);
	public void deleteBond(int id);
	public void updateBond(Bond bond);
	public double CoupnCalcul(Bond bond);
	public double actualRateOfReturnBond(Bond bond);
	

}
