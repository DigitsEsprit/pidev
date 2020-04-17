package interfaces;


import java.util.List;

import javax.ejb.Remote;
import entities.Complain;

@Remote
public interface IComplainServiceRemote {
	public int AddComplaint(Complain complaint, int id_investor);
	public void deleteComplain(int id_c);
	public void updateComplain(Complain complaint);
	public List<Complain> GetAllComplaint();
	public List<Complain> GetComplaintsByState(String State);
	public int NbComplaintByState(String State);
	public List<Complain> GetComplaintsOrderByDateASC();
	public List<Complain> GetComplaintsOrderByDateDESC();
	public List<Complain> SearchComplain(String motclé);
	public Complain AffectComplaintsToAdmin(int id);
    public void TreatComplaint(int id_complain, String state,int id_admin);

	}

