package tn.esprit.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.ComplainsStatistics;
import interfaces.IComplainsStatisticsLocal;

@ManagedBean(name= "ComplainStatBean")
@SessionScoped
public class ComplainStatBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	IComplainsStatisticsLocal complainStatservice;
	
	private List<ComplainsStatistics> complainstats;
	private int NbinprogressComplaint ;
	private int NbOpenedComplaint ;
	private int NbTreatedComplaint;
	
	
    public String addstatClaim()
    
	{
		ComplainsStatistics c = new ComplainsStatistics();
		complainStatservice.AddStatComplaint(c);
	    return "/StatisticComplain.xhtml?faces-redirect=true";

		
	}
	
	public List<ComplainsStatistics> getComplainstats() {
		complainstats =complainStatservice.GetAllStatComplaint();
		return complainstats;
	}
	public void setComplainstats(List<ComplainsStatistics> complainstats) {
		this.complainstats = complainstats;
	}
	public int getNbinprogressComplaint() {
		return NbinprogressComplaint;
	}
	public void setNbinprogressComplaint(int nbinprogressComplaint) {
		NbinprogressComplaint = nbinprogressComplaint;
	}
	public int getNbOpenedComplaint() {
		return NbOpenedComplaint;
	}
	public void setNbOpenedComplaint(int nbOpenedComplaint) {
		NbOpenedComplaint = nbOpenedComplaint;
	}
	public int getNbTreatedComplaint() {
		return NbTreatedComplaint;
	}
	public void setNbTreatedComplaint(int nbTreatedComplaint) {
		NbTreatedComplaint = nbTreatedComplaint;
	}
	
	
	
	
	
    
    
 

}
