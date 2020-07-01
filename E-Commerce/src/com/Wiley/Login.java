package com.Wiley;

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
			String un = request.getParameter("username");
		
			String pw = request.getParameter("password");
			
			HttpSession session = request.getSession(true);
			
			if(request.getParameter("submit")!=null)
			{
				
				ProductDAO pd = new ProductDAO();
				int res = pd.VerifyingUser(un,pw);
				if(res>0)
				{
					
					session.setAttribute("customerid",res);
					request.setAttribute("cartSize", pd.findCartSize(res));
					RequestDispatcher rd=request.getRequestDispatcher("catalogue.jsp");  
			        rd.forward(request,response);
				}
				else
				{
						RequestDispatcher rd=request.getRequestDispatcher("invalidLogin.jsp");  
						rd.forward(request,response);
				}
			}
			
			else if(request.getParameter("forpass")!=null)
			{
				RequestDispatcher rd=request.getRequestDispatcher("changePassword.jsp");  
		        rd.forward(request,response);
			}	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
