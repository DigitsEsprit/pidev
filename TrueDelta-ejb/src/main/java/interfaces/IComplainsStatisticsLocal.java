package interfaces;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.ComplainsStatistics;
import entities.State;

@Local
public interface IComplainsStatisticsLocal {
	void AddStatComplaint(ComplainsStatistics Cs);
	List<ComplainsStatistics> GetAllStatComplaint();
	List<ComplainsStatistics> GetStatComplaintByDate(Date d);
}
