package interfaces;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entities.ComplainsStatistics;
import entities.State;


@Remote
public interface IComplainsStatisticsRemote {
	void AddStatComplaint(ComplainsStatistics Cs);
	List<ComplainsStatistics> GetAllStatComplaint();
	List<ComplainsStatistics> GetStatComplaintByDate(Date d);
}
