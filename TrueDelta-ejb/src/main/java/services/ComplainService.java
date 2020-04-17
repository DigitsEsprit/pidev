package services;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import entities.Complain;
import entities.Role;
import entities.State;
import entities.User;
import interfaces.IComplainServiceRemote;

@Stateful
@LocalBean
public class ComplainService implements IComplainServiceRemote {
	
	@PersistenceContext(unitName="TrueDelta-ejb")
	
	EntityManager em;
	Mail_API mail;

	@Override
	public int AddComplaint(Complain complaint , int id_investor) {	
		 User investor = em.find(User.class, id_investor); 
	        if(investor.getRole()==Role.investor)
          {
			  complaint.setUser(investor);

          em.persist(complaint);
          return 1;
          }
          else
              return 0;
	
	}
	
	@Override
	public void deleteComplain(int id_c) {
		
		em.remove(em.find(Complain.class, id_c));
		
	}
	
	@Override
	public List<Complain> GetAllComplaint() {

		TypedQuery<Complain> q = em.createQuery("SELECT c FROM Complain c", Complain.class);
		return (List<Complain>) q.getResultList();
		
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
	public List<Complain> GetComplaintsByState(String state) {
		State st = State.valueOf(state);
		TypedQuery<Complain> q = em.createQuery("SELECT c FROM Complain c WHERE c.state = :state",
				Complain.class);
		q.setParameter("state", st);
		return (List<Complain>) q.getResultList();
	}
	
	@Override
	public int NbComplaintByState(String state) {
		State st = State.valueOf(state);
		Query q = em.createQuery("SELECT Count(c) FROM Complain c WHERE c.state = :state");
		q.setParameter("state", st);
		return ((Number) q.getSingleResult()).intValue();
	}
	
	@Override
	public List<Complain> GetComplaintsOrderByDateASC() {

		TypedQuery<Complain> q = em.createQuery("SELECT c FROM Complain c ORDER BY c.date ASC",
				Complain.class);

		return (List<Complain>) q.getResultList();
	}

	@Override
	public List<Complain> GetComplaintsOrderByDateDESC() {

		TypedQuery<Complain> q = em.createQuery("SELECT c FROM Complain c ORDER BY c.date DESC",
				Complain.class);

		return (List<Complain>) q.getResultList();
	}
	
	@Override
	public List<Complain> SearchComplain(String motcle) {
		TypedQuery<Complain> query = em.createQuery(
				"select c from Complain c WHERE c.description LIKE :code or c.subject LIKE :code or c.state LIKE :code ORDER BY c.date DESC",
				Complain.class);
		query.setParameter("code", "%" + motcle + "%");
		return query.getResultList();
	}
	

	
	@Override
    public void TreatComplaint(int id_complain, String state,int id_admin) {

        User admin = em.find(User.class, id_admin); 
        if(admin.getRole()==Role.admin)
         {
        Calendar currenttime = Calendar.getInstance();
        Date now = new Date((currenttime.getTime()).getTime());
        State st = State.valueOf(state);
        Complain complain = em.find(Complain.class, id_complain);

        if (st == State.inprogress) {
            complain.setAssignmentDate(now);
            complain.setState(st);
            em.merge(complain);

            try {

                mail.sendMail(complain.getUser().getEmail(), "Your complaint is being processed",
                        complain.getSubject()+ "  is being processed at" + complain.getAssignmentDate());

            } catch (MessagingException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
        else if (st == State.treated ) {

            complain.setClosingDate(now);
            complain.setState(st);
            em.merge(complain);

            try {

                mail.sendMail(complain.getUser().getEmail(), "Your complaint is treated",
                        complain.getSubject() + " is treated at " + complain.getClosingDate()
                                + " with state : " + complain.getState());

            } catch (MessagingException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }
    }
	
	@Override
	public Complain AffectComplaintsToAdmin(int id) {
		
		Complain c = em.find(Complain.class, id);
		if(c.getAdmin()==null)
		{
		User admin = em.find(User.class, 1); 
		if(admin.getRole()==Role.admin)
		 {
		  c.setAdmin(admin);
		  c.setState(State.inprogress);
		  Calendar currenttime = Calendar.getInstance();
		  Date assignmentDate = new Date((currenttime.getTime()).getTime());
		  c.setAssignmentDate(assignmentDate);
		  em.merge(c);
		 }
		
		}
		return c;
	}

	
	

	
	
	
}
