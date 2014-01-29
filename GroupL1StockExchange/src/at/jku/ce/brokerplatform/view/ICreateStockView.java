package at.jku.ce.brokerplatform.view;

import javax.servlet.ServletRequest;

public interface ICreateStockView extends StockViewable {
   // public final ServletRequest request;  

    public void displayResult(int x, int y, int result);


}
