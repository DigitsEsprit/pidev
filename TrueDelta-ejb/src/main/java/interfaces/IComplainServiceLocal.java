package interfaces;


import java.util.List;

import javax.ejb.Local;
import entities.Complain;


@Local
public interface IComplainServiceLocal {
	public int AddComplaint(Complain complaint, int id_user);
	public void deleteComplain(int id_c);
	public void updateComplain(Complain complaint);
	public List<Complain> GetAllComplaint();
	public List<Complain> GetComplaintsByState(String State);
	public int NbComplaintByState(String State);
	public List<Complain> GetComplaintsOrderByDateASC();
	public List<Complain> GetComplaintsOrderByDateDESC();
	public List<Complain> SearchComplain(String motclé);
	public Complain AffectComplaintsToAdmin(int id);
	public void TreatComplaint(int id_complain, String State);


}
