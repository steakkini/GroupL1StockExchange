<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="at.jku.ce.brokerplatform.presenter.*"%>
<%@ page import="at.jku.ce.brokerplatform.view.IDeleteStockView"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Stock</title>
</head>
<body>
<form method ="post">
	 <%  
	     LoginPresenter presenter = new DeleteStockPresenter(new DeleteStockView(request)); 
	 	 presenter.listStocks();
	     if (request.getMethod().equalsIgnoreCase("post"))  
	         presenter.deleteStock();  
	 %>  
		<p>Please select one the stock which should be deleted:</p>
		<p>
		<%	String stockName;
		    for (Iterator<String> i = stockList.iterator(); i.hasNext();) {
		    	stockName = i.next();
				out.println("<input type='radio' name='stockName' value='"+stockName+"'/>"+stockName+"<br/>");
		}
		%>
		</p>
        <% if (successLabel != null) { %>  
            <p class="result"><%= successLabel %></p>  
        <% } %>  
		<input type="submit" value="delete">		
	</form>
	Go back to the <a href="administratePlatform.jsp">Administration Platform</a>
</body>
</html>

 <%! 
  	 //Inner class, which has the view role to provide the MVP pattern.
	 protected String nameTextField;    
 	 protected String successLabel; 
 	 protected List<String> stockList;

     private class DeleteStockView implements IDeleteStockView {  
         private ServletRequest request;  

         public DeleteStockView(ServletRequest srequest){
        	 request = srequest;
         }
         public String getStockName() {  
             return request.getParameter("stockName");  
         }  
         public void setStockName(String value) {  
        	 nameTextField = String.valueOf(value);  
         }  
         
         public void setStockList(List<String> list)
         {
        	stockList = list;
         }
 
         public void displaySuccess(String message) {  
             successLabel = message;  
         }  
     }  
 %> 