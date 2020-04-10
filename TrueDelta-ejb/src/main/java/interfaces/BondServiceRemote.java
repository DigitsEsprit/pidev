package interfaces;

import java.util.List;

import javax.ejb.Remote;


import entities.Bond;

@Remote
public interface BondServiceRemote {
	
	//public void Consomation();
	
	public int addBond(Bond bond);
	public void deleteBond(int id);
	public void updateBond(int IdBond, String creditor);
	public double CoupnCalcul(Bond bond, int t); 
	public double actuarialRateOfReturnBond(Bond bond , double coursBoursier);
	public Bond findBondById(int id);
	public List findAllBonds();
	//public double BondYieldCalcul (Bond bond);
	public double CurrentYieldBon(Bond bond);
	public String findBondByCreditor(String creditor);
	public double DurationClcul(Bond bond, double r);
	public double SensibilityCalcul(Bond bond,double r);
	public double ScoringBnd(Bond bond);
	public String ClassificationBond(Bond bond);

	




}
