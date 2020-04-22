package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="COMPANIES")
public class Company implements Serializable{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_COMPANY")
	private int id_company;
	@Column(name="SYMBOL")
	private String symbol;
	@Column(name="INDUSTRY")
	private String industry;
	@Column(name="NAME")
	private String name;
	@Column(name="SECTOR")
	private String sector;
	public int getId_company() {
		return id_company;
	}
	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public Company(int id_company, String symbol, String industry, String name, String sector) {
		super();
		this.id_company = id_company;
		this.symbol = symbol;
		this.industry = industry;
		this.name = name;
		this.sector = sector;
	}
	public Company(String symbol, String industry, String name, String sector) {
		this.symbol = symbol;
		this.industry = industry;
		this.name = name;
		this.sector = sector;
	}
	
	public Company() {}
	@OneToMany(cascade = CascadeType.ALL, mappedBy="company")
	private List<Bond> bonds ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="company")
	private List<Stock> stocks ;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="company")
	private List<Option> options ;
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
	
	
	//Méthodes
	public void addStock(Stock stock) {
		
		stock.setCompany(this);
		if(this.stocks == null) //Car la première fois, aucune liste n'est instanciée
			this.stocks = new ArrayList<Stock>();
		
		this.stocks.add(stock);
	}
	
	
}
