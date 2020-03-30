package interfaces;


import java.util.List;

import javax.ejb.Remote;
import entities.Complain;

@Remote
public interface IComplainServiceRemote {
	public void AddComplaint(Complain complaint);	
	public void deleteComplain(int id_c);
	public void updateComplain(Complain complaint);
	public void affecterComplaintoUser(int id_reclam,int id_user);
	public void TreatComplaint(int id_complain, String State);
	public List<Complain> GetComplaintsByState(String State);
	public List<Complain> SearchComplain(String motclé);
	}

