package at.jku.ce.brokerplatform.model.bl;


import java.util.List;
import java.util.regex.Pattern;

import at.jku.ce.stockexchange.service.Stock;

/**
 * Presenter class for the changeStock Service
 *
 */
public class ChangeStockService {
	private BrokerPlatformService platform;
	private List<Stock> stockList;
	
	public String changeStock(String stockName, String currency, int stockAmount, double stockPrice, String isin){
		StringBuffer returnValue = new StringBuffer();
		returnValue.append("Unsuccessful.");

		stockList = platform.getInstance().getStockList();	
		
		for(Stock stock : stockList){
			if(stock.getName() == stockName){
				
				returnValue = new StringBuffer();
				String regex = "^[a-zA-Z0-9]*$";
			
				//Alphanumerisch
				if(!patternCoverage(regex,stockName) && stockName != null){
					returnValue.append("Check the stockName.\n");
				}
				else
				{
					stock.setName(stockName);
				}
				if(!patternCoverage(regex,currency) && currency != null){
					returnValue.append("Check the currency.\n");
				}
				else
				{
					stock.setCurrency(currency);
				}
				if(!patternCoverage(regex,isin) && isin != null){
					returnValue.append("Check the ISIN.\n");
				}
				else
				{
					stock.setIsin(isin);
				}
				
				regex = "\\d+";
				
				if(!patternCoverage(regex,String.valueOf(stockAmount))){
					returnValue.append("Check the availability.\n");
				}
				else
				{
					stock.setAvailability(stockAmount);
				}
				
				regex = "C=(\\d+\\.\\d+)";
		
				if(!patternCoverage(regex,String.valueOf(stockPrice))){
					returnValue.append("Check the price.\n");
				}
				else
				{
					stock.setPrice(stockPrice);
				}
				
				returnValue.append("Succesfull.\n");
			}
		}
		return returnValue.toString();
	}
	
	private boolean patternCoverage(String regex, String compareString)
	{
		Pattern pattern;
		pattern = Pattern.compile(regex);
		if(pattern.matcher(compareString).matches())
		{
			return true;
		}
		else
		{
	        return false;
		}
	}
}
