package services;

import java.awt.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Bond;
import entities.Contract;
import interfaces.ContractServiceLocal;
import interfaces.ContractServiceRemote;


@Stateful
public class ContractService implements ContractServiceLocal, ContractServiceRemote {
	
	
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	
	@Override
	public int addContract(Contract contract) {
		em.persist(contract);
		return contract.getId_Contract();
	}
	

	@Override
	public void deleteContract(int id) {
		em.remove(em.find(Contract.class, id));	

	}

	@Override
	public void updateContract(Contract contract) {
		Contract contract1=em.find(Contract.class, contract.getId_Contract());
		contract1.setDescription(contract1.getDescription());	

	}

	@Override
	public Contract findContractById(int id) {
		Contract contract=em.find(Contract.class, id);
		return contract;
	}

	@Override
	public List findAllContracts() {
		
		List contracts=(List) em.createQuery("contracts",Contract.class).getResultList();
		
		return contracts;
	}

}
