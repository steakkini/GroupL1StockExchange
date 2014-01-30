<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="at.jku.ce.brokerplatform.presenter.ChangeStockPresenter"%>
<%@ page import="at.jku.ce.brokerplatform.view.IChangeStockView"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Stock</title>
</head>
<body>
<body>
<form method ="post">
	 <%  
	     ChangeStockPresenter presenter = new ChangeStockPresenter(new ChangeStockView(request));  
	     if (request.getMethod().equalsIgnoreCase("post"))  
	         presenter.changeStock();  
	 %>  
		<p>Please insert the fields for the stock:</p>
		<p>
		Name:<br>
		<input type="text" name="stockName" value=<%= nameTextField %>></input><br>
		Currency<br>
		<input type="text" name="currency" value=<%= currencyTextField %>></input><br>
		Availability:<br>
		<input type="text" name="stockAmount" value=<%= stockAmountTextField %>></input><br>
		Price:<br>
		<input type="text" name="stockPrice" value=<%= stockPriceTextField %>></input><br>
		Isin:<br>
		<input type="text" name="isin" value=<%= isinTextField %>></input><br>
		</p>
        <% if (successLabel != null) { %>  
            <p class="result"><%= successLabel %></p>  
        <% } %>  
		<input type="submit" value="change"><br>		
	Go back to the <a href="administratePlatform.jsp">Administration Platform</a>

	 <%! 
	  	 //Inner class, which has the view role to provide the MVP pattern.
		 protected String nameTextField;
		 protected String currencyTextField;    
		 protected String stockAmountTextField;    
		 protected String stockPriceTextField;    
		 protected String isinTextField;

	
	 	 protected String successLabel; 
	
	     private class ChangeStockView implements IChangeStockView {  
	         private ServletRequest request;  
	
	         public ChangeStockView(ServletRequest srequest){
	        	 request = srequest;
	         }
	         public String getStockName() {  
	             return request.getParameter("stockName");  
	         }  
	         public void setStockName(String value) {  
	        	 nameTextField = value; 
	         }  
	     	public String getCurrency(){
	            return request.getParameter("currency");  
	     	}
	    	public void setCurrency(String currency){
	    		currencyTextField = currency;
	    	}
	    	public int getStockAmount(){
	            return Integer.parseInt(request.getParameter("stockAmount")); 
	    	}
	    	public void setStockAmount(int amount){
	    		stockAmountTextField = String.valueOf(amount);  
	    	}
	    	public double getStockPrice(){
	            return Double.parseDouble(request.getParameter("stockPrice")); 
	    	}
	    	public void setStockPrice(double price){
	    		stockPriceTextField = String.valueOf(price);  
	    	}
	    	public String getIsin(){
	            return request.getParameter("isin"); 
	    	}
	    	public void setIsin(String isin){
	    		isinTextField = isin;
	    	}

	         public void displaySuccess(String message) {  
	             successLabel = message;  
	         }  
	     }  
	 %> 
 </form>
</body>
</body>
</html>