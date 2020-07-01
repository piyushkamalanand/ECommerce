<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>

<h1>Payment </h1>
<h2> Total Amount = Rs.<%= (int) request.getAttribute("totalPrice") %> </h2>

<form action="Payment">
			
			Enter Your Card Number
			<input type="text" name="CardNumber"><br><br>
			
			Enter Account Holder's Name
			<input type="text" name="Name"><br><br>
			
			<input type="submit" value="PAY">		
		
		</form>

<h3><a href="ContinueShopping">Continue Shopping</a> </h3>


</body>
</html>