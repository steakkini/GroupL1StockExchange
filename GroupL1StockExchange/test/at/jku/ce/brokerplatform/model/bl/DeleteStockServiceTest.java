package at.jku.ce.brokerplatform.model.bl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeleteStockServiceTest {
	DeleteStockService ds;
	@Before
	public void setUp() throws Exception {
		ds = new DeleteStockService();
	}

	@Test
	public void testDeleteStock() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAvailableStocks() {
		for(String s:ds.listAvailableStocks()){
			System.out.println(s);
			assertEquals(s,"TestAktie");
		}
	}

}
