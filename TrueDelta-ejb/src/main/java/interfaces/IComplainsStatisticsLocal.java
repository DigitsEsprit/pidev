package interfaces;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import entities.ComplainsStatistics;

@Local
public interface IComplainsStatisticsLocal {
	void AddStatComplaint(ComplainsStatistics Cs);
	List<ComplainsStatistics> GetAllStatComplaint();
	List<ComplainsStatistics> GetStatComplaintByDate(Date d);
	
}
