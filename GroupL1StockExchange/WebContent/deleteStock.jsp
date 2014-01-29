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
	 <%  
	     DeleteStockPresenter presenter = new DeleteStockPresenter(new DeleteStockView(request));  
	     if (request.getMethod().equalsIgnoreCase("post"))  
	         presenter.deleteStock();  
	 %>  
	<form action="deleteStock" method="post">
		<p>Please select one of the available stock exchanges:</p>
		<p>
        <% if (successLabel != null) { %>  
             <p class="result"><%= successLabel %></p>  
         <% } %>  
		</p>
		<input type="submit" value="get stocks">		
	</form>
</body>
</html>

 <%! 
  	 //Inner class, which plays the view role to provide the MVP pattern.
	 private String nameTextField;    
	 private String successLabel;  

     private class DeleteStockView implements IDeleteStockView {  
         private ServletRequest request;  

         public DeleteStockView(ServletRequest srequest){
        	 request = srequest;
         }
         public String getStockName() {  
             return request.getParameter("x");  
         }  
         public void setStockName(String value) {  
        	 nameTextField = String.valueOf(value);  
         }  
 
         public void displaySuccess(String message) {  
             successLabel = message;  
         }  
     }  
 %> 