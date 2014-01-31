package at.jku.ce.brokerplatform.model.bl;

import java.net.ConnectException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.jku.ce.juddi.UddiManager;
import at.jku.ce.stockexchange.service.*;

public class BrokerPlatformService {
	private static final String MIC = "L1CE";
	private static final String STOCKEXCHANGENAME = "Gruppe L1 Börse";
	private static final String BUSINESSNAME = "L1";
	private static  BrokerPlatformService instance;// = new BrokerPlatformService();
	private StockExchange stockExchange;
	private List<Stock> stockList;

	private BrokerPlatformService(){

	}
	
	public static BrokerPlatformService getInstance(){
		if(instance == null){
			instance = new BrokerPlatformService();
			instance.init();
		}
		return instance;
	}
	
	private void init()
	{
		ObjectFactory factory = new ObjectFactory();
		stockExchange = factory.createStockExchange();
		stockExchange.setMic(MIC);
		stockExchange.setName(STOCKEXCHANGENAME);
		
		if (registerDirectoryService()){
			System.out.println("UDDI Registrierung erfolgreich.");
		}
		else{
			System.out.println("UDDI Registrierung nicht erfolgreich.");
		}  

		createTestStock();
	}

	public boolean registerDirectoryService(){
			UddiManager manager = UddiManager.getInstance();

			singnOutOfDirectoryService();
			try {
				//Register Group 
				String serviceKey = manager.publish(BUSINESSNAME, "Stock Exchange Service", "http://140.78.73.101:8080/GroupL1StockExchange/services/ExchangeServicePort?wsdl");
				System.out.print(serviceKey);  
			} catch (ConnectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			return true;
	}
	
	public void singnOutOfDirectoryService(){
		UddiManager manager = UddiManager.getInstance();
		String result = manager.deletePublishedBusinessFromUDDI(BUSINESSNAME);
		System.out.println(result);
	}
	
	public String getMic(){
		return MIC;
	}
	
	public String getBusinessName(){
		return BUSINESSNAME;
	}
	
	public String getStockExchangeName(){
		return STOCKEXCHANGENAME;
	}
	
	public StockExchange getStockExchange(){
		return stockExchange;
	}

	public List<Stock> getStockList() {
		return stockList;
	}
	
	public Stock getStockFromList(String name){
		for(int i=0;i<stockList.size();i++){
			Stock stock = stockList.get(i);
			
			if(stock.getName().equals(name)){
				return stock;
			}
		}
		return null;
	}

	private void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	
	private void createTestStock(){
		stockList = new ArrayList<Stock>();	
        Stock newStock = new Stock();
        newStock.setName("CEL11");
        newStock.setCurrency("EUR");
        newStock.setAvailability(5);
        newStock.setPrice(1.0);
        newStock.setIsin("L1ANF1");
        stockList.add(newStock);
        
       // System.out.println("SURcTS");
        
		boolean ret = new CreateStockService().createStock("CEL12", "EUR", 10, 10.0, "L1ANF2");
	}
}
