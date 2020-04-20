package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Stock;

@Remote
public interface IStockServiceRemote {

	public int addStock(Stock stock);
	public List<Stock> findAllStocks();
	public void deleteStock(int idStock);
	public void updateStock(Stock stock);
	
	public Stock findStockById(int idStock);
}
