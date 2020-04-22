package interfaces;


import java.awt.List;

import javax.ejb.Local;

import entities.Contract;
import entities.ContractType;
@Local

public interface ContractServiceLocal {
	
	public int addContract(Contract contract, int id_user);
	public void deleteContract(int id);
	public void updateContract(Contract contract);
	public Contract findContractById(int id);
	public List findAllContracts();
	//public int addContract(Contract contrat);
	public double CalculGainAsset(Contract con);
	 public double CalculGainCourtier(Contract con);
	int EstimatedScore(Contract contrat,int id);
	double CalculGainClient(Contract con);

	int ScoreFinalContratAL(Contract contrat, int id);
}
