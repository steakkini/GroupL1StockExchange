package at.jku.ce.brokerplatform.model.bl;

import at.jku.ce.stockexchange.service.*;

public class BrokerPlatformService {
	private static final String MIC = "L1CE";
	private static final String STOCKEXCHANGENAME = "Gruppe L1 Börse";
	private static BrokerPlatformService instance = null;
	private StockExchange stockExchange;

	private BrokerPlatformService(){
		ObjectFactory factory = new ObjectFactory();
		stockExchange.setMic(MIC);
		stockExchange.setName(STOCKEXCHANGENAME);
		
	}
	
	public static BrokerPlatformService getInstance(){
		if (instance == null){
			instance = new BrokerPlatformService();
		}
		return instance;
	}

}
