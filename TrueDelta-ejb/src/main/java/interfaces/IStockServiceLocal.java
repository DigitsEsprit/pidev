package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Stock;

@Local
public interface IStockServiceLocal {

	public int addStock(Stock stock);
	public List<Stock> findAllStocks();
	public void deleteStock(int idStock);
	public void updateStock(Stock stock);
	
	public Stock findStockById(int idStock);
	
}
