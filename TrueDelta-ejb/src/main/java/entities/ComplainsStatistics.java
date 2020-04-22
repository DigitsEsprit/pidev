package entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ComplainsStatistics")

	
	public class ComplainsStatistics implements Serializable {

	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "StatisticsId")
		private int id;
		@Column(name = "NbinprogressComplaint")
		private int NbinprogressComplaint;
		@Column(name = "NbOpenedComplaint")
		private int NbOpenedComplaint;
		@Column(name = "NbTreatedComplaint")
		private int NbTreatedComplaint;
		@Column(name = "NbComplaints")
		private int NbComplaints;
		@Column(name = "DateStat")
		private Date DateStat;
		
		public ComplainsStatistics(int id, int nbinprogressComplaint, int nbOpenedComplaint, int nbTreatedComplaint,
				int nbComplaints, Date dateStat) {
			super();
			this.id = id;
			NbinprogressComplaint = nbinprogressComplaint;
			NbOpenedComplaint = nbOpenedComplaint;
			NbTreatedComplaint = nbTreatedComplaint;
			NbComplaints = nbComplaints;
			DateStat = dateStat;
		}
		
		public ComplainsStatistics(int nbinprogressComplaint, int nbOpenedComplaint, int nbTreatedComplaint,
				int nbComplaints, Date dateStat) {
			super();
			NbinprogressComplaint = nbinprogressComplaint;
			NbOpenedComplaint = nbOpenedComplaint;
			NbTreatedComplaint = nbTreatedComplaint;
			NbComplaints = nbComplaints;
			DateStat = dateStat;
		}
		public ComplainsStatistics() {
			super();
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public int getNbComplaints() {
			return NbComplaints;
		}
		public void setNbComplaints(int nbComplaints) {
			NbComplaints = nbComplaints;
		}
		public Date getDateStat() {
			return DateStat;
		}
		public void setDateStat(Date dateStat) {
			DateStat = dateStat;
		}

		
}
