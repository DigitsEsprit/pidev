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
@Table(name="OPTIONS")
public class Option implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_OPTION")
	private int id_Option;
	@Column(name="STRIKE")
	private double strike;
	@Column(name="PRIME")
	private double prime;
	@Column(name="VOLATILITY")
	private double volatility;
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private OptionType type;
	@Column(name="START_DAY")
	private Date start_day;
	@Column(name="END_DAY")
	private Date end_day;
	@Column(name="DURATION")
	private double duration;
	@Column(name="DESCRIPTION")
	private String description;
	public int getId_Option() {
		return id_Option;
	}
	public void setId_Option(int id_Option) {
		this.id_Option = id_Option;
	}
	public double getStrike() {
		return strike;
	}
	public void setStrike(double strike) {
		this.strike = strike;
	}
	public double getPrime() {
		return prime;
	}
	public void setPrime(double prime) {
		this.prime = prime;
	}
	public double getVolatility() {
		return volatility;
	}
	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}
	public OptionType getType() {
		return type;
	}
	public void setType(OptionType type) {
		this.type = type;
	}
	public Date getStart_day() {
		return start_day;
	}
	public void setStart_day(Date start_day) {
		this.start_day = start_day;
	}
	public Date getEnd_day() {
		return end_day;
	}
	public void setEnd_day(Date end_day) {
		this.end_day = end_day;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Option(int id_Option, double strike, double prime, double volatility, OptionType type, Date start_day,
			Date end_day, double duration, String description) {
		super();
		this.id_Option = id_Option;
		this.strike = strike;
		this.prime = prime;
		this.volatility = volatility;
		this.type = type;
		this.start_day = start_day;
		this.end_day = end_day;
		this.duration = duration;
		this.description = description;
	}
	public Option() {
		
	}
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
