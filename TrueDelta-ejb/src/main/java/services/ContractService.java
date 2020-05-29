
package services;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
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


import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.record.chart.DataFormatRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
		TypedQuery<Contract> contracts = em.createQuery("select c from Contracts c where c.State ='en cours'",Contract.class);
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
	

	public java.util.List<Contract> findAll() {
			java.util.List<Contract> c = em.createQuery("SELECT c FROM  Contract c ", Contract.class).getResultList();
		System.out.println("list Contract : ");
		return c;
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
	


	
	
//	@Override
//	public User affecterAssetManagerClient (Contract c) {
//		TypedQuery <User> query=em.createQuery("SELECT * from User u where u.role='asset_manager' ",User.class)
//				            
//				              .setParameter (2,true);
//		
//		User u= query.getSingleResult();
//		c.setUser2(u);
//		return c.getUser2();
//	}
	
	
	
	@Schedule(second="*/1", minute="*", hour="*", persistent=false)	
	@Override
	public boolean Matched()
	{
		java.util.List<Contract> liste1= findAll();
			System.out.println("Inside Matching function");
	    	try {
	    		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\ASUS\\Desktop\\MatchingSS.xlsx"));
	    		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		    	XSSFSheet sheet = workbook.getSheetAt(0);
		   for(Row row : sheet)
				{
	
			   row.getCell(0).setCellType(CellType.STRING);
			   row.getCell(1).setCellType(CellType.STRING);
			   String  IBAN=row.getCell(0).getStringCellValue();
			   String Capital=row.getCell(1).getStringCellValue();
			   Double capital= Double.valueOf(Capital);
			   
				for (Contract c : liste1)
				{      
			        	if(c.getIBAN().equals(IBAN))
						  
			        	 { 
			        		   if((c.getCapital()==capital)||(c.getCapital()<capital)) 
			        		        {
			        			   c.setState("traité validé"); 
			        			   System.out.println("Matched");
			        			   System.out.println("base CAPITAL"+c.getCapital()+"CAPITAL FICHIER"+capital);
			        			   System.out.println("base IBAN"+c.getIBAN()+"IBAN FICHIER"+IBAN);
			        			
			        		         }
			        		     else {
			        			     c.setState("refusé");
			        		         System.out.println("non Matched");
			        		         System.out.println("base IBAN"+c.getIBAN()+"IBAN FICHIER"+IBAN);
		        			         System.out.println("base CAPITAL"+c.getCapital()+"CAPITAL FICHIER"+capital);
			        		          }
			        		} 
			        	
			        	
					      }    
			     	
				}
				
				}  
		   catch(Exception e) {
			   System.out.println(e.getMessage());
			}
			return true;	
		 
	}
	
	
	
	
	
	
	@Override
	public void sendMail(String email) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sarra.youssef@esprit.tn", "sarra1234Y");
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			User user = em.createQuery("select u from User u where u.email=:email", User.class)
					.setParameter("email", email).getSingleResult();
			msg.setFrom(new InternetAddress("sarra.youssef@esprit.tn"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			msg.setSubject("Forgetten password");
			msg.setSentDate(new Date());
			msg.setText("Change your password here: http://localhost:9080/TrueDelta-web/pages/newpassword.xhtml?token=");
			Transport.send(msg);
		} catch (MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
		}

	}

	
	
	
	
}
	
		     	