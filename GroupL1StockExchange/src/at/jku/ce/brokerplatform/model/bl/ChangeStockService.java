package at.jku.ce.brokerplatform.model.bl;


import java.util.ArrayList;
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
	
	public ChangeStockService(){
		platform = BrokerPlatformService.getInstance();
		stockList = platform.getStockList();	
	}
	
	public String changeStock(String stockName, String currency, int stockAmount, double stockPrice, String isin){
		
		StringBuffer returnValue = new StringBuffer();
		returnValue.append("Unsuccessful.");
		
		for(int i=0;i<stockList.size();i++){
			returnValue = new StringBuffer();
			Stock stock = stockList.get(i);
			
			if(stock.getName().equals(stockName)){
				
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
				
				if(!patternCoverage(regex,String.valueOf(stockAmount)) && String.valueOf(stockAmount) != null ){
					returnValue.append("Check the availability.\n");
				}
				else
				{
					stock.setAvailability(stockAmount);
				}
				
				regex = "C=(\\d+\\.\\d+)";
		
				if(!patternCoverage(regex,String.valueOf(stockPrice)) && String.valueOf(stockPrice) != null){
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
	/*
	public List<String> listAvailableStocks() {
		List<String> list = new ArrayList<String>();
		
		for(Stock stock : stockList){
			list.add(stock.getName());
			//System.out.println(stock.getName());
		}
		
		return list;
	}
	*/
	private boolean patternCoverage(String regex, String compareString)
	{
		try{
			
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
		catch(NullPointerException e)
		{
			return false;
		}
	}
}
