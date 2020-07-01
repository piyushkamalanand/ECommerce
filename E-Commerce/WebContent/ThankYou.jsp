<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thank You!!</title>
</head>
<body>

<h1>Thank You For Shopping</h1>
<h2> You Paid = Rs.<%= (int) request.getAttribute("totalPrice") %> </h2>
<h3><a href="ContinueShopping">Continue Shopping</a> </h3>

</body>
</html>