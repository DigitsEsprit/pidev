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
@Table(name="FEEDBACKS")
public class Feedback implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_FEEDBACK")
	private int id_feedback;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="DATE")
	private Date date;
	@Column(name="RATING")
	private String rating;
	public int getId_feedback() {
		return id_feedback;
	}
	public void setId_feedback(int id_feedback) {
		this.id_feedback = id_feedback;
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Feedback(int id_feedback, String description, Date date, String rating) {
		super();
		this.id_feedback = id_feedback;
		this.description = description;
		this.date = date;
		this.rating = rating;
	}
	public Feedback() {}
	@ManyToOne	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
