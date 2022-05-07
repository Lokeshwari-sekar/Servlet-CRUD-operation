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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delete() {
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
		Connection conn=null;
		PreparedStatement pst;
		ResultSet rst;
		try
		{
			conn=DbConnection.getconnection();
			String sel="select * from studentdata where sid=?";
			pst=conn.prepareStatement(sel);
			pst.setInt(1, id);
			rst=pst.executeQuery();
			if(rst.next())
			{
				String del="delete from studentdata where sid=?";
				pst=conn.prepareStatement(del);
				pst.setInt(1, id);
				int i=pst.executeUpdate();
				if(i>0)
				{
					out.println("Deleted Successfully");
				}
				else
				{
					out.println("Not Deleted");	
				}
			}
			else
			{
				out.println("Not Exists");	
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
