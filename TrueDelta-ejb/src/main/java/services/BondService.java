package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.ws.rs.client.Client;
/*import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
*/
import entities.Bond;
//import entities.BondType;
//import entities.MarketType;
import interfaces.BondServiceLocal;
import interfaces.BondServiceRemote;
//import java.awt.List;
//package org.o7planning.apachepoiexcel.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
 
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;





@Stateful
@LocalBean
public class BondService implements BondServiceLocal, BondServiceRemote {
	
	@PersistenceContext(unitName="TrueDelta-ejb")
	EntityManager em;
	@Override
	public int addBond(Bond bond) {		 
		em.persist(bond);
		return bond.getId_bond();
	}

	@Override
	public void deleteBond(int id) {		
		em.remove(em.find(Bond.class, id));	
	}
	
	@Override
	public double CoupnCalcul(Bond bond , int t) {	
		double c=0;
		if(t<bond.getMaturity()) {
		 c= (bond.getNominal_value()*bond.getNominal_rate());
		}
	
		else
		{ c=bond.getNominal_value()+bond.getNominal_value()*bond.getNominal_rate();}
		
		return c;
		
				
	}

	@Override
	/* Le taux de rendement actuariel tient compte non seulement des coupons
	mais également des gains (pertes) en capital que l’investisseur peut réaliser
	en portant le titre jusqu’à l’échéance. Cette mesure tient compte de la série
	temporelle des paiements.*/
	public double actuarialRateOfReturnBond(Bond bond, double coursBoursier) {
		double p0=0;
		double res=0;
			
		for (int t = 0; t < bond.getMaturity()+1; t++) {
			res=CoupnCalcul(bond, t)*Math.pow((1-coursBoursier), -t);
			p0=p0+res;
			
		}
		return p0;
		
	}

	@Override
	public Bond findBondById(int id) {
		
		Bond bond=em.find(Bond.class, id);
		return bond;
	}

	@Override
	public List findAllBonds() {
		
		//List bonds= (List) em.createQuery("select b from Bond b",Bond.class).getResultList();
		List <Bond> bonds=em.createQuery("select b from Bond b",Bond.class).getResultList();
		
		return bonds;
	}

	@Override
	public void updateBond(int IdBond, String creditor) {
		
		Bond bond = em.find(Bond.class, IdBond);
		bond.setBond_creditor(creditor);
		
	}

	/*@Override
	public double BondYieldCalcul(Bond bond) {
		
		double p=0;
		double cf=bond.getNominal_rate()/2;
		if(bond.getMarket_type()==MarketType.primary_market) {
		for (int i = 1; i < bond.getBond_nsemesters()-1; i++) {
			p=cf/Math.pow((1+(bond.getBond_maturity_yield()/200)),i);
			
		}
		p=p+((cf+100)/Math.pow((1+(bond.getBond_maturity_yield()/200)),bond.getBond_nsemesters()));
		}
		else
			for (int i = 1; i < bond.getBond_nsemesters(); i++) {
				p=cf/Math.pow((1+(bond.getBond_maturity_yield()/200)),i);
				
			}
			
		
		return p;
	}*/
	//Le taux de rendement courant comme mesure de performance, ne tient pas compte de la valeur temporelle de l’argent, il ne tient compte que ducoupon et d’aucune autre source de revenu.
	

	@Override
	public double CurrentYieldBon(Bond bond) {
		
		double ipp=(100*bond.getIssue_price())/bond.getNominal_value();
		
		double cy= bond.getNominal_rate()/ipp;
		
		return cy;
	}
	
	public static class ReadExcelDemo {
		 
	    public static void main(String[] args) throws IOException {
	    	
	    	FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\rimah\\Desktop\\Bond.xls"));
	    	
	    	HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	    	HSSFSheet sheet = workbook.getSheetAt(0);
	    	Iterator<Row> rowIterator = sheet.iterator();
	    	 System.out.print(rowIterator);
	    	 while (rowIterator.hasNext()) {
	             Row row = rowIterator.next();
	             
	             Iterator<Cell> cellIterator = row.cellIterator();
	             
	             System.out.println(cellIterator);
	             while (cellIterator.hasNext()) {
	                 Cell cell = cellIterator.next();
	  
	                 // Change to getCellType() if using POI 4.x
	                 CellType cellType = cell.getCellTypeEnum();
	  
	                 switch (cellType) {
	                 case _NONE:
	                     System.out.print("");
	                     System.out.print("\t");
	                     break;
	                 case BOOLEAN:
	                     System.out.print(cell.getBooleanCellValue());
	                     System.out.print("\t");
	                     break;
	                 case BLANK:
	                     System.out.print("");
	                     System.out.print("\t");
	                     break;
	                 case FORMULA:
	                     // Formula
	                     System.out.print(cell.getCellFormula());
	                     System.out.print("\t");
	                      
	                     FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	                     // Print out value evaluated by formula
	                     System.out.print(evaluator.evaluate(cell).getNumberValue());
	                     break;
	                 case NUMERIC:
	                     System.out.print(cell.getNumericCellValue());
	                     System.out.print("\t");
	                     break;
	                 case STRING:
	                     System.out.print(cell.getStringCellValue());
	                     System.out.print("\t");
	                     break;
	                 case ERROR:
	                     System.out.print("!");
	                     System.out.print("\t");
	                     break;
	                 }
	  
	             }
	             System.out.println("");
	             
	    	 }
	    	 
	 
	    }
	}

	@Override
	public String findBondByCreditor(String creditor) {
		// TODO Auto-generated method stub
		Bond bond=em.createQuery("select c ftom Bond where c. bond_creditor=creditor",Bond.class).getSingleResult();
		
		return bond.getBond_creditor();
	}
	
	//La duration apparait comme une durée de vie moyenne actualisée de tous les flux (intérêt et capital).
	@Override
	public double DurationClcul(Bond bond, double r) {
		
		double d=0;
		double cf=0;
		double res=0;
		for (int t = 1; t < bond.getBond_maturity_yield()+1;t++) {
			cf=CoupnCalcul(bond, t);
			res=(t*cf*Math.pow((1-r), -t))/actuarialRateOfReturnBond(bond, r);
			d=d+res;
		}
		
		return d;
	}
	
	//La sensibilité du taux d’une obligation mesure le pourcentage de variation du prix d’une obligation suite à une variation donnee du taux d’intérêt.

	@Override
	public double SensibilityCalcul(Bond bond, double r) {
		
		double s=0;
		s=-DurationClcul(bond, r)*Math.pow((1-r), -1);
		
		return s;
		
	}

	/*@Override
	public void Consomation() {
		

		Client bond=ClientBuilder.newClient();
		WebTarget  target=bond.target("http://localhost:8081/RestActivator/Bond");
		WebTarget b=target.path("ok");
		Response rep=b.request().get();
		String res=rep.readEntity(String.class);
		System.out.println(res);
		rep.close();
		
	}*/

	@Override
	public double ScoringBnd(Bond bond) {
		
		double s=0;
		
		if(bond.getNominal_value()>100000)
		{      s+=10;      }
		if(bond.getNominal_rate()>5)
		{		s+=10;			}
		if(bond.getNominal_value()>bond.getIssue_price())
		{		s+=20;			}
		if(DurationClcul(bond, 0.5)>3)
		{		s+=20;			}
		if(actuarialRateOfReturnBond(bond, 0.5)>bond.getNominal_value())
		{		s+=50;				}
		
		return s;
	}

	@Override
	public String ClassificationBond(Bond bond) {
		
		String c="";
		
		if(ScoringBnd(bond)>80)
			c="good investment";
		else
			c="not a good investment";
		
		return c;
	}
	
	

}
