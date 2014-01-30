package at.jku.ce.brokerplatform.presenter;

import java.util.List;

import at.jku.ce.brokerplatform.model.bl.DeleteStockService;
import at.jku.ce.brokerplatform.view.IDeleteStockView;

/**
 * Presenter class for the deleteStock Service
 *
 */
public class DeleteStockPresenter {
	private IDeleteStockView view;
	private DeleteStockService service;
	
	public DeleteStockPresenter(IDeleteStockView view)
	{
		this.view = view;
		service = new DeleteStockService();
	}
	
	public void deleteStock()
	{
		service = new DeleteStockService();
		//System.out.print(listAvailableStocks());
		//view.setStockList(listAvailableStocks());
		String stockName = view.getStockName();
		
		if(service.deleteStock(stockName)){
			view.displaySuccess("Succesfully deleted.");
		}
		else{
			view.displaySuccess("Problem occured.");
		}
		
	}
	
	public void listStocks(){
		view.setStockList(listAvailableStocks());
	}
	
	public List<String> listAvailableStocks(){
		return service.listAvailableStocks();
	}

}
