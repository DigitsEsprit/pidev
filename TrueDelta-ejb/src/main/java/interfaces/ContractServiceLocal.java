package interfaces;


import java.awt.List;

import javax.ejb.Local;

import entities.Contract;
@Local

public interface ContractServiceLocal {
	
	
	public int addContract(Contract contract);
	public void deleteContract(int id);
	public void updateContract(Contract contract);
	public Contract findContractById(int id);
	public List findAllContracts();

}
