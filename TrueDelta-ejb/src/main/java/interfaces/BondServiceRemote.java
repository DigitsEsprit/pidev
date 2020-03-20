package interfaces;

import javax.ejb.Remote;


import entities.Bond;

@Remote
public interface BondServiceRemote {
	public int addBond(Bond bond);
	public void deleteBond(int id);
	public void updateBond(Bond bond);
	public double CoupnCalcul(Bond bond); 

}
