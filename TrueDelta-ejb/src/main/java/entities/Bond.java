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
//import javax.ws.rs.Path;
//import javax.ws.rs.GET;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.QueryParam;



@SuppressWarnings("serial")
@Entity
@Table(name="BONDS")
//@Path("Bond")
public class Bond implements Serializable {
	
	//private static final long serialVersionUID = 1421746759512286392L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_BOND")
	private int id_bond;
	//vaeure ecrire sur l'obligation (pourcentage)
	@Column(name="NOMINAL_VALUE")
	private double nominal_value;
	//prix d'emission => pas nesessaire = prix nominale (pourcentage)
	//emission generalemet = nominale (on dit au paire)
	//prime d'emission = nominale-prix emission
	@Column(name="ISSUE_PRICE")
	private double issue_price;
	@Column(name="DATE_OF_ISSUE")
	private Date date_of_isssue;
	@Column(name="MATURITY")
	private int maturity;
	//taux qui determine la valeur nominale
	@Column(name="NOMINAL_RATE")
	private double nominal_rate;
	@Column(name="DAYS_NEXT_COUPN")
	private int days_next_coupn;
	//@Column(name="REFUND_VALUE")
	//private double refund_value;
	@Column(name="BOND_TYPE")
	@Enumerated(EnumType.STRING)
	private BondType bond_type;
	@Column(name="MARKET_TYPE")
	@Enumerated(EnumType.STRING)
	private MarketType market_type ;
	@Column(name="CREDITOR")
	private String bond_creditor;
	@Column(name="DEPTOR")
	private String bond_deptor;
	@Column(name="NUMBER_OF_SEMESTERS")
	private int bond_nsemesters;
	@Column(name="YIELD_TO_MATURITY")
	private double bond_maturity_yield;
	@Column(name="FREQUENTY")
	private int frequency;
	
	
	
	
	
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public double getBond_maturity_yield() {
		return bond_maturity_yield;
	}
	public void setBond_maturity_yield(double bond_maturity_yield) {
		this.bond_maturity_yield = bond_maturity_yield;
	}
	public int getBond_nsemesters() {
		return bond_nsemesters;
	}
	public void setBond_nsemesters(int bond_nsemesters) {
		this.bond_nsemesters = bond_nsemesters;
	}
	public int getId_bond() {
		return id_bond;
	}
	public void setId_bond(int id_bond) {
		this.id_bond = id_bond;
	}
	public double getNominal_value() {
		return nominal_value;
	}
	public void setNominal_value(double nominal_value) {
		this.nominal_value = nominal_value;
	}
	public double getIssue_price() {
		return issue_price;
	}
	public void setIssue_price(double issue_price) {
		this.issue_price = issue_price;
	}
	public Date getDate_of_isssue() {
		return date_of_isssue;
	}
	public void setDate_of_isssue(Date date_of_isssue) {
		this.date_of_isssue = date_of_isssue;
	}
	public int getMaturity() {
		return maturity;
	}
	public void setMaturity(int maturity) {
		this.maturity = maturity;
	}
	public double getNominal_rate() {
		return nominal_rate;
	}
	public void setNominal_rate(double nominal_rate) {
		this.nominal_rate = nominal_rate;
	}
	public int getDays_next_coupn() {
		return days_next_coupn;
	}
	public void setDays_next_coupn(int days_next_coupn) {
		this.days_next_coupn = days_next_coupn;
	}
/*	public double getRefund_value() {
		return refund_value;
	}
	public void setRefund_value(double refund_value) {
		this.refund_value = refund_value;
	}
	public BondType getBond_type() {
		return bond_type;
	}*/
	public void setBond_type(BondType bond_type) {
		this.bond_type = bond_type;
	}
	public MarketType getMarket_type() {
		return market_type;
	}
	public void setMarket_type(MarketType market_type) {
		this.market_type = market_type;
	}
	public String getBond_creditor() {
		return bond_creditor;
	}
	public void setBond_creditor(String bond_creditor) {
		this.bond_creditor = bond_creditor;
	}
	public String getBond_deptor() {
		return bond_deptor;
	}
	public void setBond_deptor(String bond_deptor) {
		this.bond_deptor = bond_deptor;
	}
	public Bond( double nominal_value, double issue_price, Date date_of_isssue, int maturity,
			double nominal_rate, int days_next_coupn, double refund_value, BondType bond_type, MarketType market_type,
			String bond_creditor, String bond_deptor,int bond_nsemesters,double bond_maturity_yield,int frequency) {
		//super();
		//this.id_bond = id_bond;
		this.nominal_value = nominal_value;
		this.issue_price = issue_price;
		this.date_of_isssue = date_of_isssue;
		this.maturity = maturity;
		this.nominal_rate = nominal_rate;
		this.days_next_coupn = days_next_coupn;
		//this.refund_value = refund_value;
		this.bond_type = bond_type;
		this.market_type = market_type;
		this.bond_creditor = bond_creditor;
		this.bond_deptor = bond_deptor;
		this.bond_nsemesters=bond_nsemesters;
		this.bond_maturity_yield=bond_maturity_yield;
		this.frequency=frequency;
		
	}
	public Bond() {}
	@ManyToOne	
	private Portfolio portfolio;
	@ManyToOne	
	private Company company;
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
