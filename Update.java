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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
				String upd="update studentdata set sname=? where sid=?";
				pst=conn.prepareStatement(upd);
				pst.setString(1, name);
				pst.setInt(2, id);
				int i=pst.executeUpdate();
				if(i>0)
				{
					out.println("Updated Successfully");
				}
				else
				{
					out.println("Not Updated");	
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
