package tn.esprit.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.*;


import org.primefaces.model.chart.PieChartModel;

import services.ComplainService;




@ManagedBean(name= "ChartComplainBean")
@RequestScoped
public class ChartComplainBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @EJB
	    ComplainService complain;
	 
	
    private PieChartModel pieChartModel;
  
     @PostConstruct
    public void init() {
       
            
       
    }
   
	
	public PieChartModel getPieChartModel() {
		 pieChartModel = new PieChartModel();
	
         
         pieChartModel.set("Opened",complain.NbComplaintByState("Opened"));
         pieChartModel.set("inprogress",complain.NbComplaintByState("inprogress"));
         pieChartModel.set("treated",complain.NbComplaintByState("treated"));
         pieChartModel.setShowDataLabels(true);

         
		
		return pieChartModel;
	}
	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}
  
  
    
    
    
    
	}
    
