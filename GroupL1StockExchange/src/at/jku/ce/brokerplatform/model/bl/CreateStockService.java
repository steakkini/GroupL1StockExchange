package at.jku.ce.brokerplatform.model.bl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import at.jku.ce.stockexchange.service.Stock;

/**
 * Presenter class for the createStock Service
 *
 */
public class CreateStockService {
	private BrokerPlatformService platform;
	private List<Stock> stockList;
	
	public boolean createStock(String stockName, String currency, int stockAmount, double stockPrice, String isin){
		boolean returnValue = false;
		stockList = platform.getInstance().getStockList();	
		
		for(Stock stock : stockList){
			if(stock.getName() == stockName){
				returnValue = false;
			}
			else
			{
				Stock newStock = new Stock();
				
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(new Date());
				
		        newStock.setName(stockName);
		        newStock.setCurrency(currency);
		        newStock.setAvailability(stockAmount);
		        newStock.setPrice(stockPrice);
		        newStock.setIsin(isin);
		        try {
					newStock.setPublication(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
				} catch (DatatypeConfigurationException e) {
					newStock.setPublication(null);
					e.printStackTrace();
				}
				stockList.add(newStock);
				returnValue = true;
			}
		}
		
		return returnValue;
	}
}
