package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="COMPLAINS")

public class Complain implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_RECLAMATION")
	private int id_Reclamation;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="DATE")
	private Date date;
	@Column(name="SUBJECT")
	private String subject;
	@Column(name="STATE")
	@Enumerated(EnumType.STRING)
	private State state;
	@Column(name = "assignmentDate")
	private Date assignmentDate;
	@Column(name = "closingDate")
	private Date closingDate;
	@Column(name="REPONSE")
	private String reponse;
	
	
	private static final long serialVersionUID = -558553967080513790L;

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
	
	public Complain(int id_Reclamation, String description, Date date, String subject, State state) {
		super();
		this.id_Reclamation = id_Reclamation;
		this.description = description;
		this.date = date;
		this.subject = subject;
		this.state = state;
	}
	
	public Complain(String description,Date date, String subject, State state) {
		super();
		this.description = description;
		this.date = date;
		this.subject = subject;
		this.state = state;

	}
	public Complain(String description,String subject,State state) {
		super();
		this.description = description;
		this.subject = subject;
		this.state = state;

	}
	
	
	public Complain(int id_Reclamation, String description, Date date, String subject, State state, Date assignmentDate,
			Date closingDate, String reponse, User user, User admin, User investor) {
		super();
		this.id_Reclamation = id_Reclamation;
		this.description = description;
		this.date = date;
		this.subject = subject;
		this.state = state;
		this.assignmentDate = assignmentDate;
		this.closingDate = closingDate;
		this.reponse = reponse;
		this.user = user;
		this.admin = admin;
		this.investor = investor;
	}
	public Complain() {}
	@ManyToOne	
	@JsonBackReference

	private User user;
	private User admin;
	private User investor;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public User getInvestor() {
		return investor;
	}
	public void setInvestor(User investor) {
		this.investor = investor;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
	
	
	
	
	
	
	
}
