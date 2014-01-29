package at.jku.ce.brokerplatform.model.dal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is will represent a POJO for the DB Exchange table.
 *
 */

public class ExchangeDB {
	private double transaction_ID;
	private String mic;
	private String stockexchange_name;
	private String isin;
	private String stock_name;
	private String stock_currency;
	private double stock_price;
	private int order;
	private int execution;
	private Date exchange_date;
	
	
	public ExchangeDB(){
		
	}
	
	public ExchangeDB(String mic, String stockexchangeName, String isin, String stockName,
					  String stockCurrency, double stockPrice, int order, int execution){
		this.setMic(mic);
		this.setStockexchange_name(stockexchangeName);
		this.setIsin(isin);
		this.setStock_name(stockName);
		this.setStock_currency(stockCurrency);
		this.setStock_price(stockPrice);
		this.setStockexchange_name(stockexchangeName);
		this.setStockexchange_name(stockexchangeName);
		this.setStockexchange_name(stockexchangeName);
		this.setExchange_date(new Date());
	}
	
	public double getTransaction_ID() {
		return transaction_ID;
	}
	public void setTransaction_ID(double transaction_ID) {
		this.transaction_ID = transaction_ID;
	}
	public String getMic() {
		return mic;
	}
	public void setMic(String mic) {
		this.mic = mic;
	}
	public String getStockexchange_name() {
		return stockexchange_name;
	}
	public void setStockexchange_name(String stockexchange_name) {
		this.stockexchange_name = stockexchange_name;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public double getStock_price() {
		return stock_price;
	}
	public void setStock_price(double stock_price) {
		this.stock_price = stock_price;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getExecution() {
		return execution;
	}
	public void setExecution(int execution) {
		this.execution = execution;
	}
	public String getExchange_date() {
		DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		return df.format(exchange_date);
	}
	public void setExchange_date(Date exchange_date2) {
		this.exchange_date = exchange_date2;
	}

	public String getStock_currency() {
		return stock_currency;
	}

	public void setStock_currency(String stock_currency) {
		this.stock_currency = stock_currency;
	}


}
