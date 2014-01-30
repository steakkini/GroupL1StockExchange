package at.jku.ce.brokerplatform.model.bl;

import java.util.ArrayList;
import java.util.List;

import at.jku.ce.stockexchange.service.Stock;

/**
 * Presenter class for the deleteStock Service
 *
 */
public class DeleteStockService {
	private BrokerPlatformService platform;
	private List<Stock> stockList;
	
	public DeleteStockService(){
		stockList = platform.getInstance().getStockList();	
	}
	public boolean deleteStock(String stockName){
		boolean returnValue = false;
		
		for(Stock stock : stockList){
			if(stock.getName() == stockName){
				stockList.remove(stock);
				returnValue = true;
			}
		}
		
		return returnValue;
	}

	public List<String> listAvailableStocks() {
		List<String> list = new ArrayList<String>();
		
		for(Stock stock : stockList){
			list.add(stock.getName());
		}
		
		return list;
	}

}
