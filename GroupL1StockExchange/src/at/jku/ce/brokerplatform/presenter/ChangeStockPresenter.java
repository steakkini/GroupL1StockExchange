package at.jku.ce.brokerplatform.presenter;


import at.jku.ce.brokerplatform.model.bl.ChangeStockService;
import at.jku.ce.brokerplatform.view.IChangeStockView;

/**
 * Presenter class for the changeStock Service
 *
 */
public class ChangeStockPresenter {
	private IChangeStockView view;
	private ChangeStockService service;
	
	public ChangeStockPresenter(IChangeStockView view)
	{
		this.view = view;
		service = new ChangeStockService();
	}
	
	public void changeStock()
	{
		String displayMessage;
		String stockName = view.getStockName();
		String currency = view.getCurrency();
		int stockAmount = view.getStockAmount();
		double stockPrice = view.getStockPrice();
		String isin = view.getIsin();
		
		
		displayMessage = service.changeStock(stockName,currency,stockAmount,stockPrice,isin);
		view.displaySuccess(displayMessage);
	}
	

}
