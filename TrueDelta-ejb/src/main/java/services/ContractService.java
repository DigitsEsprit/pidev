
package services;

import java.awt.List;
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

		  contract.setNoticeContract(null);
		  contract.setState("en cours");
	//	  contract.setScore(30);
		
		//  contract.setGain();
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
}
	
	       

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	public void send_Email(String msg, String adress,String subject)throws MessagingException {

		final String username = "sarra.youssef@gmail.com";
	    final String password = "183JFT0494";
	    String host="smtp.gmail.com"; 
	    
	    
	    Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from",username);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
        props.setProperty("mail.debug", "true");

        
	    Session session = Session.getInstance(props,null);                            
	 
		MimeMessage message = new MimeMessage(session);
		message.setRecipients(Message.RecipientType.TO, adress);
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setText(msg);
        
		Transport transport = session.getTransport("smtp");

        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}

	@Override
	public int scoreClient(User pk) {
	
		int score=0;
		
		int idClient1;
		idClient1=pk.getId_user();
		User client = em.find(User.class, idClient1);
		if (client.getSalaire()<1000) 
		{     
			score += 10;
		}
		else if (client.getSalaire()>=1000 && client.getSalaire()<=3000)
		{
			score += 30;
		}
		else if (client.getSalaire()>3000 && client.getSalaire()<10000)
		{
			score += 50;
		}
		else
		{
			score +=100;
		}
		
		if (client.getAge()<=30)
		{
			score +=10;
		}
		else if (client.getAge()>30 && client.getAge()<55)
		{
			score +=30;
		}
		else
		{
			score +=50;
		}
		
		if (client.getEtatCivil().equals("marie"))
		{
			score +=10;
		}
		else if (client.getEtatCivil().equals("celibataire") || client.getEtatCivil().equals("divorce"))
		{
			score +=20;
		}
		else 
		{
			score +=0;
		}
		
		if (client.getMetier().equals("financier") || client.getMetier().equals("homme d'affaires"))
		{
			score +=100;
		}
		else 
		{
			score += 30;
		}
		
		return score;
	
	}	
	
	@Override
	public User affecterAssetManagerClient (Contract c) {
		TypedQuery <User> query=em.createQuery("SELECT * from User u where u.role=asset_manager AND u.score>=? ",User.class)
				              .setParameter(1,c.getScore())
				              .setParameter (2,true);
		
		User u= query.getSingleResult();
		c.setUser2(u);
		return c.getUser2();
	}
	
	
	
	
	
	
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		     	