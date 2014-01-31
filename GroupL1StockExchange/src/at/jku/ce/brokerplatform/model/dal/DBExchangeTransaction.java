package at.jku.ce.brokerplatform.model.dal;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import at.jku.ce.brokerplatform.model.bl.BrokerPlatformService;
import at.jku.ce.stockexchange.service.Exchange;
import at.jku.ce.stockexchange.service.ObjectFactory;

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
		ResultSet rs = null;
		String sqlStmt;
		int executeUpdateStatus = 0;
		
		try{
			exchangeDB = createDBExchange(exchange);
			con = this.getDBConnection();
			
			sqlStmt = "select max(transaction_ID) from Exchange";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStmt);
			rs.first();
			Double transactionId = rs.getDouble(1) + 1;
			rs.close();
			
			sqlStmt = "insert into Exchange " //values(transaction_ID, mic, stockexchange_name, isin, stock_name, stock_currency, stock_price, order, execution, exchange_date )"
					+ "values (" +  transactionId + ", '" + exchangeDB.getMic()  + "', '"
					+ exchangeDB.getStockexchange_name()  + "', '" + exchangeDB.getIsin() + "', '"
					+ exchangeDB.getStock_name() + "', '" + exchangeDB.getStock_currency() + "', '"
					+ exchangeDB.getStock_price() + "', '" + exchangeDB.getOrder() + "', '"
					+ exchangeDB.getExecution() + "', '" + exchangeDB.getExchange_date() + "');";
			
			System.out.println(sqlStmt);
			
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
	public List<Exchange> getEntries(){
		List<Exchange> exchangeList = new ArrayList<Exchange>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ObjectFactory factory = new ObjectFactory();
		Exchange exchange;
		java.util.Date date;
		GregorianCalendar calendar = new GregorianCalendar();
		String sqlStmt;
		//int executeUpdateStatus = 0;
		BrokerPlatformService platform = BrokerPlatformService.getInstance();
		
		try{
			con = this.getDBConnection();
			sqlStmt = "select * from Exchange where MIC='"+platform.getMic()+"'"; 
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlStmt);
			
			while(rs.next()){
				exchange = factory.createExchange();
				exchange.setStock(factory.createStock());
				exchange.setStockExchange(factory.createStockExchange());
				
				exchange.setExecution(rs.getInt("execution"));
				exchange.setOrder(rs.getInt("order"));
				
				exchange.getStock().setName(rs.getString("stock_name"));
				exchange.getStock().setIsin(rs.getString("isin"));
				exchange.getStock().setCurrency(rs.getString("stock_currency"));
				exchange.getStock().setPrice(rs.getDouble("stock_price"));
				
				exchange.getStockExchange().setMic(rs.getString("mic"));
				exchange.getStockExchange().setName(rs.getString("stockexchange_name"));
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = formatter.parse(rs.getString("exchange_date"));
				calendar.setTime(date);
				exchange.setExchangeDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar));
				
				exchangeList.add(exchange);
			}

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
	return exchangeList;
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
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:mysql://140.78.73.67:3306/stockexchangeDB", "ceue", "cestock2013");
	}

}
