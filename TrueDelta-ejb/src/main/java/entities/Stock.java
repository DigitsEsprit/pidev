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
@Table(name="STOCKS")
public class Stock implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ID_STOCK")
	private int id_stock;
	@Column(name="OPEN")
	private double open;
	@Column(name="HIGH")
	private double high;
	@Column(name="LOW")
	private double low;
	@Column(name="CLOSE")
	private double close;
	@Column(name="ADJ_CLOSE")
	private double adj_close;
	@Column(name="VOLUME")
	private double volume;
	@Column(name="DATE")
	private Date date;
	public int getId_stock() {
		return id_stock;
	}
	public void setId_stock(int id_stock) {
		this.id_stock = id_stock;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getAdj_close() {
		return adj_close;
	}
	public void setAdj_close(double adj_close) {
		this.adj_close = adj_close;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Stock(double open, double high, double low, double close, double adj_close, double volume,
			Date date) {
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adj_close = adj_close;
		this.volume = volume;
		this.date = date;
	}
	
	public Stock() {}
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
