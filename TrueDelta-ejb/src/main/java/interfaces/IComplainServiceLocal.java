package interfaces;


import java.util.List;

import javax.ejb.Local;
import entities.Complain;
import entities.State;


@Local
public interface IComplainServiceLocal {
	
	public int AddComplaint(Complain complaint, int id_investor);
	public void deleteComplain(int id_c);
	public void updateComplain(Complain complaint);
	public List<Complain> GetAllComplaint();
	public List<Complain> GetComplaintsByState(String State);
	public int NbComplaintByState(String State);
	public List<Complain> GetComplaintsOrderByDateASC();
	public List<Complain> GetComplaintsOrderByDateDESC();
    public List<Complain> GetReclamByclient(int CltID) ;
	public List<Complain> SearchComplain(String motclé);
	public Complain AffectComplaintsToAdmin(int id);
    public void TreatComplaint(int id_complain, String state,String reponse);
    public String verifBadWord(int idRec ) throws InterruptedException;
    public List<State> GetComplaintState();



}
