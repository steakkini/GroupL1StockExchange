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
	private static final String STOCKEXCHANGENAME = "Gruppe L1 B�rse";
	private static final String BUSINESSNAME = "L1";
	private static final BrokerPlatformService instance = new BrokerPlatformService();
	private StockExchange stockExchange;
	private List<Stock> stockList;

	private BrokerPlatformService(){
		ObjectFactory factory = new ObjectFactory();
		stockExchange = factory.createStockExchange();
		stockExchange.setMic(MIC);
		stockExchange.setName(STOCKEXCHANGENAME);
		
		registerDirectoryService();
		setStockList(new ArrayList<Stock>());
	}
	
	public static BrokerPlatformService getInstance(){
		return instance;
	}

	public boolean registerDirectoryService(){
			UddiManager manager = UddiManager.getInstance();
			singnOutOfDirectoryService();
			try {
				//Register Group 
				String serviceKey = manager.publish(BUSINESSNAME, STOCKEXCHANGENAME, "http://140.78.73.101:8080/GroupL2StockExchange/services/ExchangeServicePort?wsdl");
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

	private void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
}
