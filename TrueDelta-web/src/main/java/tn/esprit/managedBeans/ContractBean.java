package tn.esprit.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import entities.Contract;
import entities.ContractType;
import entities.PeriodiciteVersement;
import entities.PortfolioType;
import entities.User;
import interfaces.ContractServiceRemote;
import services.ContractService;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ContractBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	private String description;

	private Date  start_date ;
	private Date  end_date ;
	private ContractType  contract_type ;
private PortfolioType portfolio_type;
	private double capital; 
	private double risk; 
	private String contracttypeenum;
	double percentage_client;
	double percentage_AM;

private String signature;  
 
private String NIVEAU;
private String typePortefeuille;
private String Reaction;
private String objectif;
private String partEpargne;
private String HorizonPlacement;
 
private String PROFIL; 
private String DESCRIPTION;
private String contrat;

private String IBAN;
private String fonds;
private String period;
private Date dateVersement;

private String program;
private double versement;
private String portfoliotypeenum;

private String periodenum;









public PortfolioType getPortfolio_type() {
	return portfolio_type;
}




public void setPortfolio_type(PortfolioType portfolio_type) {
	this.portfolio_type = portfolio_type;
}




public String getPortfoliotypeenum() {
	return portfoliotypeenum;
}




public void setPortfoliotypeenum(String portfoliotypeenum) {
	this.portfoliotypeenum = portfoliotypeenum;
}




public double getVersement() {
	return versement;
}




public void setVersement(double versement) {
	this.versement = versement;
}




public String getProgram() {
	return program;
}




public void setProgram(String program) {
	this.program = program;
}




public String getFonds() {
	return fonds;
}




public void setFonds(String fonds) {
	this.fonds = fonds;
}




public String getPeriod() {
	return period;
}




public void setPeriod(String period) {
	this.period = period;
}




public Date getDateVersement() {
	return dateVersement;
}




public void setDateVersement(Date dateVersement) {
	this.dateVersement = dateVersement;
}








public String getIBAN() {
	return IBAN;
}




public void setIBAN(String iBAN) {
	IBAN = iBAN;
}






public String getContrat() {
	return contrat;
}




public void setContrat(String contrat) {
	this.contrat = contrat;
}




public String getDESCRIPTION() {
	return DESCRIPTION;
}




public void setDESCRIPTION(String dESCRIPTION) {
	DESCRIPTION = dESCRIPTION;
}




public String getPROFIL() {
	return PROFIL;
}




public void setPROFIL(String pROFIL) {
	PROFIL = pROFIL;
}

private ExternalContext externalContext;

public String getNIVEAU() {
	return NIVEAU;
}




public void setNIVEAU(String nIVEAU) {
	NIVEAU = nIVEAU;
}




public String getTypePortefeuille() {
	return typePortefeuille;
}




public void setTypePortefeuille(String typePortefeuille) {
	this.typePortefeuille = typePortefeuille;
}




public String getReaction() {
	return Reaction;
}




public void setReaction(String reaction) {
	Reaction = reaction;
}




public String getObjectif() {
	return objectif;
}




public void setObjectif(String objectif) {
	this.objectif = objectif;
}




public String getPartEpargne() {
	return partEpargne;
}




public void setPartEpargne(String partEpargne) {
	this.partEpargne = partEpargne;
}




public String getHorizonPlacement() {
	return HorizonPlacement;
}




public void setHorizonPlacement(String horizonPlacement) {
	HorizonPlacement = horizonPlacement;
}

@EJB 
ContractService CSR;
private Contract C;

/*
public void addContract() {
Contract Vh = new Contract();

Vh.setGain(this.getGain());
Vh.setPercentage_AM(this.getPercentage_AM());
Vh.setCapital(this.getCapital());
CSR.addContract(Vh);

}
  */ 

public void ProfilInvestisseur() throws IOException {  
	
	 if ((NIVEAU.equals("PasdeRisque") ) && (typePortefeuille.equals("PortfolioA"))) {
			System.out.println("Investisseur Sécuritaire  ");
			PROFIL="Investisseur Sécuritaire" ;
			DESCRIPTION="Vous êtes donc prêt à valoriser un capital à moyen terme, tout en bénéficiant d’un risque modéré ";
			contrat="Au regard de votre profil investisseur sécuritaire, nous vous conseillons la Gestion libre";	}
 
	   else 
		   if ((NIVEAU.equals("RisqueLimite"))&& (typePortefeuille.equals("PortfolioB"))) {
			System.out.println("Investisseur Prudent  ");
			PROFIL="Investisseur Prudent" ;
			DESCRIPTION="Vous êtes prêt à accepter un rendement modéré de votre investissement en contrepartie d'un faible risque de perte en capital. ";
			contrat="Au regard de votre profil investisseur prudent, nous vous conseillons la Gestion sous Mandat";
		}
		else 
			if ((NIVEAU.equals("RisqueMoyen"))&& (typePortefeuille.equals("PortfolioC"))) {
				System.out.println("Équilibré  ");
				PROFIL="Investisseur Équilibré" ;
				DESCRIPTION="Vous êtes donc prêt à valoriser un capital à moyen terme, grâce à une diversification par classe d’actifs, tout en bénéficiant d’un risque équilibré.";
				contrat="Au regard de votre profil investisseur équilibré, nous vous conseillons la Gestion libre";}
		else 
		    if ((NIVEAU.equals("RisqueEleve")) && (typePortefeuille.equals("PortfolioD"))){
					System.out.println("Dynamique ");
					PROFIL="Investisseur Dynamique" ;
					DESCRIPTION="Vous êtes donc prêt à accepter un risque de perte en capital. Vous maîtrisez les produits et instruments financiers,vous permettant d'investir essentiellement sur des supports en unités de comptes.";
					contrat="Au regard de votre profil investisseur prudent, nous vous conseillons la Gestion sous Mandat";}
	 
	 
	 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	externalContext.redirect("ResultatProfil.xhtml");
}


public String addContract() {
	Contract c = new  Contract(capital,IBAN,versement,dateVersement);
	
	
	if(contracttypeenum.equals("Specific"))
	{
		c.setContract_type(ContractType.specified);
	} 
	else {c.setContract_type(ContractType.all_in);}
	if(portfoliotypeenum.equals("Dynamique"))
	{
		c.setPortfolio_type(PortfolioType.Dynamique);
	} 
	if(portfoliotypeenum.equals("Equilibre"))
	{
		c.setPortfolio_type(PortfolioType.Equilibre);
	} else {c.setPortfolio_type(PortfolioType.Modere);}
	
	
	
	
	
	
	if(period.equals("mensuel"))
	{
		c.setPeriodicite(PeriodiciteVersement.Mensuel);
	}

	if(period.equals("semestriel"))
	{
		c.setPeriodicite(PeriodiciteVersement.Semestriel);
	}
	if(period.equals("trimestriel"))
	{
		c.setPeriodicite(PeriodiciteVersement.Trimestriel);
	} else {	c.setPeriodicite(PeriodiciteVersement.Annuel);}
	
	
	CSR.addContract(c);
	
	return "ContratDone?faces-redirect=true";
	}




public void setStart_date(Date start_date) {
	this.start_date = start_date;
}


public Date getStart_date() {
	return start_date;
}

public Date getEnd_date() {
	return end_date;
}


public String getContracttypeenum() {
	return contracttypeenum;
}

public void setContracttypeenum(String contracttypeenum) {
	this.contracttypeenum = contracttypeenum;
}

public void setEnd_date(Date end_date) {
	this.end_date = end_date;
}


public ContractType getContract_type() {
	System.out.println("ddddddddd"+ contract_type);
	return contract_type;
}


public void setContract_type(ContractType contract_type) {
	this.contract_type = contract_type;
}

public SelectItem[] getMyContractTypeValues() {
    SelectItem[] items = new SelectItem[ContractType.values().length];
    int i = 0;
    for(ContractType g: ContractType.values()) {
      items[i++] = new SelectItem(g);
    }
    return items;
  }
//public ContractType[] getMyContractTypeValues() 
//{ return ContractType.values(); }


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public void setCSR(ContractService cSR) {
	this.CSR = cSR;
}


public double getCapital() {
	return capital;
}
public void setCapital(double capital) {
	this.capital = capital;
}





public double getPercentage_client() {
	return percentage_client;
}

public void setPercentage_client(double percentage_client) {
	this.percentage_client = percentage_client;
}

public double getPercentage_AM() {
	return percentage_AM;
}

public void setPercentage_AM(double percentage_AM) {
	this.percentage_AM = percentage_AM;
}

public double getRisk() {
	return risk;
}

public void setRisk(double risk) {
	this.risk = risk;
}

public String getSignature() {  
return signature;  
}  
public void setSignature(String signature) {  
this.signature = signature;  
}  

public void goToProfil2() throws IOException {
    // ...
	 
	 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	externalContext.redirect("Profil2.xhtml");

}

public void goToProfil1() throws IOException {
    // ...
	 
	 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	externalContext.redirect("Profil.xhtml");

}
public void goToMandat() throws IOException {
    // ...
	 
	 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	externalContext.redirect("Mandat.xhtml");

}
public void GoResultatProfil() throws IOException {
    // ...
	 
	 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	externalContext.redirect("ResultatProfil.xhtml");

}

public void GoVersement() throws IOException {
    // ...
	 
	 externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	externalContext.redirect("Versement.xhtml");

}
public String GoContrat() {
    // ...
	 

return "Contrat?faces-redirect=true";

}

public String GoIndex() {

return "index?faces-redirect=true";

}



}


