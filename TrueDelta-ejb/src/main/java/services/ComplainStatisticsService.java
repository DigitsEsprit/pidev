package services;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.ComplainsStatistics;
import interfaces.IComplainsStatisticsLocal;
import interfaces.IComplainsStatisticsRemote;

@Stateless

public class ComplainStatisticsService implements IComplainsStatisticsLocal, IComplainsStatisticsRemote {
	
@PersistenceContext(unitName="TrueDelta-ejb")
	
	EntityManager em;
@EJB
ComplainService complainservice;



	@Override
	public void AddStatComplaint(ComplainsStatistics Cs) {
	
		int nbrO= complainservice.NbComplaintByState("Opened");
        int nbrIP=complainservice.NbComplaintByState("inprogress");
        int nbrT = complainservice.NbComplaintByState("treated");
        int nbrTot = complainservice.GetAllComplaint().size();
		
		Calendar currenttime = Calendar.getInstance();
		Date now = new Date((currenttime.getTime()).getTime());
		Cs.setDateStat(now);
		Cs.setNbOpenedComplaint(nbrO);
		Cs.setNbinprogressComplaint(nbrIP);
		Cs.setNbTreatedComplaint(nbrT);
		Cs.setNbComplaints(nbrTot);
		em.persist(Cs);
		

	}

	@Override
	public List<ComplainsStatistics> GetAllStatComplaint() {
		TypedQuery<ComplainsStatistics> q = em.createQuery("SELECT c FROM ComplainsStatistics c", ComplainsStatistics.class);
		return (List<ComplainsStatistics>) q.getResultList();
	}

	@Override
	public List<ComplainsStatistics> GetStatComplaintByDate(Date d) {
		TypedQuery<ComplainsStatistics> q = em.createQuery("SELECT c FROM ComplainsStatistics c WHERE c.DateStat= :datestat", ComplainsStatistics.class);
		q.setParameter("datestat", d);
		return (List<ComplainsStatistics>) q.getResultList();
	}



}
