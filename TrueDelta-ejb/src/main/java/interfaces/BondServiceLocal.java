package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Bond;
import entities.Portfolio;
import entities.User;

@Local
public interface BondServiceLocal {
	
	//public void Consomation();
	
	public int addBond(Bond bond);
	public int deleteBond(int id);
	public void updateBond(int IdBond, String creditor );
	public Bond findBondById(int id);
	public List<Bond> findAllBonds();
	public String findBondByCreditor(String creditor);

	public double BondPrice(Bond bond , double r);
	public double CashFlow(Bond bond, int t);
	public double PvCashFlow(Bond bond, int t, double r);
	public double actuarialRateOfReturnBond(Bond bond , double coursBoursier);
	public double CurrentYieldBon(Bond bond);
	public double DurationClcul(Bond bond, double r);
	public double SensibilityCalcul(Bond bond,double r);
	public double ConvexityCalcul(Bond bond , double r);
	public double NbBondPortfolio(Portfolio p);
	public List<Bond> getAllBondsByPortfolio(int idp);
	public double ScoringBnd(Bond bond);
	public String ClassificationBond(Bond bond);
	public double getPortfolioCapital(User u);
	public double ScoringBndPortfolio(int id, User user);


	
	

} 
