package interfaces;

import java.awt.List;

import javax.ejb.Remote;

import entities.Contract;


@Remote

public interface ContractServiceRemote {
	
	public int addContract(Contract contract);
	public void deleteContract(int id);
	public void updateContract(Contract contract);
	public Contract findContractById(int id);
	public List findAllContracts();

}
