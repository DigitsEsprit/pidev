package interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import org.json.JSONException;

import entities.Company;
import entities.Stock;

@Local
public interface IStockServiceLocal {

	public int addStock(Stock stock);
	public List<Stock> findAllStocks();
	public void deleteStock(int idStock);
	void updateStockByDate(Stock stock, Date newDate);
	
	public Stock findStockById(int idStock);
	Stock getRealStockInformation(Company company) throws IOException;
	Company getRealCompanyInformation(String symbol) throws UnsupportedEncodingException, IOException, JSONException;
	int addCompany(Company company);
	void deleteCompany(int idCompany);
}