
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.sql.DriverManager"%>
  <%@page import="java.sql.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%

try
{
	Class.forName("com.mysql.jdbc.Driver");
}
catch(Exception e)
{
	System.out.println(e);
}
Connection con = null;
Statement st = null;
ResultSet rst = null;
PreparedStatement pst = null;

%>
<!DOCTYPE html>
<html>
	<head>
                                <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Your Cart</title>
	</head>

	<body>
	<h3><a href="LogOut">LOGOUT</a></h3>
	<h1> You have <%=(int) request.getAttribute("cartSize") %> item (s)  Total Price = 
	<%= (int) request.getAttribute("totalPrice")%> </h1>
	<h2><a href="CheckOut">CheckOut</a> </h2>
<table border="1">	
					
		<td>Product </td>
		<td>Price</td>
		<td>Action</td>
		
<%
try{
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
st = con.createStatement();
pst = con.prepareStatement("SELECT ProductId,Product,Price from cart where customerid = ? ");

HttpSession sess=request.getSession(false);  
int c = (int) session.getAttribute("customerid");

pst.setInt(1, c);
rst = pst.executeQuery();

while(rst.next()){
%>

<tr>
<form action="RemoveCart" method="POST">
		  <input type="hidden" name="productId" value="<%=rst.getInt("ProductId")%>"/>
<td><%=rst.getString("Product") %></td>
<td><%=rst.getInt("Price") %></td>
<td> <input type="submit" value="Remove From Cart"/></td>
	      </form>
</tr>
<%
}
con.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
<h2><a href="ContinueShopping">Continue Shopping</a> </h2>
		</table>
	</body>
</html>