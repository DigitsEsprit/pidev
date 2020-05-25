package interfaces;

import java.awt.List;

import javax.ejb.Remote;
import javax.mail.MessagingException;

import entities.Contract;
import entities.ContractType;
import entities.User;


@Remote

public interface ContractServiceRemote {
	
	public int addContract(Contract contract);

	public void deleteContract(int id);
	public void updateContract(Contract contract);
	public Contract findContractById(int id);
	public List findAllContracts();
	//public int addContract(Contract contrat);
	public double CalculGainAsset(Contract con);
	 public double CalculGainCourtier(Contract con);
	//int EstimatedScore(Contract contrat,int id);
	double CalculGainClient(Contract con);
	

	int EstimatedScore(Contract contrat, int id);
	List FindContractByEtat(String state);
	List FindContractByDate();
	List FindContractByType(String type); 
	int addBond(Contract contract);
	Boolean ifExists(Contract C);

	void send_Email(String msg, String adress, String subject) throws MessagingException;

	int scoreClient(User pk);
	User affecterAssetManagerClient(Contract c);



} 
