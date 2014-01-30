package at.jku.ce.brokerplatform.model.bl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.jku.ce.stockexchange.service.Stock;

public class CreateStockServiceTest {

	private CreateStockService cs;
    private List<Stock> stockList;

	
	@Before
	public void setUp() throws Exception {
		cs = new CreateStockService();
	}

	@Test
	public void test() {
		assertTrue(cs.createStock("Testaktie", "EUR", 10, 10.0, "L1LUI1"));
		//cs.createStock("Testaktie", "EUR", 10, 10.0, "L1LUI1");
		//cs.createStock("Testaktie2", "EUR", 10, 10.0, "L1LUI2");

		//System.out.println("SURTEST1");

		stockList = BrokerPlatformService.getInstance().getStockList();	
		
		for(Stock s : stockList)
		{
			System.out.println(s.getName());
		}
	}

}
