package com.Wiley;
import java.sql.*;
public class ProductDAO {

	private Connection con;
	
	private PreparedStatement pst;
	
	private ResultSet rs;
	
	public ProductDAO() {
		
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				this.con = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
				
			}
			catch(Exception e) {
				
				System.out.println(e);
				
			}
	}
	
	public void register(String username,String password,String email)
	{
		try
		{
			pst = con.prepareStatement("INSERT into customer(UserName,Password,EmailId) values(?,?,?) ");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
			int i = pst.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public int VerifyingUser(String name,String password)
	{
		try
		{
			pst = con.prepareStatement("SELECT * from customer where Username = ? and password = ?");
			pst.setString(1, name);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	
	public void changePassword(String email,String newPassword)
	{
		try
		{
			
			pst = con.prepareStatement("UPDATE customer SET Password  = ? WHERE EmailId = ?");
			pst.setString(1, newPassword);
			pst.setString(2, email);
			int rowAffected = pst.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
public int addToCart(int customerid,int productid){
		  
		  
	
		try {
			
			PreparedStatement pstt = con.prepareStatement("SELECT ProductName,Price from product where ProductId = "+productid);
			ResultSet rst = pstt.executeQuery();
			
			
			String product="";
			int price=0;
			if(rst.next()) {
				product = rst.getString(1);
				price = rst.getInt(2);
			}
			
			pst = con.prepareStatement("INSERT into cart(customerid,product,price) values(?,?,?) ");
			pst.setInt(1, customerid);
			pst.setString(2, product);
			pst.setInt(3, price);
			int i = pst.executeUpdate();	
			
		}
		catch(Exception e) {
			
			System.out.println(e);
		}
		
		
		return 0;
		
	}

public void removeFromCart(int customerid,int productid)
{
	try
	{
		
		pst = con.prepareStatement("DELETE from cart where customerid = ? and ProductId = ?");
		pst.setInt(1, customerid);
		pst.setInt(2, productid);
		int rowAffected = pst.executeUpdate();
	}
	catch(Exception e)
	{
		
	}
}


public int totalPrice(int customerid)
{
	int sum=0;
	try
	{
		
		//PreparedStatement st = con.prepareStatement("SELECT sum (Price) from cart where customerid = "+customerid+" GROUP BY customerid");
		Statement st = con.createStatement();
		rs = st.executeQuery("SELECT SUM(price) FROM cart where customerid = "+customerid);
	    while (rs.next()) {
	      int c = rs.getInt(1);
	      sum = sum + c;
	    }
	}
	catch(Exception e)
	{
		
	}
	
	return sum;
}

	
public int findCartSize(int customerid){
		
		int size = 0;
		  
		  
		try {
			
			this.pst = this.con.prepareStatement("SELECT count(*) from cart WHERE customerid = "+customerid);
			
			this.rs = this.pst.executeQuery();
			
			if(rs.next()) {
				size = rs.getInt(1);
			}	
			
		}
		catch(Exception e) {
			
			System.out.println(e);
		}
		
		
		return size;
		
	}

public void deleteRecords(int customerid)
{
	try
	{
		
		pst = con.prepareStatement("DELETE from cart where customerid = "+customerid);
		int rowAffected = pst.executeUpdate();
	}
	catch(Exception e)
	{
		
	}
}
	
}
