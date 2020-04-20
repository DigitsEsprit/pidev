package interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entities.Company;
import entities.Stock;

@Local
public interface IStockServiceLocal {

	public int addStock(Stock stock);
	public List<Stock> findAllStocks();
	public void deleteStock(int idStock);
	public void updateStockByDate(Stock stock, Date newDate);
	
	public Stock findStockById(int idStock);
	
	public Stock getRealStockInformation(Company company) throws IOException;
	
	public int addCompany(Company company);
	public Company getRealCompanyInformation(String symbol) throws UnsupportedEncodingException, IOException;
	public void deleteCompany(int idCompany);
}
