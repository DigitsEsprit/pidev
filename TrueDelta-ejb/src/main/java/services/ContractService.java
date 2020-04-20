package services;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Contract;
import entities.ContractType;
import entities.User;
import interfaces.ContractServiceLocal;
import interfaces.ContractServiceRemote;

@Stateful

public class ContractService implements ContractServiceLocal, ContractServiceRemote {
	
	ArrayList<Integer>scoreS= new ArrayList<Integer>();
	ArrayList<Integer>scoreA= new ArrayList<Integer>();
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	
	@Override
	public int addContract(Contract contract, int id_user) {
		User user = em.find(User.class, id_user); 
		if(user!=null)
        {
			  contract.setUser(user);

        
		em.persist(contract);
		return contract.getId_Contract();
        }
        else
            return 0;
	}
	
	/*@Override
	public int addContract(Contract contrat) {	
		contrat.setScore(0);
		em.persist(contrat);
		return contrat.getId_Contract();
	}
	*/
	

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
		int scoreSP=0;

	
		ContractType AL= ContractType.all_in;
    	ContractType SP= ContractType.specified;
    	
	    if((contrat.getContract_type().equals(AL)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
	    	scoreAL+=30;
	    } else {scoreAL+=0;}
	    if((contrat.getContract_type().equals(SP)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
	    	scoreSP+=10;
	    }
	    else {scoreSP+=0;}
	    
	 
	    if((contrat.getContract_type().equals(SP)) && (contrat.getGain()<CalculGainClient(contrat)))
	    {
	    	{ for (int i = 0; i<scoreS.size(); i++) 
		           scoreS.add( scoreSP);}
       
           return scoreSP;}
   	    
           
	       else { for (int i = 0; i<scoreA.size(); i++) 
	           scoreA.add( scoreAL);}
	       {       return scoreAL;
	       }}
	

	@Override
	public int ScoreFinalContratAL(Contract contrat,int id) {
		int total = 0;
		for (int note : scoreA) {
		    total += note;
		}
		int moyenne = total / scoreA.size();
		System.out.println(moyenne);
		return moyenne;
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	     	