package entities;

import java.io.Serializable;
import java.util.Set;

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
	private User users;	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolios")
	private Set<Bond> bonds ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolios")
	private Set<Stock> stocks ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolios")
	private Set<Option> options ;
	
}