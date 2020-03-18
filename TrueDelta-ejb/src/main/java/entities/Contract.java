package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="START_DATE")
	private Date start_date;
	@Column(name="END_DATE")
	private Date end_date;
	@Column(name="CONTRACT_TYPE")
	private ContractType contract_type;
	@Column(name="RISK")
	private double risk;
	@Column(name="SIGNATURE")
	private String signature;
	@Column(name="GAIN")
	private double gain;
	@Column(name="CLIENT_PERCENTAGE")
	private double percentage_client;
	@Column(name="BROKER_PERCENTAGE")
	private double percentage_broker;
	@Column(name="AM_PERCENTAGE")
	private double percentage_AM;
	@Column(name="CAPITAL")
	private double capital;
	public int getId_Contract() {
		return id_Contract;
	}
	public void setId_Contract(int id_Contract) {
		this.id_Contract = id_Contract;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public double getGain() {
		return gain;
	}
	public void setGain(double gain) {
		this.gain = gain;
	}
	public double getPercentage_client() {
		return percentage_client;
	}
	public void setPercentage_client(double percentage_client) {
		this.percentage_client = percentage_client;
	}
	public double getPercentage_broker() {
		return percentage_broker;
	}
	public void setPercentage_broker(double percentage_broker) {
		this.percentage_broker = percentage_broker;
	}
	public double getPercentage_AM() {
		return percentage_AM;
	}
	public void setPercentage_AM(double percentage_AM) {
		this.percentage_AM = percentage_AM;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public Contract(int id_Contract, String description, Date start_date, Date end_date, ContractType contract_type,
			double risk, String signature, double gain, double percentage_client, double percentage_broker,
			double percentage_AM, double capital) {
		super();
		this.id_Contract = id_Contract;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.contract_type = contract_type;
		this.risk = risk;
		this.signature = signature;
		this.gain = gain;
		this.percentage_client = percentage_client;
		this.percentage_broker = percentage_broker;
		this.percentage_AM = percentage_AM;
		this.capital = capital;
	}
	public Contract() {
		
	}
	
	@ManyToOne	
	private User users;
}
