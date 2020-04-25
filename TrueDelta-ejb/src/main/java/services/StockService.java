package services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONException;
import org.json.JSONObject;

import entities.Company;
import entities.Stock;
import interfaces.IStockServiceRemote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import yahoofinance.YahooFinance;

@Stateful
public class StockService implements IStockServiceRemote{

	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	
	
	@Override
	public int addStock(Stock stock) {
		
		em.persist(stock);
		System.out.println("Stock : id = " + stock.getId_stock() + " has been added!");
		return stock.getId_stock();
	}

	@Override
	public List<Stock> findAllStocks() {
	 
		List<Stock> stocks = em.createQuery("select s from Stock s", Stock.class).getResultList();
		return stocks;
	}

	@Override
	public void deleteStock(int idStock) {
		
		em.remove(em.find(Stock.class, idStock));	
		System.out.println("Stock : id = " + idStock + " has been deleted!");
	}

	@Override
	public void updateStockByDate(Stock stock, Date newDate) {
		
		//double open, double high, double low, double close, double adj_close, double volume, Date date
		Query query = em.createQuery("update Stock s set s.open=:open,s.high=:high,s.low=:low,s.close=:close,"
				+ "s.adj_close=:adj_close,s.volume=:volume, s.date=:date  where s.id_stock=:id_stock and s.date=:oldDate");
				query.setParameter("open", stock.getOpen());
				query.setParameter("high", stock.getHigh());
				query.setParameter("low", stock.getLow());
				query.setParameter("close", stock.getClose());
				query.setParameter("adj_close", stock.getAdj_close());
				query.setParameter("volume", stock.getVolume());
				//////////////////New date///////////////////////
				query.setParameter("date", newDate);
				//////////////////////////////////////////////////////
				query.setParameter("id_stock", stock.getId_stock());
				//Old date
				query.setParameter("oldDate", stock.getDate());
				
				int m=query.executeUpdate();
			    if (m==1) {
			    	System.out.println("updated successfully");}
			    	else {
			    		System.out.println("failed to update");
			    }
		
	}

	@Override
	public Stock findStockById(int idStock) {
		
		 /*Query query = em.createQuery("select s from Stocks s where s.id=:id", Stock.class);
		 query.setParameter("id", idStock);
		 Stock stock = (Stock) query.getSingleResult();*/
		
		 Stock stock = em.find(Stock.class, idStock);
		 System.out.println("Stock : id = " + idStock + " | Adj Close = " + stock.getAdj_close());
		 return stock;
	}
	
	@Override
	public Stock getRealStockInformation(Company company) throws IOException{
		
		yahoofinance.Stock RealStock = YahooFinance.get(company.getSymbol());
		Stock stock = new Stock();		
		
		stock.setOpen(RealStock.getQuote().getOpen().doubleValue());
		stock.setHigh(RealStock.getQuote().getDayHigh().doubleValue());
		stock.setLow(RealStock.getQuote().getDayLow().doubleValue());
		stock.setClose(RealStock.getQuote().getPrice().doubleValue());
		stock.setAdj_close(RealStock.getQuote().getPrice().doubleValue());
		stock.setVolume(RealStock.getQuote().getVolume().doubleValue());
		stock.setDate(RealStock.getQuote().getLastTradeTime().getTime());
		stock.setCompany(company);
		
		return stock;
	}

	/*public List<Stock> getStockHistory(){
		
	}*/
	
	//Company service methods
	@Override
	public Company getRealCompanyInformation(String symbol) throws UnsupportedEncodingException, IOException, JSONException {
		
		URL url = new URL("https://financialmodelingprep.com/api/v3/company/profile/" + symbol);
		String jsonString = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		for (String line; (line = reader.readLine()) != null;) {
		    	jsonString = jsonString + "\n"+line;		    
		}
		    //System.out.println(jsonString);
				
		Company company = new Company();
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONObject newJsonObject = (JSONObject) jsonObject.get("profile");
		 for (Iterator iterator = newJsonObject.keys(); iterator.hasNext();) {
			    Object cle = iterator.next();
			    Object val = newJsonObject.get(String.valueOf(cle));
			    //System.out.println("key=" + cle + ", value=" + val);
			    company.setSymbol(symbol);
			    if(String.valueOf(cle).contains("companyName"))
			    	company.setName((String) val);
			    if(String.valueOf(cle).contains("sector"))
			    	company.setSector((String)val);
			    if(String.valueOf(cle).contains("industry"))
			    	company.setIndustry((String)val);
			  }
		return company;
	}
	
	@Override
	public int addCompany(Company company) {
		
		em.persist(company);
		System.out.println("Company : id = " + company.getId_company() + " has been added!");
		return company.getId_company();
	}
	
	@Override
	public void deleteCompany(int idCompany) {
		
		em.remove(em.find(Company.class, idCompany));	
		System.out.println("idCompany : id = " + idCompany + " has been deleted!");
		
	}
}

	
