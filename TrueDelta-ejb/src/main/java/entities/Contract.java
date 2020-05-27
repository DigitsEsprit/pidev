package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="CONTRACTS")
public class Contract implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_CONTRACT")
	private int id_Contract;
	@Column(name="START_DATE")
	private Date start_date;
	
	@Column(name="END_DATE")
	private Date end_date;
	@Column(name="CONTRACT_TYPE")
	@Enumerated(EnumType.STRING)
	private ContractType contract_type;
	
	@Column(name="PORTFOLIO_TYPE")
	@Enumerated(EnumType.STRING)
	private PortfolioType portfolio_type;
	@Column(name="RISK")
	private double risk;
	@Column(name="SIGNATURE")
	private String signature;
	@Column(name="CAPITAL")
	private double capital;
	@Column(nullable=true)
	private String State;
	
	@Column(name="IBAN")
	private String IBAN;
	
	@Column(name="MONTANT_VERSEMENT")
	private double MontantVersement;
	
	@Column(name="DATE_PREMIERVERSEMENT")
	private Date DatePremierVersement;
	
	@Column(name="PERIODICITE_VERSEMENT")
	@Enumerated(EnumType.STRING)
	private PeriodiciteVersement periodicite;
	
	
	public int getId_Contract() {
		return id_Contract;
	}
	public void setId_Contract(int id_Contract) {
		this.id_Contract = id_Contract;
	}

	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public ContractType getContract_type() {
		return contract_type;
	}
	public void setContract_type(ContractType contract_type) {
		this.contract_type = contract_type;
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
	
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	
	public Contract() {
		
	}
	
	@ManyToOne	
	private User user1;
	
	@ManyToOne	
	private User user2;
	
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}

	
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public double getMontantVersement() {
		return MontantVersement;
	}
	public void setMontantVersement(double montantVersement) {
		MontantVersement = montantVersement;
	}
	public Date getDatePremierVersement() {
		return DatePremierVersement;
	}
	public void setDatePremierVersement(Date datePremierVersement) {
		DatePremierVersement = datePremierVersement;
	}
	
	public PeriodiciteVersement getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(PeriodiciteVersement periodicite) {
		this.periodicite = periodicite;
	}
	public Contract(Date start_date, Date end_date, ContractType contract_type, double capital,String Signature) {
		super();
	
		this.start_date = start_date;
		this.end_date = end_date;
		this.contract_type = contract_type;
		this.capital = capital;

		this.signature = signature;
		
	}


	public PortfolioType getPortfolio_type() {
		return portfolio_type;
	}
	public void setPortfolio_type(PortfolioType portfolio_type) {
		this.portfolio_type = portfolio_type;
	}
	public Contract(double capital, String iBAN,double montantVersement, Date datePremierVersement) {
		super();
		
		this.capital = capital;
		IBAN = iBAN;
		MontantVersement = montantVersement;
		DatePremierVersement = datePremierVersement;
	
	}
	
	
}
