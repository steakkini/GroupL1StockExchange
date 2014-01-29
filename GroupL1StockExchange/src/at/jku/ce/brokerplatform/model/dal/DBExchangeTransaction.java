package at.jku.ce.brokerplatform.model.dal;

import java.sql.*;
import java.util.List;

import at.jku.ce.brokerplatform.model.ExchangeDB;
import at.jku.ce.stockexchange.service.Exchange;

/**
 * This class manages the DB transactions to the Exchange table.
 *
 */
public class DBExchangeTransaction {
	
	/**
	 * This method inserts 1 new row in the Exchange table.
	 * 
	 * @param exchange Object which should be saved.
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing or something went wrong.
	 */
	public int insertEntry(Exchange exchange){
		ExchangeDB exchangeDB;
		Connection con = null;
		Statement stmt = null;
		String sqlStmt;
		int executeUpdateStatus = 0;
		
		try{
			exchangeDB = createDBExchange(exchange);
			con = this.getDBConnection();
			sqlStmt = "insert into Exchange values(transaction_ID, mic, stockexchange_name, isin, stock_name, " 
					+ "stock_currency, stock_price, order, execution, exchange_date )"
					+ "values(" +  getTransactionId() + ", '" + exchangeDB.getMic()  + "', '"
					+ exchangeDB.getStockexchange_name()  + "', '" + exchangeDB.getIsin() + "', '"
					+ exchangeDB.getStock_name() + "', '" + exchangeDB.getStock_currency() + "', '"
					+ exchangeDB.getStock_price() + "', '" + exchangeDB.getOrder() + "', '"
					+ exchangeDB.getExecution() + "', '" + exchangeDB.getExchange_date() + "');";
			
			stmt = con.createStatement();
			executeUpdateStatus = stmt.executeUpdate(sqlStmt);
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if (stmt != null){
					stmt.close();
				}
				if (con != null){
					con.close();
				}			
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return executeUpdateStatus;
	}
	
	
	/**
	 * This method gets the table entries of 
	 * 
	 * @return A list of the ExchangeDB, generated through the database entries.
	 */
	public List<ExchangeDB> getEntries(){
		return null;
	}
	

	/**
	 * Returns a transaction ID
	 * @return Double A proper transaction ID
	 * @throws SQLException
	 */
	private Double getTransactionId() throws SQLException{
		return getDBConnection().createStatement().executeQuery("select max(transaction_ID) from Exchange").getDouble(1) + 1;
	}
		
	/**
	 * This method transforms an exchange object to a database exchange object.
	 * @param exchange object
	 * @return exchange object for database
	 */	
	private ExchangeDB createDBExchange(Exchange exchange)
	{
		ExchangeDB dbExchange = new ExchangeDB(exchange.getStockExchange().getMic(),
											   exchange.getStockExchange().getName(),
											   exchange.getStock().getIsin(),
											   exchange.getStock().getName(),
											   exchange.getStock().getCurrency(),
											   exchange.getStock().getPrice(),
											   exchange.getOrder(),
											   exchange.getExecution()
								);
		return dbExchange;
	}
	
	/**
	 * This method returns a proper connection object to the database.
	 * 
	 * @return Connection object
	 * @throws SQLException
	 */
	private Connection getDBConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://140.78.73.67:3306/stockexchangeDB", "ceue", "cestock2013");
	}

}
