package at.jku.ce.brokerplatform.model.bl;

import at.jku.ce.stockexchange.service.*;

public class BrokerPlatformService {
	
	private static final String MIC = "L1CE";
	private static final String STOCKEXCHANGENAME = "Gruppe L1 Börse";
	private StockExchange stockExchange;

	BrokerPlatformService(){
		ObjectFactory factory = new ObjectFactory();
		stockExchange.setMic(MIC);
		stockExchange.setName(STOCKEXCHANGENAME);
		
	}

}
