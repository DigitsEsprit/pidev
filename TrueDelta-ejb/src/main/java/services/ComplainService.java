package services;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Complain;
import entities.State;
import entities.User;
import interfaces.IComplainServiceRemote;

@Stateful
public class ComplainService implements IComplainServiceRemote {
	
	@PersistenceContext(unitName="TrueDelta-ejb")
	
	EntityManager em;

	
	@Override
	public void AddComplaint(Complain complaint) {	
		
		em.persist(complaint);
	
	}
	
	@Override
	public void deleteComplain(int id_c) {
		
		em.remove(em.find(Complain.class, id_c));
		
	}
	
	@Override
	public void updateComplain(Complain complaint)
	{
	Complain c=em.find(Complain.class, complaint.getId_Reclamation());
	c.setDescription(complaint.getDescription());
	c.setDate(complaint.getDate());
	c.setSubject(complaint.getSubject());
	c.setState(complaint.getState());
	
	}
	
	@Override
	public void affecterComplaintoUser(int id_reclam,int id_user)
	{
		User user=em.find(User.class,id_user);
		Complain complain=em.find(Complain.class, id_reclam);
		complain.setUser(user);
	}
	
	
	@Override
	public void TreatComplaint(int id_complain, String state) {

		Calendar currenttime = Calendar.getInstance();
		Date now = new Date((currenttime.getTime()).getTime());
		State st = State.valueOf(state);
		Complain complain = em.find(Complain.class, id_complain);

		if (st == State.inprogress) {
			complain.setAssignmentDate(now);
			complain.setState(st);
			em.merge(complain);
		
		}
		else if (st == State.treated ) {

			complain.setClosingDate(now);
			complain.setState(st);
			em.merge(complain);
			
		}	
	}

	
	@Override
	public List<Complain> GetComplaintsByState(String state) {
		State st = State.valueOf(state);
		TypedQuery<Complain> q = em.createQuery("SELECT c FROM Complain c WHERE c.state = :state",
				Complain.class);
		q.setParameter("state", st);
		return (List<Complain>) q.getResultList();
	}
	
	@Override
	public List<Complain> SearchComplain(String motclé) {
		TypedQuery<Complain> query = em.createQuery(
				"select c from Complain c WHERE c.description LIKE :code or c.subject LIKE :code or c.state LIKE :code ORDER BY c.date DESC",
				Complain.class);
		query.setParameter("code", "%" + motclé + "%");
		return query.getResultList();
	}
	
	
	
	
}
