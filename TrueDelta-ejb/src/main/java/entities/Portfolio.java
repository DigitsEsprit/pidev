package entities;

import java.io.Serializable;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="PORTFOLIOS")
public class Portfolio implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_PORTFOLIO")
	private int id_portfolio;
	@Column(name="CAPITAL")
	private double capital;
	public int getId_portfolio() {
		return id_portfolio;
	}

	public void setId_portfolio(int id_portfolio) {
		this.id_portfolio = id_portfolio;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public Portfolio(int id_portfolio, double capital) {
		super();
		this.id_portfolio = id_portfolio;
		this.capital = capital;
	}

	public Portfolio() {
		
	}
	@OneToOne
	private User user;	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolio")
	private List<Bond> bonds ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolio")
	private List<Stock> stocks ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolio")
	private List<Option> options ;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Bond> getBonds() {
		return bonds;
	}

	public void setBonds(List<Bond> bonds) {
		this.bonds = bonds;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	
}