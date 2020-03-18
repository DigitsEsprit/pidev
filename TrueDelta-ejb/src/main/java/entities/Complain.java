package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
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
	private State state;
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
	public Complain(int id_Reclamation, String description, Date date, String subject, State state) {
		super();
		this.id_Reclamation = id_Reclamation;
		this.description = description;
		this.date = date;
		this.subject = subject;
		this.state = state;
	}
	public Complain() {}
	@ManyToOne	
	private User users;
}
