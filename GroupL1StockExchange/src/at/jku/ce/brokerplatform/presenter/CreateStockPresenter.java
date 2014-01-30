package at.jku.ce.brokerplatform.presenter;

import at.jku.ce.brokerplatform.model.bl.CreateStockService;
import at.jku.ce.brokerplatform.view.ICreateStockView;

/**
 * Presenter class for the createStock Service
 *
 */
public class CreateStockPresenter {
	private ICreateStockView view;
	private CreateStockService service;
	
	public CreateStockPresenter(ICreateStockView view)
	{
		this.view = view;
	}
	
	public void createStock()
	{
		String stockName = view.getStockName();
		String currency = view.getCurrency();
		int stockAmount = view.getStockAmount();
		double stockPrice = view.getStockPrice();
		String isin = view.getIsin();
		
		if(service.createStock(stockName,currency,stockAmount,stockPrice,isin)){
			view.displaySuccess("Succesfully added.");
		}
		else{
			view.displaySuccess("Problem occured.");
		}
		
	}
}
