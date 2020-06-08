package tn.esprit.managedBeans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.*;



import entities.Complain;
import entities.ComplainsStatistics;
import entities.State;
import services.ComplainService;

import javax.ejb.EJB;

@ManagedBean(name= "ComplainBean")
@SessionScoped
public class ComplainBean implements Serializable {
	private static final long serialVersionUID= 1L;
	
	private int id_Reclamation;
	private String description;
	private Date date;
	private String subject;
	public State state;
	private Date assignmentDate;
	private Date closingDate;
	private String reponse;
    
	
	@EJB
	ComplainService complainservice;
	
	public String AddComplaint() throws InterruptedException {
		Complain c = new Complain(description,new Date(),subject,state);
		complainservice.AddComplaint(c,3);
		 try {
			 complainservice.verifBadWord(c.getId_Reclamation());
	            }
	            catch ( Exception e)
	            {
	             System.out.println("errrrrrrr");
	             e.getMessage();
	             }
		 
	    return "/getClientComplain.xhtml?faces-redirect=true";
	}

	
	private List<Complain> complains;
	private List<Complain> complainsClient;
	private List<ComplainsStatistics> complainstat;
	private List<State> states = new ArrayList<State>();
	
	
	
	
	
	
	public List<Complain> getComplains() {
		complains= complainservice.GetAllComplaint();
	return complains;
	}
	

	
	public List<Complain> getComplainsClient() {
		complainsClient= complainservice.GetReclamByclient(3);

		return complainsClient;
	}
	
	
	


	public String removeComplaint(int compId)
	{
		complainservice.deleteComplain(compId);
	    return "/getClientComplain.xhtml?faces-redirect=true";

	}
	
	public String removeComplaint2(int compId)
	{
		complainservice.deleteComplain(compId);
	    return "/getClientComplain.xhtml?faces-redirect=true";

	}
	
	private Integer complainIdToBeUpdated;
	public String displayComplain(Complain c)
	{
	this.setSubject(c.getSubject());
	this.setDescription(c.getDescription());
	this.setState(c.getState());
	this.setDate(c.getDate());
	this.setComplainIdToBeUpdated(c.getId_Reclamation());
    return "/updateComplain.xhtml?faces-redirect=true";

	}
	
	
	public String updateComplaint()
	{ complainservice.updateComplain(new Complain(complainIdToBeUpdated,description,date,subject,state)); 
    return "/getClientComplain.xhtml?faces-redirect=true";

	}
	
    private String statee="";

	public String goToTreatComplain(Complain c)
	{
		statee = state.inprogress.toString();
	this.setSubject(c.getSubject());
	this.setDescription(c.getDescription());
	c.setState(state.inprogress);
	this.setState(state.inprogress);
	this.setReponse(c.getReponse());
	this.setDate(c.getDate());
	this.setComplainIdToBeTreated(c.getId_Reclamation());
	complainservice.TreatComplaint(complainIdToBeTreated,statee, null); 
    return "/TreatComplain.xhtml?faces-redirect=true";

	}
	
	private Integer complainIdToBeTreated;
    
	public String treatComplaint()
	{      statee="treated";

		 complainservice.TreatComplaint(complainIdToBeTreated,statee, reponse); 
    return "/getComplain.xhtml?faces-redirect=true";

	}
	
	
	public ComplainBean() {

		super();
	}


	public ComplainBean(int id_Reclamation, String description, Date date, String subject, State state,
			Date assignmentDate, Date closingDate, String reponse) {
		super();
		this.id_Reclamation = id_Reclamation;
		this.description = description;
		this.date = date;
		this.subject = subject;
		this.state = state;
		this.assignmentDate = assignmentDate;
		this.closingDate = closingDate;
		this.reponse = reponse;
	}


	public ComplainBean(String description, Date date, String subject, State state) {
		super();
		this.description = description;
		this.date = date;
		this.subject = subject;
		this.state = state;
	}



	public void setComplainsClient(List<Complain> complainsClient) {
		this.complainsClient = complainsClient;
	}

	public int getId_Reclamation() {
		return id_Reclamation;
	}

	public void setId_Reclamation(int id_Reclamation) {
		this.id_Reclamation = id_Reclamation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	

	public List<ComplainsStatistics> getComplainstat() {
		return complainstat;
	}



	public void setComplainstat(List<ComplainsStatistics> complainstat) {
		this.complainstat = complainstat;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	

	public void setComplains(List<Complain> complains) {
		this.complains = complains;
	}


	public Integer getComplainIdToBeUpdated() {
		return complainIdToBeUpdated;
	}


	public void setComplainIdToBeUpdated(Integer complainIdToBeUpdated) {
		this.complainIdToBeUpdated = complainIdToBeUpdated;
	}



	public Integer getComplainIdToBeTreated() {
		return complainIdToBeTreated;
	}



	public void setComplainIdToBeTreated(Integer complainIdToBeTreated) {
		this.complainIdToBeTreated = complainIdToBeTreated;
	}



	public String getStatee() {
		return statee;
	}



	public void setStatee(String statee) {
		this.statee = statee;
	}



	public List<State> getStates() {
		states=complainservice.GetComplaintState();
		return states;
	}



	public void setStates(List<State> states) {
		this.states = states;
	}


	String  motcle;

    private List<Complain> filteredComplain;

    public void filteredComplain()
    {
		filteredComplain=complainservice.SearchComplain(motcle);

    }
    
	public List<Complain> getFilteredComplain() {
		return filteredComplain;
	}



	public void setFilteredComplain(List<Complain> filteredComplain) {
		this.filteredComplain = filteredComplain;
	}
    


	
	

	


	



	

	


	





	



	
}
