package services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Stock;
import interfaces.IStockServiceRemote;

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
		
		List<Stock> stocks = em.createQuery("select s from stocks s", Stock.class).getResultList();
		return stocks;
	}

	@Override
	public void deleteStock(int idStock) {
		
		em.remove(em.find(Stock.class, idStock));	
		System.out.println("Stock : id = " + idStock + " has been deleted!");
	}

	@Override
	public void updateStock(Stock stock) {
		// TODO Auto-generated method stub
		
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
	
	

}
