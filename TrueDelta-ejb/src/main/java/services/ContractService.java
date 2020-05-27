
package services;

import java.awt.List;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Bond;
import entities.Complain;
import entities.Contract;
import entities.ContractType;
import entities.State;
import entities.User;
import interfaces.ContractServiceLocal;
import interfaces.ContractServiceRemote;

@Stateful
@LocalBean 


public class ContractService implements ContractServiceLocal, ContractServiceRemote {
	
	ArrayList<Integer>scoreS= new ArrayList<Integer>();
	ArrayList<Integer>scoreA= new ArrayList<Integer>();
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	
	@Override
	public int addContract(Contract contract) {

		
	
		  contract.setState("en cours");
		  LocalDateTime localDateTime = LocalDateTime.now();
		  contract.setStart_date(Date.from( localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		
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
//		contract1.setDescription(contract1.getDescription());	

	}

	@Override
	public Contract findContractById(int id) {
		Contract contract=em.find(Contract.class, id);
		return contract;
	}
	@Override
	public List FindContractByEtat(String state) {
		TypedQuery<Contract> contracts = em.createQuery("select c from Contracts c where c.State = :State",Contract.class);
		contracts.setParameter("State", state);
		return (List) contracts.getResultList();
	}

	@Override
	public List FindContractByDate() {

		TypedQuery<Contract> contracts = em.createQuery("select c from Contracts c order by c.START_DATE ASC",
				Contract.class);

		return (List) contracts.getResultList();
	}
	
	
	@Override
	public List FindContractByType(String type) {
		ContractType TypeC = ContractType.valueOf(type);
		TypedQuery<Contract> contracts = em.createQuery("select c from Contracts c where c.CONTRACT_TYPE = :type",
				Contract.class);
		contracts.setParameter("CONTRACT_TYPE",TypeC);
		return (List) contracts.getResultList();
	}
	
	@Override
	public List findAllContracts() {
		
		List contracts=(List) em.createQuery("contracts",Contract.class).getResultList();
		
		return contracts;
	}

	@Override
	public double CalculGainAsset(Contract con) {
		
		ContractType ct= ContractType.all_in;
    	ContractType ctt= ContractType.specified;
    	double TotalPortefeuille=10.5;
        double	GainAsset1=0;       
        	    if(con.getContract_type().equals(ctt))
        	    {
    	           double GainAsset=TotalPortefeuille*0.3;
    	    	   return GainAsset;
             	}	
            	else if(con.getContract_type().equals(ct) )            		
            	{
                	double GainAsset=TotalPortefeuille*0.2;
                    return GainAsset;
            	}
       return GainAsset1; }
	
	@Override
	 public double CalculGainCourtier(Contract con) {
	    	ContractType ct= ContractType.all_in;
	    	ContractType ctt= ContractType.specified;
	    	double TotalPortefeuille=10.5;
	        double	GainCourtier=0;
	    	if(con.getContract_type().equals(ctt)) {
	    		 double GainCourtier1=TotalPortefeuille*0.3;
	    		 return GainCourtier1;
	    	}	
	    	else if(con.getContract_type().equals(ct) )
	     	{
	           	double GainCourtier1=TotalPortefeuille*0.2;
	            return GainCourtier1;
	      	}
	 
	return GainCourtier; }
	
	@Override
	public double CalculGainClient(Contract con) {
		
		ContractType ct= ContractType.all_in;
    	ContractType ctt= ContractType.specified;
    	double TotalPortefeuille=10.5;
        double	GainClientCT=0;   
        double	GainClientSP=0;  
        	    if(con.getContract_type().equals(ctt))
        	    {
    	           double GainClient=TotalPortefeuille*0.7;
    	    	   return GainClient;
             	}	
            	else if(con.getContract_type().equals(ct) )            		
            	{
                	double GainClient=TotalPortefeuille*0.6;
                    return GainClient;
            	}
        	   
        	    if(con.getContract_type().equals(ctt))
        	    {
                   return GainClientCT; }
                   else 
                   return GainClientSP;
        	    }
	
	@Override
	public int EstimatedScore(Contract contrat,int id) {
	int scoreAL =0;
//		int scoreSP=0;
//
//	
//		ContractType AL= ContractType.all_in;
//    	ContractType SP= ContractType.specified;
//    
//	    if((contrat.getContract_type().equals(AL)) && (contrat.getGain()<CalculGainClient(contrat)))
//	    {
//	    	scoreAL+=30;
//	    } else {scoreAL+=0;}
//	    if((contrat.getContract_type().equals(SP)) && (contrat.getGain()<CalculGainClient(contrat)))
//	    {
//	    	scoreSP+=10;
//	    }
//	    else {scoreSP+=0;}
//	    
//	 
//	    if((contrat.getContract_type().equals(SP)) && (contrat.getGain()<CalculGainClient(contrat)))
//	    {
//	    	{ for (int i = 0; i<scoreS.size(); i++) 
//		           scoreS.add( scoreSP);}
//       
//           return scoreSP;}
//   	    
//           
//	       else { for (int i = 0; i<scoreA.size(); i++) 
//	           scoreA.add( scoreAL);}
//	       { 
		return scoreAL;
}
	
	       

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public Boolean ifExists(Contract C) {
		if (em.find(Contract.class, C.getId_Contract()) == null)
			return false;
		else
			return true;
	}
	
	@Override
	public int addBond(Contract C) {		 
		if (ifExists(C) == false) {
			em.persist(C);
			System.out.println("Contract:" + C.getId_Contract());
			return C.getId_Contract();
		} else
			return (Integer) null; 
		}


	
	
	@Override
	public User affecterAssetManagerClient (Contract c) {
		TypedQuery <User> query=em.createQuery("SELECT * from User u where u.role='asset_manager' ",User.class)
				            
				              .setParameter (2,true);
		
		User u= query.getSingleResult();
		c.setUser2(u);
		return c.getUser2();
	}
	
	
	
	
	
	
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		     	