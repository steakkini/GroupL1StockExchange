package at.jku.ce.brokerplatform.view;

import javax.servlet.ServletRequest;

public interface ICreateStockView extends StockViewable {
	public String getCurrency();
	public void setCurrency(String currency);
	public int getStockAmount();
	public void setStockAmount(int amount);
	public double getStockPrice();
	public void setStockPrice(double price);
	public String getIsin();
	public void setIsin(String isin);
}
