package com.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	    int id=Integer.parseInt(request.getParameter("sid"));
		String name=request.getParameter("sname");
		String password=request.getParameter("spass");
		int age=Integer.parseInt(request.getParameter("sage"));
	    float fees=Float.parseFloat(request.getParameter("sfees"));
	    
	    Connection con = null;
	    PreparedStatement pst;
		ResultSet rst;
	   try 
		{ 
		   con=DbConnection.getconnection();
		    String sel="select * from studentdata where sid=?";
		    pst=con.prepareStatement(sel);
		    pst.setInt(1, id);
		    rst=pst.executeQuery();
		 if(!rst.next())
		 {
		    String reg="insert into studentdata values(?,?,?,?,?)";
			pst=con.prepareStatement(reg);
			pst.setInt(1,id );
			pst.setString(2, name);
			pst.setString(3, password);
			pst.setInt(4,age);
			pst.setFloat(5,fees);
			int i=pst.executeUpdate();
			if(i>0)
			{
				out.println("Registered Successfully");
			}
			else
			{
				out.println("Registered Successfully");
			}
		}else
		{
			out.println("Already Exists");
		}
      }
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

}
